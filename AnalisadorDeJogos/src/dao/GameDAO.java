package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Game;

public class GameDAO extends DAO<Game>{

	private static final String CREATE_QUERY = 
			"INSERT INTO game values("
			+ "?,?,?,?,?,?);";
	
	private static final String READ_QUERY =
			"SELECT * from game WHERE name_game = ? "
			+ "AND site_sold = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE game SET "
			+ "date_release = ?, price = ?, name_dev = ?,"
			+ "name_pub = ? WHERE name_game = ? AND site_sold = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM game WHERE name_game = ? AND site_sold = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from game ORDER BY name_game;";
	
	
	GameDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(Game game) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
			statement.setString(1, game.getName());
			statement.setInt(2, game.getSite());
			statement.setDate(3, (java.sql.Date) game.getDate_release());
			statement.setDouble(4, game.getPrice());
			statement.setString(5, game.getDeveloper());
			statement.setString(6, game.getPublisher());
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
		
	}

	
	public Game read(String name, Integer site) throws SQLException {
		Game game = new Game();
		
		try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)){
			statement.setString(1, name);
			statement.setInt(2, site);
			
			statement.executeQuery();
			
			try(ResultSet result = statement.executeQuery()){
				if(result.next()) {
					game.setName(result.getString("name_game"));
					game.setSite(result.getInt("site_sold"));
					game.setDate_release(result.getDate("date_release"));
					game.setPrice(result.getDouble("price"));
					game.setDeveloper(result.getString("name_dev"));
					game.setPublisher(result.getString("name_pub"));
				} else {
					throw new SQLException("Erro: Usuario nao encontrado no banco de dados.");
				}
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
		
		return game;
	}

	@Override
	public void update(Game game) throws SQLException {
		
		
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)){
			statement.setDate(1, game.getDate_release());
			statement.setDouble(2, game.getPrice());
			statement.setString(3, game.getDeveloper());
			statement.setString(4, game.getPublisher());
			statement.setString(5, game.getName());
			statement.setInt(6, game.getSite());
			
			if( statement.executeUpdate() < 1) {
				throw new SQLException("Erro na atualização: Jogo não encontrado");
			} 
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			
		}
	}

	public void delete(String name, Integer site) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)){
			statement.setString(1, name);
			statement.setInt(2, site);
			
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Jogo não encontrado");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
			
		}
	}

	@Override
	public List<Game> all() throws SQLException {
		List<Game> gameList = new ArrayList<Game>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
			ResultSet result = statement.executeQuery()){
			while(result.next()) {
				Game game = new Game();
				game.setName(result.getString("name_game"));
				game.setSite(result.getInt("site_sold"));
				game.setDate_release(result.getDate("date_release"));
				game.setPrice(result.getDouble("price"));
				game.setDeveloper(result.getString("name_dev"));
				game.setPublisher(result.getString("name_pub"));
				//System.out.println("Jogo pego: " + game.getName());
				
				gameList.add(game);
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
			
			throw new SQLException("Erro ao listar Jogos");
		}
		return gameList;
	}


	@Override
	public Game read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	
}
