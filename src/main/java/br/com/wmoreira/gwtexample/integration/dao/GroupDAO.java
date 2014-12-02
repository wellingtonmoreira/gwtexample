package br.com.wmoreira.gwtexample.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.wmoreira.gwtexample.business.entity.Group;
import br.com.wmoreira.gwtexample.integration.datasource.PoolDataSourceFactory;
import br.com.wmoreira.gwtexample.integration.util.GroupMapper;


public class GroupDAO implements Crudable<Group> {

    private Connection conn;
    private GroupMapper mapper;
    
    public GroupDAO() {
	conn = PoolDataSourceFactory.getConnection();
	mapper = new GroupMapper();
    }
    
    @Override
    public Group find(int id) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM group WHERE group_id = ?");
	    stmt.setInt(0, id);
	    return mapper.mapResultToObject(stmt.executeQuery());
        } catch (SQLException e) {
	    // exception handling
            return null;
        }
    }

    @Override
    public List<Group> findAll() {
	try {
	    CallableStatement stmt = conn.prepareCall("SELECT * FROM group");
	    return mapper.mapResultToObjects(stmt.executeQuery());
        } catch (SQLException e) {
	    // exception handling
            return null;
        }
    }

    @Override
    public int create(Group object) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO group VALUES(NEXTVAL('group_seq'), ?)");
	    stmt.setString(0, object.getName());
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }

    @Override
    public int update(Group object) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("UPDATE group SET name = ? WHERE group_id = ?");
	    stmt.setString(0, object.getName());
	    stmt.setInt(1, object.getId());
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }

    @Override
    public int delete(int id) {
	try {
	    PreparedStatement stmt = conn.prepareStatement("DELETE FROM group WHERE group_id = ?");
	    stmt.setInt(0, id);
	    return stmt.executeUpdate();
        } catch (SQLException e) {
	    // exception handling
            return 0;
        }
    }

}
