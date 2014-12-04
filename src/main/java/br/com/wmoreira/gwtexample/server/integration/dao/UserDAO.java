package br.com.wmoreira.gwtexample.server.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.wmoreira.gwtexample.server.integration.datasource.PoolDataSourceFactory;
import br.com.wmoreira.gwtexample.server.integration.util.Crudable;
import br.com.wmoreira.gwtexample.server.integration.util.mapper.UserMapper;
import br.com.wmoreira.gwtexample.shared.business.entity.User;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class UserDAO
    implements Crudable<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    private Connection          conn;
    private UserMapper          mapper;

    public UserDAO() {
	conn = PoolDataSourceFactory.getConnection();
	mapper = new UserMapper();
    }

    @Override
    public User find(int id) throws Exception {
	LOGGER.info("UserDAO | find() : id : " + id);
	try {
	    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_user WHERE user_id = ?");
	    stmt.setInt(1, id);
	    User user = mapper.mapResultToObject(stmt.executeQuery());

	    if (user == null) {
		LOGGER.info("User not found");
	    } else {
		LOGGER.info("User found : User : " + user.toString());
	    }

	    return user;
	} catch (SQLException e) {
	    LOGGER.error("Exception thrown: " + e.getMessage());
	    throw e;
	}
    }

    @Override
    public List<User> findAll() throws Exception {
	LOGGER.info("UserDAO | findAll()");
	try {
	    CallableStatement stmt = conn.prepareCall("SELECT * FROM tb_user");
	    List<User> list = mapper.mapResultToObjects(stmt.executeQuery());

	    LOGGER.info("Retrieved list size: " + list.size());

	    return list;
	} catch (SQLException e) {
	    LOGGER.error("Exception thrown: " + e.getMessage());
	    throw e;
	}
    }

    @Override
    public int create(User object) throws Exception {
	LOGGER.info("UserDAO | create() : User : " + object.toString());
	try {
	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO tb_user VALUES(NEXTVAL('user_seq'), ?, ?, ?, ?, ?)");
	    stmt.setString(1, object.getName());
	    stmt.setString(2, object.getPassword());
	    stmt.setDate(3, object.getCreationDate());
	    stmt.setBoolean(4, object.isEnabled());
	    stmt.setString(5, object.getEmail());
	    int result = stmt.executeUpdate();

	    LOGGER.info("User created successfully");

	    return result;
	} catch (SQLException e) {
	    LOGGER.error("Exception thrown: " + e.getMessage());
	    throw e;
	}
    }

    @Override
    public int update(User object) throws Exception {
	LOGGER.info("UserDAO | update() : User : " + object.toString());
	try {
	    PreparedStatement stmt = conn.prepareStatement("UPDATE tb_user SET name = ?, password = ?, creation_date = ?, enabled = ?, email = ? WHERE user_id = ?");
	    stmt.setString(1, object.getName());
	    stmt.setString(2, object.getPassword());
	    stmt.setDate(3, object.getCreationDate());
	    stmt.setBoolean(4, object.isEnabled());
	    stmt.setString(5, object.getEmail());
	    stmt.setInt(6, object.getId());
	    int result = stmt.executeUpdate();

	    LOGGER.info("User updated successfully");

	    return result;
	} catch (SQLException e) {
	    LOGGER.error("Exception thrown: " + e.getMessage());
	    throw e;
	}
    }

    @Override
    public int delete(int id) throws Exception {
	LOGGER.info("UserDAO | delete() : id : " + id);
	try {
	    PreparedStatement stmt = conn.prepareStatement("DELETE FROM tb_user WHERE user_id = ?");
	    stmt.setInt(1, id);
	    int result = stmt.executeUpdate();

	    LOGGER.info("User deleted successfully");

	    return result;
	} catch (SQLException e) {
	    LOGGER.error("Exception thrown: " + e.getMessage());
	    throw e;
	}
    }
}
