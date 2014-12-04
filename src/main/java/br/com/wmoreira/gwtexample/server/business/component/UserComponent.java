package br.com.wmoreira.gwtexample.server.business.component;

import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.dao.impl.UserDAOImpl;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserComponent {

    private UserDAOImpl dao;
    
    public UserComponent() {
	this.dao = new UserDAOImpl();
    }
    
    public User find(int id) throws Exception {
	return dao.find(id);
    }

    public List<User> findAll() throws Exception {
	return dao.findAll();
    }

    public int create(User object) throws Exception {
	return dao.create(object);
    }

    public int update(User object) throws Exception {
	return dao.update(object);
    }

    public int delete(int id) throws Exception {
	return dao.delete(id);
    }
    
}
