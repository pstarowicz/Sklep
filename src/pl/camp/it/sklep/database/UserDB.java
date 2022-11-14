package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private final Map<String,User> users = new HashMap<>();
    private final String USER_DB_FILE = "users.txt";

    public UserDB() {

    }

    public User findUserByLogin(String login){
        return this.users.get(login);
    }


    public Map<String, User> getUsers() {
        return users;
    }
}
