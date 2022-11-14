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
        boolean isWorking= Authenticator.authenticate(userDB);
        Scanner scanner = new Scanner(System.in);
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
                case "4":
                    isWorking=false;
                    DBManager.persistToFile(productDB,userDB);
                    break;
                case "3":
                    if(Authenticator.loggedUser.getRole()== User.Role.ADMIN){
                        GUI.addProductAmount(productDB);
                        break;
                    }
                default:
                    System.out.println("Zły numer!");
                    break;
            }
        }
    }
}
