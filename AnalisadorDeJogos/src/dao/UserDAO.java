package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO extends DAO<User>{


	private static final String CREATE_QUERY
		= "INSERT INTO login_user"
		+ "(name, login, password, date_of_birth) "
		+ "values (?, ?, md5(?), ?) RETURNING id";
		
	private static final String READ_QUERY
		= "SELECT name, login, date_of_birth from login_user "
				+ "WHERE id = ?;";
	
	private static final String UPDATE_QUERY
		= "UPDATE login_user SET name = ?, login = ?,"
				+ "date_of_birth = ? WHERE id = ?;";
	
	private static final String DELETE_QUERY
		= "DELETE FROM login_user WHERE id = ?;";
	
	private static final String UPDATE_WITH_PASSWORD_QUERY
		= "UPDATE login_user SET name = ?, login = ?,"
				+ "date_of_birth = ? password = md5(?) "
				+ "WHERE id = ?;";
	
	private static final String ALL_QUERY
		= "SELECT id, name, login, date_of_birth "
				+ "FROM login_user ORDER BY id";
	
	private static final String AUTHENTICATE_QUERY
		= "SELECT id, name, login, date_of_birth "
				+ "FROM login_user WHERE login = ? AND "
				+ "password = md5(?);";
	
	UserDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create(User user) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)){
			statement.setString(1, user.getName());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());
			statement.setDate(4, user.getDate_of_birth());
			
			try(ResultSet result = statement.executeQuery();){
				if(result.next()) {
					user.setId(result.getInt("id"));
				}
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@Override
	public User read(Integer id) throws SQLException {
		User user = new User();
		try (PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setInt(1, id);
			
			try(ResultSet result = statement.executeQuery();){
				if(result.next()) {
					user.setId(id);
					user.setName(result.getString("name"));
					user.setLogin(result.getString("login"));
					user.setDate_of_birth(result.getDate("date_of_birth"));
					
				} else {
					throw new SQLException("Erro ao visualizar: Usuário não encontrado");
				}
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		String query;
		
		if(user.getPassword() == null) {
			query = UPDATE_QUERY;
		} else {
			query = UPDATE_WITH_PASSWORD_QUERY;
		}
		
		try (PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, user.getName());
			statement.setString(2, user.getLogin());
			statement.setDate(3, user.getDate_of_birth());
			 if(user.getPassword() == null) {
				 statement.setInt(4, user.getId());
			 } else {
				 statement.setString(4, user.getPassword());
				 statement.setInt(5, user.getId());
			 }
			  if(statement.executeUpdate() < 1) {
				  throw new SQLException("Erro ao editar, usuário não encontrado.");
			  }
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)){
			statement.setInt(1, id);
			
			if(statement.executeUpdate() < 1){
				throw new SQLException("Erro ao excluir: usuário não encontrado.");
			}
		} catch ( SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public List<User> all() throws SQLException {
		List<User> userList = new ArrayList<User>();
		
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
			ResultSet result = statement.executeQuery()){
			while(result.next()) {
				User user = new User();
				user.setName(result.getString("name"));
				user.setLogin(result.getString("login"));
				user.setId(result.getInt("id"));
				user.setDate_of_birth(result.getDate("date_of_birth"));
				
				userList.add(user);
			}
			
			
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
			
			throw new SQLException("Erro ao listar usuários.");
		}
			
		return userList;
	}
	
	public void authenticate(User user) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY);){
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					user.setId(result.getInt("id"));
					user.setName(result.getString("name"));
					user.setDate_of_birth(result.getDate("date_of_birth"));
					System.out.println("Logado com sucesso!");
				} else {
					throw new SecurityException("Login ou senha incorretos.");
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao autenticar o usuário.");
			}
		}
	}

}

























