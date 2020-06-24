package com.excilys.formation.cdb.validator;

import com.excilys.formation.cdb.pojos.Computer;

public class ComputerValidator {

	public static void validate(Computer computer) {
		if(computer == null) {
			throw new NullPointerException ("Computer object is null");
		}
		if(computer.getName().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if(computer.getIntroDate() != null && computer.getDiscDate() != null && computer.getIntroDate().isAfter(computer.getDiscDate())) {
			throw new IllegalArgumentException("Introduction date must not be greater than discontinuation date.");
		}
		
	}
}
