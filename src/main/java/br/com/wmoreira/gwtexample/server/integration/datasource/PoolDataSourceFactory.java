package br.com.wmoreira.gwtexample.server.integration.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 * @author welingtonmoreira
 */

public class PoolDataSourceFactory {

    public static final Logger       LOGGER = Logger.getLogger(PoolDataSourceFactory.class);

    private static GenericObjectPool connectionPool;
    private static DataSource        dataSource;

    public static Connection getConnection() {
	LOGGER.info("PoolDataSourceFactory | getConnection()");

	getDataSource();
	if (dataSource != null) {
	    LOGGER.info("Datasource not null");

	    try {
		LOGGER.info("Attempting to retrieve connection from Datasource");

		return dataSource.getConnection();
	    } catch (SQLException e) {
		LOGGER.error("Exception thrown: " + e.getMessage());
	    }
	}
	return null;
    }

    public static DataSource getDataSource() {
	try {
	    LOGGER.info("PoolDataSourceFactory | getDataSource()");

	    if (dataSource == null) {
		LOGGER.info("Datasource is null");

		Properties dtProps = getDataSourceProperties();

		if (dtProps != null) {
		    Class.forName(dtProps.getProperty("driver"));

		    connectionPool = new GenericObjectPool();
		    connectionPool.setMaxActive(20);

		    ConnectionFactory cf = new DriverManagerConnectionFactory(dtProps.getProperty("url"), 
		                                                              dtProps.getProperty("user"),
			                                                      dtProps.getProperty("password"));

		    new PoolableConnectionFactory(cf, connectionPool, null, null, false, true);

		    dataSource = new PoolingDataSource(connectionPool);
		    PoolDataSourceFactory.setUpDb();
		}
	    }
	    return dataSource;
	} catch (ClassNotFoundException e) {
	    LOGGER.error("Exception thrown" + e.getMessage());
	    return null;
	}
    }

    public static GenericObjectPool getConnectionPool() {
	return connectionPool;
    }

    private static void setUpDb() {
	LOGGER.info("PoolDataSourceFactory | setUpDb()");

	try {
	    Properties p = new Properties();
	    InputStream is = PoolDataSourceFactory.class.getResourceAsStream("/db.properties");
	    p.load(is);

	    for (Object s : p.keySet()) {
		try {
		    PoolDataSourceFactory.getDataSource().getConnection().prepareCall(p.getProperty(s.toString()).toString()).execute();
		    LOGGER.info("db setup done succesfully");
		} catch (Exception e) {
		    LOGGER.error("Exception thrown " + e.getMessage());
		}

	    }
	} catch (IOException e) {
	    LOGGER.error("Exception thrown " + e.getMessage());
	}
    }

    private static Properties getDataSourceProperties() {
	LOGGER.info("PoolDataSourceFactory | getDataSourceProperties()");
	Properties p = new Properties();
	InputStream is = PoolDataSourceFactory.class.getResourceAsStream("/datasource.properties");

	try {
	    p.load(is);
	    return p;
	} catch (IOException e) {
	    LOGGER.error("Exception thrown " + e.getMessage());
	    return null;
	}

    }
}
