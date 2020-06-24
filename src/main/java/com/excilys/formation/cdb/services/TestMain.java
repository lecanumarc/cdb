package com.excilys.formation.cdb.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.daos.DAOFactory;
import com.excilys.formation.cdb.pojos.Computer;

public class TestMain {

	public static void main(String[] args) {
	    Logger logger = LoggerFactory.getLogger(TestMain.class);
	    logger.error("Hello World");
	    int newT = 15;
        int oldT = 16;

        // using traditional API
        logger.debug("Temperature set to {}. Old temperature was {}.", newT, oldT);

        ComputerDAO dao = new ComputerDAO(true);
        Computer cpt = dao.findByName("Apple III Plus");
        System.out.println(cpt);
	}

}
