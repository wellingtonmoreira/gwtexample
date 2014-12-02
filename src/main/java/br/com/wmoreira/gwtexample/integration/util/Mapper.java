package br.com.wmoreira.gwtexample.integration.util;

import java.sql.ResultSet;
import java.util.List;

/**
 * 
 * @author welingtonmoreira
 *
 * @param <T>
 */

public interface Mapper<T> {

    T mapResultToObject(ResultSet set);
    List<T> mapResultToObjects(ResultSet set);
    
}