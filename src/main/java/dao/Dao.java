package dao;

import exception.PersistentException;
import model.BaseEntity;

public interface Dao<Type extends BaseEntity> {
    Integer create(Type entity) throws PersistentException;

    Type read(Integer identity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}