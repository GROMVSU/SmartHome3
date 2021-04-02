package service;

import exception.PersistentException;
import model.User;

import java.util.List;

public interface UserService extends Service {
    List<User> allUsers() throws PersistentException;

    User findById(Integer identity) throws PersistentException;

    User findByLoginAndPassword(String login, String password) throws PersistentException;

    void save(User user) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
