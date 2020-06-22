package com.excilys.formation.cdb.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.cdb.mapper.CompanyMapper;
import com.excilys.formation.cdb.pojos.Company;

public class CompanyDAO extends DAO<Company> {
	
	private static final String CREATE_QRY = "insert into company value (?,?)";
	private static final String DELETE_QRY = "delete from company where id = (?)";
	private static final String UPDATE_QRY  = "update company set name = ? where id = ?";
	private static final String FIND_QRY = "SELECT id, name FROM company WHERE id = ?"; 
	private static final String LIST_QRY = "SELECT id, name FROM company"; 
	private static final String COUNT_QRY = "SELECT COUNT(*) as var FROM company";
	private static final String PAGINATED_LIST_QRY =  "SELECT id, name FROM computer LIMIT ? OFFSET ? ";
	
	public CompanyDAO(Connection conn) {
		super(conn);
	}

	public boolean create(Company obj) {
		try(PreparedStatement st = connect.prepareStatement(CREATE_QRY)){
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			int count = st.executeUpdate();
			System.out.println(count +" company row(s) created.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		try(PreparedStatement st = connect.prepareStatement(DELETE_QRY)){
			st.setInt(1,id);
			int count = st.executeUpdate();
			System.out.println(count +" company row(s) deleted.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Company obj) {
		Company company = this.find(obj.getId());
		try(PreparedStatement st = connect.prepareStatement(UPDATE_QRY)){
			if(obj.getName() == "") {
				st.setString(1, company.getName());
			} else {
				st.setString(1, obj.getName());
			}

			st.setInt(2, obj.getId());
			int count = st.executeUpdate();
			System.out.println(count +" company row(s) updated.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Company find(int id) {
		Company company = new Company();      
		try(Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(FIND_QRY);
			company = CompanyMapper.map(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public ArrayList<Company> list() {
		ArrayList<Company> list = new ArrayList<Company>();
		try(Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(LIST_QRY);
			while(result.next()) {
				list.add(CompanyMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<Company> listByPage(int offset, int rows) {
		ArrayList<Company> list = new ArrayList<Company>();
		try(PreparedStatement st = connect.prepareStatement(PAGINATED_LIST_QRY)){
			st.setInt(1, rows);
			st.setInt(2, offset);
		    ResultSet result = st.executeQuery();
			while(result.next()) {
				list.add(CompanyMapper.map(result));
			}    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getNumberRows() {
		int count = 0;
		try(Statement st = connect.createStatement()){
			ResultSet result = st.executeQuery(COUNT_QRY);
			if(result.next())
				count = result.getInt("var");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void closeConnection() throws SQLException {
		  this.connect.close();
	}
}