package br.com.wmoreira.gwtexample.server.business.component;

import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.dao.UserDAO;
import br.com.wmoreira.gwtexample.server.integration.util.Crudable;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

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
    public User find(int id) throws Exception {
	return dao.find(id);
    }

    @Override
    public List<User> findAll() throws Exception {
	return dao.findAll();
    }

    @Override
    public int create(User object) throws Exception {
	return dao.create(object);
    }

    @Override
    public int update(User object) throws Exception {
	return dao.update(object);
    }

    @Override
    public int delete(int id) throws Exception {
	return dao.delete(id);
    }
    
}
