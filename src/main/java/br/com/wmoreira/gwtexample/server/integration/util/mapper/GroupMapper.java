package br.com.wmoreira.gwtexample.server.integration.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmoreira.gwtexample.server.integration.util.Mapper;
import br.com.wmoreira.gwtexample.shared.business.entity.Group;

/**
 * 
 * @author welingtonmoreira
 *
 */

public class GroupMapper
    implements Mapper<Group> {

    @Override
    public Group mapResultToObject(ResultSet rs) throws SQLException {
	if (rs.next()) {
	    return new Group(rs.getInt("group_id"), rs.getString("name"));
	}
	return null;

    }

    @Override
    public List<Group> mapResultToObjects(ResultSet rs)
	throws SQLException {
	List<Group> groups = new ArrayList<Group>();

	while (rs.next()) {
	    groups.add(new Group(rs.getInt("group_id"), rs.getString("name")));
	}
	
	return groups;

    }

}
