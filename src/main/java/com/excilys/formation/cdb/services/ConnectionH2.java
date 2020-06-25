package com.excilys.formation.cdb.services;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class ConnectionH2 implements Connector{ 

	private static Connection connect;
	private static final String FILE_PATH =  "src/test/resources/datasource.properties";

	private static HikariConfig config = new HikariConfig(FILE_PATH);
	private static HikariDataSource ds = new HikariDataSource(config);
	
	public ConnectionH2(){
		try {
			connect = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance() throws SQLException{
		if(connect == null || connect.isClosed()){
			new ConnectionH2();
		}
		return connect;   
	}  
	
	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}