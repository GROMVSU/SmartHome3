package dao;

import exception.PersistentException;
import model.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    User read(String login, String password) throws PersistentException;

    List<User> allUsers() throws PersistentException;

}
