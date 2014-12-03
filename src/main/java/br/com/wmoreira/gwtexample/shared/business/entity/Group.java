package br.com.wmoreira.gwtexample.shared.business.entity;

import java.io.Serializable;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class Group implements Serializable{
    
    private static final long serialVersionUID = -7724237112081511503L;
    
    private int id;
    private String name;
    
    public Group() {
	/* default constructor */
    }
    
    public Group(String name) {
	this.name = name;
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
    
    public String toString() {
	StringBuilder builder = new StringBuilder();
	
	builder.append("Group {");
	builder.append("\nid - ");
	builder.append(getId());
	builder.append("\nname - ");
	builder.append(getName());
	builder.append("\n}");
	
	return builder.toString();
    }
}
