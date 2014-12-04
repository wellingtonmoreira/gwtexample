package br.com.wmoreira.gwtexample.server.integration.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.wmoreira.gwtexample.server.integration.dao.GroupDAO;
import br.com.wmoreira.gwtexample.server.integration.datasource.PoolDataSourceFactory;
import br.com.wmoreira.gwtexample.server.integration.util.mapper.GroupMapper;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupDAOImpl implements GroupDAO {

    private static final Logger LOGGER = Logger.getLogger(GroupDAOImpl.class);
    private Connection conn;
    private GroupMapper mapper;
    
    public GroupDAOImpl() {
	conn = PoolDataSourceFactory.getConnection();
	mapper = new GroupMapper();
    }
    
    @Override
    public Group find(int id) throws Exception {
	LOGGER.info("GroupDAO | find() : id : " + id);
	try {
	    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_group WHERE group_id = ?");
	    stmt.setInt(1, id);
	    Group tb_group = mapper.mapResultToObject(stmt.executeQuery());
	    
	    if (tb_group == null) {
		LOGGER.info("Group not found");
	    } else {
		LOGGER.info("Group found : Group : " + tb_group.toString());
	    }

	    return tb_group;
        } catch (SQLException e) {
            LOGGER.error("Exception thrown: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Group> findAll() throws Exception {
	LOGGER.info("GroupDAO | findAll()");
	try {
	    CallableStatement stmt = conn.prepareCall("SELECT * FROM tb_group");
	    List<Group> list = mapper.mapResultToObjects(stmt.executeQuery());

	    LOGGER.info("Retrieved list size: " + list.size());

	    return list;
        } catch (SQLException e) {
            LOGGER.error("Exception thrown: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int create(Group object) throws Exception {
	LOGGER.info("GroupDAO | create() : Group : " + object.toString());
	try {
	    PreparedStatement stmt = conn.prepareStatement("INSERT INTO tb_group VALUES(NEXTVAL('group_seq'), ?)");
	    stmt.setString(1, object.getName());
	    int result = stmt.executeUpdate();

	    LOGGER.info("Group created successfully");

	    return result;
        } catch (SQLException e) {
            LOGGER.error("Exception thrown: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int update(Group object) throws Exception {
	LOGGER.info("GroupDAO | update() : Group : " + object.toString());
	try {
	    PreparedStatement stmt = conn.prepareStatement("UPDATE tb_group SET name = ? WHERE group_id = ?");
	    stmt.setString(1, object.getName());
	    stmt.setInt(2, object.getId());
	    int result = stmt.executeUpdate();

	    LOGGER.info("Group updated successfully");

	    return result;
        } catch (SQLException e) {
            LOGGER.error("Exception thrown: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public int delete(int id) throws Exception {
	try {
	    PreparedStatement stmt = conn.prepareStatement("DELETE FROM tb_group WHERE group_id = ?");
	    stmt.setInt(1, id);
	    int result = stmt.executeUpdate();

	    LOGGER.info("Group deleted successfully");

	    return result;
        } catch (SQLException e) {
            LOGGER.error("Exception thrown: " + e.getMessage());
            throw e;
        }
    }

}
