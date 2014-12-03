package br.com.wmoreira.gwtexample.client.service;

import java.util.List;

import br.com.wmoreira.gwtexample.shared.business.entity.Group;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author welingtonmoreira
 *
 */

@RemoteServiceRelativePath("group")
public interface GroupService
    extends RemoteService {

    Group find(int id);

    List<Group> findAll();

    int create(Group object);

    int update(Group object);

    int delete(int id);
}
