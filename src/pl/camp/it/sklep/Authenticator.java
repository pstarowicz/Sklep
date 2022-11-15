package pl.camp.it.sklep;

import pl.camp.it.sklep.GUI.GUI;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.User;

public class Authenticator {
    public static User loggedUser;
    public static final String seed = "cdscdssdv434632*(&*)";

    public static boolean authenticate(UserDB userDB){
        for(int i=0;i<3; i++){
            User user = GUI.readLogAndPass();
            User userFromDB = userDB.findUserByLogin(user.getLogin());
            if(userFromDB != null && userFromDB.equals(user)){
                Authenticator.loggedUser = userFromDB;
                System.out.println("Zalogowano pomyślnie");
                return true;
            }
        }
        System.out.println("Błąd logowania");
        return false;
    }
}
