package pl.camp.it.sklep;

import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;

import java.io.*;

public class DBManager {

    private final static String DATA_FILE = "data.txt";

    public static void getFromFile(ProductDB productDB, UserDB userDB){
        try {
            boolean flag=true;
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.equals(" ")) {
                    flag=false;
                    line=reader.readLine();
                }
                if(flag){
                    String[] params = line.split(";");
                    userDB.getUsers().put(params[0],
                            new User(params[0], params[1], User.Role.valueOf(params[2])));
                }
                else {
                    String[] productData = line.split(";");
                    productDB.getProducts().add(new Product(productData));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("plik nie dziala !!");
        }
    }
    public static void persistToFile(ProductDB productDB, UserDB userDB){
        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(DATA_FILE));
            boolean flag = false;
            for(User user : userDB.getUsers().values()) {
                if(flag) {
                    writer.newLine();
                }
                flag = true;
                writer.write(user.convertToData());
            }
            writer.newLine();
            writer.write(" ");
            for(int i = 0; i < productDB.getProducts().size(); i++) {
                writer.newLine();
                writer.write(productDB.getProducts().get(i).convertToData());
                //writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("B????d podczas zapisu");
            e.printStackTrace();
        }
    }
}
