package com.excilys.formation.cdb.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.daos.ComputerDAO;
import com.excilys.formation.cdb.pojos.Computer;

@WebServlet("/find")
public class FindComputerServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("id"));
//		ComputerDAO dao = new ComputerDAO(true);
//		Computer computer = dao.findById(id);
//		System.out.println(computer.toString());
		System.out.println("Id : " +id);
	}
}
