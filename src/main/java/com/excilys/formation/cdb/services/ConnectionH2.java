package com.excilys.formation.cdb.services;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.store.RangeReader;
import org.h2.tools.Script;
import org.h2.util.ScriptReader;

public class ConnectionH2 implements Connector{ 

	private static Properties properties;
	private static Connection connect;
	private static final String FILE_PATH =  "src/test/resources/h2DataBase.properties";
	private static final String URL_KEY =  "urlH2";
	private static final String USER_KEY  =  "user";
	private static final String PASSWORD_KEY  =  "password";

	public ConnectionH2(){
		properties = new Properties();
		try (InputStream input = new FileInputStream(FILE_PATH)) {
			properties.load(input);

		} catch (IOException io) {
			io.printStackTrace();
		}

		try {

			connect = DriverManager.getConnection(properties.getProperty(URL_KEY),
					properties.getProperty(USER_KEY),
					properties.getProperty(PASSWORD_KEY));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance(){
		if(connect == null){
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