package com.excilys.formation.cdb.daos;

import java.sql.Connection;

import com.excilys.formation.cdb.services.ConnectionSingleton;

public class DAOFactory {
	protected static final Connection conn = ConnectionSingleton.getInstance();   

	/**
	 * Retourne un objet interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO getComputerDAO(){
		return new ComputerDAO(conn);
	}

	/**
	 * Retourne un objet interagissant avec la BDD
	 * @return DAO
	 */
	public static DAO getCompanyDAO(){
		return new CompanyDAO(conn);
	}

}