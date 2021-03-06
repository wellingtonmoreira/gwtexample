package br.com.wmoreira.gwtexample.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.wmoreira.gwtexample.client.service.GroupService;
import br.com.wmoreira.gwtexample.server.business.component.GroupComponent;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupServiceImpl extends RemoteServiceServlet implements GroupService {

    /**
     * 
     */
    private static final long serialVersionUID = -3704612216030963407L;
    private GroupComponent component;
    
    public GroupServiceImpl() {
	component = new GroupComponent();
    }
    
    @Override
    public Group find(int id) throws Exception {
	return component.find(id);
    }

    @Override
    public List<Group> findAll() throws Exception {
	return component.findAll();
    }

    @Override
    public int create(Group object) throws Exception {
	return component.create(object);
    }

    @Override
    public int update(Group object) throws Exception {
	return component.update(object);
    }

    @Override
    public int delete(int id) throws Exception {
	return component.delete(id);
    }

}
