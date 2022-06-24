package by.tms.sql.MySQL_3_trying.dao;

import by.tms.sql.MySQL_3_trying.model.Person;

import java.util.List;

public class BaseDAOImpl implements  BaseDAO<Person>{


    @Override
    public boolean add(Person person) {
        return false;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }

    @Override
    public Person findById(int byId) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }
}
