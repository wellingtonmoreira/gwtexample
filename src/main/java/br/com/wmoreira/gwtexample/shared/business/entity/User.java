package br.com.wmoreira.gwtexample.shared.business.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class User implements Serializable{

    private static final long serialVersionUID = 8978296459374501482L;
    
    private int id;
    private String name;
    private String password;
    private Date creationDate;
    private boolean enabled;
    private String email;
    
    public User() {
	/* default constructor */
    }
    
    public User(String name, String password, Date creationDate, boolean enabled, String email) {
	this.name = name;
	this.password = password;
	this.creationDate = creationDate;
	this.enabled = enabled;
	this.email = email;
    }
    
    public User(int id, String name, String password, Date creationDate, boolean enabled, String email) {
	this.id = id;
	this.name = name;
	this.password = password;
	this.creationDate = creationDate;
	this.enabled = enabled;
	this.email = email;
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
    
    public void setName(String username) {
        this.name = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
}
