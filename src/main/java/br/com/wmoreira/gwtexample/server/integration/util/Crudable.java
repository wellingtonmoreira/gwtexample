package br.com.wmoreira.gwtexample.server.integration.util;

import java.util.List;

/**
 * 
 * @author welingtonmoreira
 *
 * @param <T>
 */

public interface Crudable<T> {
    T find(int id);
    List<T> findAll();
    int create(T object);
    int update(T object);
    int delete(int id);
}
