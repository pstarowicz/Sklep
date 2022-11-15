package pl.camp.it.sklep.core;

import pl.camp.it.sklep.*;
import pl.camp.it.sklep.GUI.GUI;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.User;

import java.util.Scanner;

public class Engine {
    public static void start(){
        ProductDB productDB = new ProductDB();
        UserDB userDB = new UserDB();
        DBManager.getFromFile(productDB,userDB);
        Scanner scanner = new Scanner(System.in);

        boolean isWorking=false;
        boolean isNotLogged=true;
        while(isNotLogged) {
            System.out.println("1. Rejestracja");
            System.out.println("2. Logowanie");
            System.out.println("3. Wyjście");
            switch(scanner.nextLine()) {
                case "1":
                    Registration.registrate(userDB);
                    break;
                case "2":
                    isWorking= Authenticator.authenticate(userDB);
                    isNotLogged=!isWorking;
                    break;
                case "3":
                    isNotLogged=false;
                    DBManager.persistToFile(productDB,userDB);
                    break;
                default:
                    System.out.println("Nieprawidłowy numer");
                    break;
            }
        }


        System.out.println();

        while(isWorking){
            GUI.showMenu();
            switch(scanner.nextLine()){
                case "1":
                    GUI.listProducts(productDB.getProducts());
                    break;
                case "2":
                    GUI.buyProduct(productDB);
                    break;
                case "5":
                    isWorking=false;
                    DBManager.persistToFile(productDB,userDB);
                    break;
                case "3":
                    if(Authenticator.loggedUser.getRole()== User.Role.ADMIN){
                        GUI.addProductAmount(productDB);
                        break;
                    }
                case "4":
                    if(Authenticator.loggedUser.getRole()==User.Role.ADMIN){
                        GUI.changeUserToAdmin(userDB);
                        break;
                    }
                default:
                    System.out.println("Zły numer!");
                    break;
            }
        }
    }
}
