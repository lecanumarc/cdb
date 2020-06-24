package com.excilys.formation.cdb.test.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.pojos.Computer;

public class TestComputerDAO {


	ComputerDAO computerDAO;

	@Before
	public void setUp() throws Exception {
		computerDAO = new ComputerDAO(true);
	}

	//	@After
	//	public void tearDown() throws Exception {
	//		connect = null;
	//		computerDAO = null; 
	//	}

	@Test
	public void testFindById() {
		Computer computer = computerDAO.findById(17);
		assertEquals("computer id is incorrect", 17, computer.getId());
		assertEquals("computer name is incorrect", "Apple III Plus", computer.getName());
		assertEquals("computer introduction date is incorrect", LocalDate.parse("1983-12-01"), computer.getIntroDate());
		assertEquals("computer discontinuation is incorrect", LocalDate.parse("1984-04-01"), computer.getDiscDate());
		assertEquals("computer company id is incorrect", 1, computer.getCompanyId());
	}

	@Test
	public void testFindByName() {
		Computer computer = computerDAO.findByName("Apple III Plus");
		assertEquals("computer id is incorrect", 17, computer.getId());
		assertEquals("computer name is incorrect", "Apple III Plus", computer.getName());
		assertEquals("computer introduction date is incorrect", LocalDate.parse("1983-12-01"), computer.getIntroDate());
		assertEquals("computer discontinuation is incorrect", LocalDate.parse("1984-04-01"), computer.getDiscDate());
		assertEquals("computer company id is incorrect", 1, computer.getCompanyId());
	}

	@Test
	public void testCreate() throws Exception {
		Computer computer = new Computer();
		computer.setCompanyId(1);
		computer.setDiscDate(LocalDate.parse("1984-04-01"));
		computer.setIntroDate(LocalDate.parse("1983-12-01"));
		computer.setName("Computer name");
		computerDAO.create(computer);
	}

	@Test
	public void testDelete() throws Exception {
		boolean deletion = computerDAO.delete(17);
		assertTrue("computer was not deleted", deletion);
	}

	@Test
	public void testCountRows() throws Exception {
		assertEquals(574, computerDAO.getNumberRows());
	}
	
	@Test
	public void testList() throws Exception {
		ArrayList<Computer> list = computerDAO.list();
		assertEquals(574, list.size());
	}
	

	@Test
	public void testListByPage() throws Exception {
		ArrayList<Computer> list = computerDAO.listByPage(0, 10);
		assertEquals(10, list.size());
	}


	

}
