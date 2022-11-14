package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private final Map<String,User> users = new HashMap<>();
    private final String USER_DB_FILE = "users.txt";

    public UserDB() {
        /*try {
            BufferedReader reader = new BufferedReader(new FileReader(USER_DB_FILE));
            String line;
            while((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                this.users.put(params[0],
                        new User(params[0], params[1], User.Role.valueOf(params[2])));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("plik nie dziala !!");
        }*/
    }

    public User findUserByLogin(String login){
        return this.users.get(login);
    }

    public void persistToFile(){
        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(this.USER_DB_FILE));
            boolean flag = false;
            for(User user : this.users.values()) {
                if(flag) {
                    writer.newLine();
                }
                flag = true;
                writer.write(user.convertToData());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu");
            e.printStackTrace();
        }
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
