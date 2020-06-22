package com.excilys.formation.cdb.daos;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.formation.cdb.mapper.ComputerMapper;
import com.excilys.formation.cdb.pojos.Computer;

public class ComputerDAO extends DAO<Computer> {

	private static final String CREATE_QRY = "insert into computer id, name, introduced, discontinued, company_id value (?,?,?,?,?)";
	private static final String DELETE_QRY = "delete from computer where id = (?)";
	private static final String UPDATE_QRY = "update computer set name = ?, introduced = ?, discontinued = ? where id = ?";
	private static final String FIND_QRY = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?"; 
	private static final String LIST_QRY = "SELECT id, name, introduced, discontinued, company_id FROM computer"; 
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM computer";
	private static final String PAGINATED_LIST_QRY =  "SELECT id, name, introduced, discontinued, company_id FROM computer LIMIT ? OFFSET ? ";
	
	public ComputerDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Computer obj) {
		try (PreparedStatement st = connect.prepareStatement(CREATE_QRY)){
			st.setNull(1, java.sql.Types.INTEGER);
			if(obj.getName() == "") {
				throw new SQLException("Cannot insert computer with empty name !");
			} else {
				st.setString(2, obj.getName());
			}
			if(obj.getIntroDate() != null && obj.getDiscDate() != null) {
				if(obj.getIntroDate().compareTo(obj.getDiscDate()) > 0) {
					throw new Exception("Introduction date must be greater than discountinuation date.");
				}
			}
			if(obj.getIntroDate() == null) {
				st.setNull(3, java.sql.Types.DATE);
			} else {
				st.setDate(3, java.sql.Date.valueOf(obj.getIntroDate()));
			}
			if(obj.getDiscDate() == null) {
				st.setNull(4, java.sql.Types.DATE);
			} else {
				st.setDate(4, java.sql.Date.valueOf(obj.getDiscDate())); 
			}
			if(obj.getCompanyId() == 0) {
				st.setNull(5, java.sql.Types.INTEGER);
			} else {
				st.setInt(5, obj.getCompanyId());
			}
			int count = st.executeUpdate();
			System.out.println(count +" computer row(s) created.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		try (PreparedStatement st = connect.prepareStatement(DELETE_QRY)){
			st.setInt(1, id);
			int count = st.executeUpdate();
			System.out.println(count +" computer row(s) deleted.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Computer obj) {

		Computer computer = this.find(obj.getId());
		try (PreparedStatement st = connect.prepareStatement(UPDATE_QRY)){
			if(obj.getName() == "") {
				st.setString(1, computer.getName());
			} else {
				st.setString(1, obj.getName());
			}
			
			if(obj.getIntroDate() != null) {
				st.setDate(2, java.sql.Date.valueOf(obj.getIntroDate()));
			} else if(computer.getIntroDate() != null) {
				st.setDate(2, java.sql.Date.valueOf(computer.getIntroDate()));
			} else {
				st.setNull(2, java.sql.Types.DATE);
			}

			if(obj.getDiscDate() != null) {
				st.setDate(3, java.sql.Date.valueOf(obj.getDiscDate()));
			} else if(computer.getDiscDate() != null) {
				st.setDate(3, java.sql.Date.valueOf(computer.getDiscDate()));
			} else {
				st.setNull(3, java.sql.Types.DATE);
			}

			st.setInt(4, obj.getId());
			int count = st.executeUpdate();
			System.out.println(count +" computer row(s) updated.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Computer find(int id) {
		Computer computer = new Computer();      
		try (PreparedStatement st = connect.prepareStatement(FIND_QRY)){
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			if(result.next())
				computer = ComputerMapper.map(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	@Override
	public ArrayList<Computer> list() {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(LIST_QRY);
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public int getNumberRows() {
		int count = 0;
		try (Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(COUNT_QRY);
			if(result.next())
				count = result.getInt("var");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<Computer> listByPage(int offset, int rows) {
		ArrayList<Computer> list = new ArrayList<Computer>();
		try (PreparedStatement st = connect.prepareStatement(PAGINATED_LIST_QRY)){
			st.setInt(1, rows);
			st.setInt(2, offset);
		    ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(ComputerMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void closeConnection() throws SQLException {
		  this.connect.close();
	  }

}