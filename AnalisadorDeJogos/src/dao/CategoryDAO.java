package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Game;

public class CategoryDAO extends DAO<Category>{
	
	CategoryDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private static final String CREATE_QUERY = 
			"INSERT INTO category "
			+ "values(?);";
	
	private static final String READ_QUERY =
			"SELECT * from category WHERE name_cat = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE category SET "
			+ "name_cat = ? WHERE name_cat = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM category WHERE name_cat = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from category ORDER BY name_cat;";

	@Override
	public void create(Category t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
		
			statement.setString(1, t.getName());
			
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
	}

	
	public Category read(String name) throws SQLException {
		Category category = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, name);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					category = new Category();
					category.setName(result.getString("name_cat"));
				}
				else
					throw new SQLException("Categoria nao encontrada");
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return category;
	}

	@Override
	public void update(Category t) throws SQLException {
		
		
		
	}

	
	public void delete(String name) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, name);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Categoria nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@Override
	public List<Category> all() throws SQLException {
		List<Category> catList = new ArrayList<Category>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					Category cat = new Category();
					cat.setName(result.getString("name_cat"));
					
					catList.add(cat);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Categorias");
			}
		return catList;
	}

	@Override
	public Category read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	
}