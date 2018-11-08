package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DevHasCat;
import model.Developer;

public class DevHasCatDAO extends DAO<DevHasCat>{
	
	private static final String CREATE_QUERY = 
			"INSERT INTO developer_has_category "
			+ "(name_cat, name_dev)"
			+ "values(?,?);";
	
	private static final String READ_QUERY =
			"SELECT * from developer_has_category WHERE "
			+ "name_dev = ? AND name_cat = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE developer_has_category SET "
			+ "name_dev = ? WHERE name_cat = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM developer_has_category WHERE "
			+ "name_dev = ? AND name_cat = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from developer_has_category ORDER BY name_dev;";
	
	
	
	
	DevHasCatDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(DevHasCat t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
			
			statement.setString(1, t.getCatName());
			statement.setString(2, t.getDevName());
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
	}

	
	public DevHasCat read(String catName, String devName) throws SQLException {
		DevHasCat dev = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, devName);
			statement.setString(2, catName);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				dev = new DevHasCat();
				if(result.next()) {
					dev.setDevName(result.getString("name_dev"));
					dev.setCatName(result.getString("name_cat"));
					
				}
				else
					throw new SQLException("Relação Dev/Cat nao encontrada.");
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return dev;
	}

	@Override
	public void update(DevHasCat t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String nameDev, String nameCat) throws SQLException {
		
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, nameDev);
			statement.setString(2, nameCat);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Categoria nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public List<DevHasCat> all() throws SQLException {
		List<DevHasCat> list = new ArrayList<DevHasCat>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					DevHasCat dev = new DevHasCat();
					dev.setCatName(result.getString("name_cat"));
					dev.setDevName(result.getString("name_dev"));
					list.add(dev);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Desemvolvedoras");
			}
		
		return list;
	}

	@Override
	public DevHasCat read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
