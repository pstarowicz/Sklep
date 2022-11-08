package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.User;

public class UserDB {
    private User[] users = new User[2];

    public UserDB() {
        this.users[0] = new User("admin","df678e611ef53095dc775d94c7f3d577", User.Role.ADMIN);
        this.users[1] = new User("user","3026eb3ca37a1d6205fb6cd725e9da54", User.Role.USER);
    }

    public User findUserByLogin(String login){
        for(User user:this.users){
            if(user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }
}
