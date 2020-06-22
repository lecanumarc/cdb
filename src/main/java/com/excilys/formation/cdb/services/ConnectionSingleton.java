package com.excilys.formation.cdb.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionSingleton {
	
	private static Connection connect;

	private ConnectionSingleton(){
		ArrayList<String> properties = FileReader.readFile("properties");
		try {
			connect = DriverManager.getConnection(properties.get(0), properties.get(1), properties.get(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance(){
		if(connect == null){
			new ConnectionSingleton();
		}
		return connect;   
	}   
	
}