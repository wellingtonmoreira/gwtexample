package br.com.wmoreira.gwtexample.integration.dao;

import java.util.List;


public interface Crudable<T> {
    T find(int id);
    List<T> findAll();
    int create(T object);
    int update(T object);
    int delete(int id);
}
