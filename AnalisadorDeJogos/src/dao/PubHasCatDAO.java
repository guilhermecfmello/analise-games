package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Developer;
import model.PubHasCat;

public class PubHasCatDAO extends DAO<PubHasCat>{

	private static final String CREATE_QUERY = 
			"INSERT INTO publisher_has_category "
			+ "(name_cat, name_pub)"
			+ "values(?,?);";
	
	private static final String READ_QUERY =
			"SELECT * from publisher_has_category WHERE "
			+ "name_cat = ? AND name_pub = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE publisher_has_category SET "
			+ "name_dev = ? WHERE name_dev = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM publisher_has_category WHERE "
			+ "name_cat = ? AND name_pub = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from publisher_has_category "
			+ "ORDER BY name_pub;";
	
	
	PubHasCatDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(PubHasCat t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
			
			statement.setString(1, t.getCatName());
			statement.setString(2, t.getPubName());
						
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
	}

	
	public PubHasCat read(String nameCat, String namePub) throws SQLException {
		PubHasCat pubHasCat = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, nameCat);
			statement.setString(2, namePub);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				pubHasCat = new PubHasCat();
				if(result.next()) {
					pubHasCat.setCatName(result.getString("name_cat"));
					pubHasCat.setPubName(result.getString("name_pub"));	
				}
				else
					throw new SQLException("Desenvolvedora nao encontrada.");
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return pubHasCat;
	}

	@Override
	public void update(PubHasCat t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String nameCat, String namePub) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, nameCat);
			statement.setString(2, namePub);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Relação Categoria/Publicadora nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@Override
	public List<PubHasCat> all() throws SQLException {
		List<PubHasCat> list = new ArrayList<PubHasCat>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					PubHasCat pubHasCat = new PubHasCat();
					pubHasCat.setCatName(result.getString("name_cat"));
					pubHasCat.setPubName(result.getString("name_pub"));
					
					list.add(pubHasCat);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Relação Publicadora/Categoria");
			}
		return list;
	}

	@Override
	public PubHasCat read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
