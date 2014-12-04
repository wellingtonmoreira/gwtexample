package br.com.wmoreira.gwtexample.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.wmoreira.gwtexample.client.service.UserService;
import br.com.wmoreira.gwtexample.server.business.component.UserComponent;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    /**
     * 
     */
    private static final long serialVersionUID = -2474825731368093768L;
    private UserComponent component;
    
    public UserServiceImpl() {
	component = new UserComponent();
    }
    
    
    @Override
    public User find(int id) throws Exception {
	return component.find(id);
    }

    @Override
    public List<User> findAll() throws Exception {
	return component.findAll();
    }

    @Override
    public int create(User object) throws Exception {
	return component.create(object);
    }

    @Override
    public int update(User object) throws Exception {
	return component.update(object);
    }

    @Override
    public int delete(int id) throws Exception {
	return component.delete(id);
    }

}
