package br.com.wmoreira.gwtexample.server.business.component;

import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.dao.impl.GroupDAOImpl;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupComponent {

    private GroupDAOImpl dao;
    
    public GroupComponent() {
	this.dao = new GroupDAOImpl();
    }
    
    public Group find(int id) throws Exception {
	return dao.find(id);
    }

    public List<Group> findAll() throws Exception {
	return dao.findAll();
    }

    public int create(Group object) throws Exception {
	return dao.create(object);
    }

    public int update(Group object) throws Exception {
	return dao.update(object);
    }

    public int delete(int id) throws Exception {
	return dao.delete(id);
    }

}
