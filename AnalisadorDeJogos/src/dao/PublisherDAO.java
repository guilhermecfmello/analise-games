package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Publisher;

public class PublisherDAO extends DAO<Publisher>{

	private static final String CREATE_QUERY = 
			"INSERT INTO publisher "
			+ "values(?);";
	
	private static final String READ_QUERY =
			"SELECT * from publisher WHERE name_pub = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE publisher SET "
			+ "name_pub = ? WHERE name_pub = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM publisher WHERE name_pub = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from publisher ORDER BY name_pub;";

	
	PublisherDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Publisher t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
			
			statement.setString(1, t.getName());
			
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
		
	}

	
	public Publisher read(String name) throws SQLException {
		Publisher pub = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, name);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					pub = new Publisher();
					pub.setName(result.getString("name_pub"));
				}
				else
					throw new SQLException("Publicadora não encontrada");
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return pub;
	}

	@Override
	public void update(Publisher t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String name) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, name);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Publicadora nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public List<Publisher> all() throws SQLException {
		List<Publisher> pubList = new ArrayList<Publisher>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					Publisher pub = new Publisher();
					pub.setName(result.getString("name_pub"));
					
					pubList.add(pub);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Publicadoras");
			}
		return pubList;
	}

	@Override
	public Publisher read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
