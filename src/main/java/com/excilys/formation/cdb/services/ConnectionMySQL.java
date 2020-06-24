package com.excilys.formation.cdb.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionMySQL implements Connector {
	
	private static Connection connect;

	public ConnectionMySQL(){
		ArrayList<String> properties = FileReader.readFile("properties");
		try {
			connect = DriverManager.getConnection(properties.get(0), properties.get(1), properties.get(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getInstance(){
		if(connect == null){
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