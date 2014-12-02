package br.com.wmoreira.gwtexample.business.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class Group implements Serializable{
    
    private static final long serialVersionUID = -7724237112081511503L;
    
    private int id;
    private String name;
    private List<User> users;
    
    public Group() {
	/* default constructor */
    }
    
    public Group(int id, String name) {
	this.id = id;
	this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
