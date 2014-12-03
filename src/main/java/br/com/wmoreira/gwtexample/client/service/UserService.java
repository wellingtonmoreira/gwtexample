package br.com.wmoreira.gwtexample.client.service;

import java.util.List;

import br.com.wmoreira.gwtexample.shared.business.entity.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author welingtonmoreira
 *
 */

@RemoteServiceRelativePath("user")
public interface UserService
    extends RemoteService {

    User find(int id);
    List<User> findAll();
    int create(User object);
    int update(User object);
    int delete(int id);
}
