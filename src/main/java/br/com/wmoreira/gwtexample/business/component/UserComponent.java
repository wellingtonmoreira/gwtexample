package br.com.wmoreira.gwtexample.business.component;

import java.util.List;

import br.com.wmoreira.gwtexample.business.entity.User;
import br.com.wmoreira.gwtexample.integration.dao.UserDAO;
import br.com.wmoreira.gwtexample.integration.util.Crudable;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserComponent implements Crudable<User>{

    private UserDAO dao;
    
    public UserComponent() {
	this.dao = new UserDAO();
    }
    
    @Override
    public User find(int id) {
	return dao.find(id);
    }

    @Override
    public List<User> findAll() {
	return dao.findAll();
    }

    @Override
    public int create(User object) {
	return dao.create(object);
    }

    @Override
    public int update(User object) {
	return dao.update(object);
    }

    @Override
    public int delete(int id) {
	return dao.delete(id);
    }
    
}
