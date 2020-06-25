package com.excilys.formation.cdb.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionMySQL implements Connector {
	
	private static Connection connect;
	private static final String FILE_PATH =  "src/main/resources/datasource.properties";

	private static HikariConfig config = new HikariConfig(FILE_PATH);
	private static HikariDataSource ds = new HikariDataSource(config);
	
	public ConnectionMySQL(){
		try {
			connect = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance() throws SQLException{
		if(connect == null || connect.isClosed()){
			new ConnectionMySQL();
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