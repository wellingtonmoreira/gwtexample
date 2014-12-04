package br.com.wmoreira.gwtexample.server.business.component;

import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.dao.GroupDAO;
import br.com.wmoreira.gwtexample.server.integration.util.Crudable;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupComponent implements Crudable<Group>{

    private GroupDAO dao;
    
    public GroupComponent() {
	this.dao = new GroupDAO();
    }
    
    @Override
    public Group find(int id) throws Exception {
	return dao.find(id);
    }

    @Override
    public List<Group> findAll() throws Exception {
	return dao.findAll();
    }

    @Override
    public int create(Group object) throws Exception {
	return dao.create(object);
    }

    @Override
    public int update(Group object) throws Exception {
	return dao.update(object);
    }

    @Override
    public int delete(int id) throws Exception {
	return dao.delete(id);
    }

}
