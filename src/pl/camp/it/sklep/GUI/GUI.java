package pl.camp.it.sklep.GUI;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;
import pl.camp.it.sklep.database.ProductDB;

import java.util.Scanner;

public class GUI {
    public static void showMenu(){
        System.out.println();
        System.out.println("1. Wyświetl listę produktów");
        System.out.println("2. Kup produkt");
        if (Authenticator.loggedUser.getRole() == User.Role.ADMIN) {

            System.out.println("3. Uzupełnij produkt");
        }
        System.out.println("4. Wyjście");
    }

    public static void listProducts(Product[] products){
        for(Product currentProduct:products){
            if(currentProduct.getAmount()!=0) {
                System.out.println(currentProduct);
            }
        }
    }

    public static void buyProduct(ProductDB productDB){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę produktu:");
        Product product = productDB.returnProduct(scanner.nextLine());
        if(product!=null){
            System.out.println("Podaj ilość sztuk do kupienia (maks. " + product.getAmount() + "):");
            int wanted=Integer.parseInt(scanner.nextLine());
            if(wanted<=product.getAmount() && wanted>=0){
                System.out.println("Kwota zakupu wynosi: "+ product.getPrice() * ((double) wanted) +"zł");
                product.setAmount(product.getAmount()-wanted);
            }
            else if(wanted>product.getAmount()){
                System.out.println("Podano za dużą ilość!");
            }
            else{
                System.out.println("Podano niewłaściwą ilość!");
            }
        }
        else System.out.println("Brak podanego produktu w bazie");
    }

    public static void addProductAmount(ProductDB productDB){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę produktu:");
        Product product = productDB.returnProduct(scanner.nextLine());
        if(product!=null){
            System.out.println("Podaj ilość sztuk do dodania (akt. "+product.getAmount()+"):");
            int added=Integer.parseInt(scanner.nextLine());
            if(added>=0){
                product.setAmount(product.getAmount()+added);
            }
            else{
                System.out.println("Podano ujemną liczbę!");
            }
        }
        else{
            System.out.println("Brak podanego produktu w bazie");
        }
    }

    public static User readLogAndPass(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Hasło:");
        return new User(login,scanner.nextLine());
    }
}
