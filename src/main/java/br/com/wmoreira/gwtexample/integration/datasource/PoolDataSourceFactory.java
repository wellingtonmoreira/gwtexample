package br.com.wmoreira.gwtexample.integration.datasource;

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

/**
 * @author welingtonmoreira
 */

public class PoolDataSourceFactory {

    public static final String       DRIVER = "org.h2.Driver";
    public static final String       URL    = "jdbc:h2:mem:test";
    public static final String       USER   = "sa";
    public static final String       PASSWD = "";

    private static GenericObjectPool connectionPool;
    private static DataSource        dataSource;

    public static Connection getConnection() {
	getDataSource();
	if (dataSource != null) {
	    try {
	        return dataSource.getConnection();
            } catch (SQLException e) {
	        //Exception handling
            }
	}
	return null;
    }
    
    public static DataSource getDataSource() {
	try {
	    if (dataSource == null) {
		Class.forName(PoolDataSourceFactory.DRIVER);

		connectionPool = new GenericObjectPool();
		connectionPool.setMaxActive(20);

		ConnectionFactory cf = new DriverManagerConnectionFactory(PoolDataSourceFactory.URL, 
		                                                          PoolDataSourceFactory.USER,
		                                                          PoolDataSourceFactory.PASSWD);

		new PoolableConnectionFactory(cf, connectionPool, null, null, false, true);

		dataSource = new PoolingDataSource(connectionPool);
		PoolDataSourceFactory.setUpDb();
	    }
	    return dataSource;
	} catch (ClassNotFoundException e) {
	    return null;
	}
    }

    public static GenericObjectPool getConnectionPool() {
	return connectionPool;
    }

    public static void setUpDb() {
	try {
	    Properties p = new Properties();
	    InputStream is = ClassLoader.getSystemResourceAsStream("db.properties");
	    p.load(is);

	    
	    for(Object s : p.keySet()) {
		if (s.toString().startsWith("create")) {
		    try {
			PoolDataSourceFactory.getDataSource().getConnection().prepareCall(p.getProperty(s.toString()).toString()).execute();
		    } catch (Exception e) {
			//exception handling
		    }
		}
	    }
	    
//	    Alternativa com Lambda
//	    p.forEach((k, v) -> {
//		if (k.toString().startsWith("create")) {
//		    try {
//			PoolDataSourceFactory.getDataSource().getConnection().prepareCall(v.toString()).execute();
//		    } catch (Exception e) {
//			//exception handling
//		    }
//		}
//	    });
	} catch (IOException e1) {
	    //exception handling
	}
    }
}
