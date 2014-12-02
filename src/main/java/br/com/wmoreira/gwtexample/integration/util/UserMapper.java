package br.com.wmoreira.gwtexample.integration.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.business.entity.User;

public class UserMapper
    implements Mapper<User> {

    @Override
    public User mapResultToObject(ResultSet rs) {
	try {
	    if (rs.next()) {
		return new User(rs.getInt("user_id"), 
		                rs.getString("name"), 
		                rs.getString("password"), 
		                rs.getDate("creationDate"), 
		                rs.getBoolean("enabled"), 
		                rs.getString("email"));
	    }
	} catch (SQLException e) {
	    // exception handling
	}
	return null;
	
    }

    @Override
    public List<User> mapResultToObjects(ResultSet rs) {
	List<User> groups = new ArrayList<User>();

	try {
	    while (rs.next()) {
		groups.add(new User(rs.getInt("user_id"), 
			                rs.getString("name"), 
			                rs.getString("password"), 
			                rs.getDate("creationDate"), 
			                rs.getBoolean("enabled"), 
			                rs.getString("email")));
	    }
	} catch (SQLException e) {
	    // exception handling
	}
	return groups;

    }

}
