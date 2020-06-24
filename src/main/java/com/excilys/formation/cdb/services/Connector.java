package com.excilys.formation.cdb.services;

import java.sql.Connection;

public interface Connector {

	public abstract Connection getInstance();

	public abstract void close();
}
