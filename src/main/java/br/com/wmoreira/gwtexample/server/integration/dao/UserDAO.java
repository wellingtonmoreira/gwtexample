package br.com.wmoreira.gwtexample.server.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.datasource.PoolDataSourceFactory;
import br.com.wmoreira.gwtexample.server.integration.util.Crudable;
import br.com.wmoreira.gwtexample.server.integration.util.mapper.UserMapper;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserDAO implements Crudable<User>{

    private Connection conn;
    private UserMapper mapper;
    
    public UserDAO() {
	conn = PoolDataSourceFactory.getConnection();
	mapper = new UserMapper();
    }
    
    @Override
    public User find(int id) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
	    stmt.setInt(1, id);
	    return mapper.mapResultToObject(stmt.executeQuery());
        } catch (SQLException e) {
	    // exception handling
            return null;
        }
    }

    @Override
    public List<User> findAll() {
	try {
	    CallableStatement stmt = conn.prepareCall("SELECT * FROM user");
	    return mapper.mapResultToObjects(stmt.executeQuery());
        } catch (SQLException e) {
	    // exception handling
            return null;
        }
    }

    @Override
    public int create(User object) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO user VALUES(NEXTVAL('user_seq'), ?, ?, ?, ?, ?)");
	    stmt.setString(1, object.getName());
	    stmt.setString(2, object.getPassword());
	    stmt.setDate(3, object.getCreationDate());
	    stmt.setBoolean(4, object.isEnabled());
	    stmt.setString(5, object.getEmail());
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }

    @Override
    public int update(User object) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("UPDATE user SET name = ?, password = ?, creation_date = ?, enabled = ?, email = ? WHERE user_id = ?");
	    stmt.setString(1, object.getName());
	    stmt.setString(2, object.getPassword());
	    stmt.setDate(3, object.getCreationDate());
	    stmt.setBoolean(4, object.isEnabled());
	    stmt.setString(5, object.getEmail());
	    stmt.setInt(6, object.getId());
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }

    @Override
    public int delete(int id) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE user_id = ?");
	    stmt.setInt(1, id);
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }
}
