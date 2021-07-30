package com.MySQL_3_trying.dao;

import java.util.List;

public interface BaseDAO<T>  {

    boolean add(T t);

    boolean delete (T t);

    T findById(int byId);

    List<T> findAll();

}
