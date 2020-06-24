package com.excilys.formation.cdb.test.pojos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.cdb.pojos.Computer;

public class TestComputer {

	private Computer computer;

	@Before
	public void setUp() throws Exception {
		computer = new Computer();
	}

	@After
	public void tearDown() throws Exception {
		computer = null;
	}

	@Test
	public void testSetName() throws Exception {

		assertNull(computer.getName());
		computer.setName("name");
		assertEquals("computer name is incorrect", "name", computer.getName());
		try {
			computer.setName("");
			fail("Exception should be caught");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSetDiscDate() throws Exception {
		//	discDate is null
		assertNull(computer.getDiscDate());
		
		LocalDate introDate = LocalDate.of(2019, 05, 01);
		computer.setIntroDate(introDate);
		LocalDate discDate = LocalDate.of(2018, 05, 01);
		try {
			//	discDate > introDate
			computer.setDiscDate(discDate);
			fail("Exception should be caught");
		} catch (IllegalArgumentException e) {

		}
		
		discDate = LocalDate.of(2020, 05, 01);

		//	discDate < introDate
		computer.setDiscDate(discDate);
		assertEquals("computer discDate is incorrect", discDate, computer.getDiscDate());
	}
	
	@Test
	public void testSetIntroDate() throws Exception {
		//	introDate is null
		assertNull(computer.getIntroDate());
		
		LocalDate introDate = LocalDate.of(2020, 05, 01);
		LocalDate discDate  = LocalDate.of(2019, 05, 01);
		computer.setDiscDate(discDate);
		try {
			//	introDate > discDate
			computer.setIntroDate(introDate);
			fail("Exception should be caught");
		} catch (IllegalArgumentException e) {

		}
		
		introDate = LocalDate.of(2018, 05, 01);

		//	introDate < discDate
		computer.setIntroDate(introDate);
		assertEquals("computer discDate is incorrect", introDate, computer.getIntroDate());
	}


	
}
