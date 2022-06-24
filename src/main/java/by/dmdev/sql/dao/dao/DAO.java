package by.dmdev.sql.dao.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <L, E>{

    boolean delete(L id);
    E save(E ticket);
    void update(E ticket);
    List<E> findAll();
    Optional<E> selectById(L id);
}
