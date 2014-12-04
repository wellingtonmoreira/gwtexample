package br.com.wmoreira.gwtexample.server.integration.util;

import java.util.List;

/**
 * 
 * @author welingtonmoreira
 *
 * @param <T>
 */

public interface Crudable<T> {
    T find(int id) throws Exception;
    List<T> findAll() throws Exception;
    int create(T object) throws Exception;
    int update(T object) throws Exception;
    int delete(int id) throws Exception;
}
