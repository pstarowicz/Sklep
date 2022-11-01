public class Authenticator {
    public static User loggedUser;
    public static final String seed = "cdscdssdv434632*(&*)";

    public static boolean authenticate(UserDB userDB){
        for(int i=0;i<3; i++){
            User user = GUI.readLogAndPass();
            User userFromDB = userDB.findUserByLogin(user.getLogin());
            if(userFromDB != null && userFromDB.equals(user)){
                Authenticator.loggedUser = userFromDB;
                return true;
            }
        }
        return false;
    }
}
