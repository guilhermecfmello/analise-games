package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Developer;

public class DeveloperDAO extends DAO<Developer>{

	private static final String CREATE_QUERY = 
			"INSERT INTO developer "
			+ "values(?);";
	
	private static final String READ_QUERY =
			"SELECT * from developer WHERE name_dev = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE developer SET "
			+ "name_dev = ? WHERE name_dev = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM developer WHERE name_dev = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from developer ORDER BY name_dev;";
	
	DeveloperDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Developer t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
		
			statement.setString(1, t.getName());
			
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
	}

	
	public Developer read(String name) throws SQLException {
		Developer dev = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, name);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				dev = new Developer();
				if(result.next())
					dev.setName(result.getString("name_dev"));
				else
					throw new SQLException("Desenvolvedora nao encontrada.");
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return dev;
	}

	@Override
	public void update(Developer t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String name) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, name);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Desenvolvedora nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@Override
	public List<Developer> all() throws SQLException {
		List<Developer> devList = new ArrayList<Developer>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					Developer dev = new Developer();
					dev.setName(result.getString("name_dev"));
					
					devList.add(dev);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Desemvolvedoras");
			}
		return devList;
	}

	@Override
	public Developer read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
