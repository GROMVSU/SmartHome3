package dao;

import exception.PersistentException;

public interface DaoFactory {
    <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException;

    void close();
}
