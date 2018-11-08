package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Developer;
import model.GameHasCat;

public class GameHasCatDAO extends DAO<GameHasCat>{

	private static final String CREATE_QUERY = 
			"INSERT INTO game_has_category "
			+ "(name_cat, name_game, site_game)"
			+ "values(?,?,?);";
	
	private static final String READ_QUERY =
			"SELECT * from game_has_category "
			+ "WHERE name_cat = ? AND name_game = ? "
			+ "AND site_game = ?;";
	
	private static final String UPDATE_QUERY =
			"UPDATE game_has_category SET "
			+ "name_dev = ? WHERE name_dev = ?;";
	
	private static final String DELETE_QUERY =
			"DELETE FROM game_has_category WHERE "
			+ "name_cat = ? AND name_game = ? AND site_game = ?;";
	
	private static final String ALL_QUERY = 
			"SELECT * from game_has_category "
			+ "ORDER BY name_game;";
	
	
	
	GameHasCatDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(GameHasCat t) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);){
			
			statement.setString(1, t.getCatName());
			statement.setString(2, t.getGameName());
			statement.setInt(3, t.getGameSite());
			
			
			statement.executeQuery();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		
		}
	}

	
	public GameHasCat read(String catName, String gameName, Integer gameSite) throws SQLException {
		GameHasCat gameHasCat = null;
		try(PreparedStatement statement = connection.prepareStatement(READ_QUERY);){
			statement.setString(1, catName);
			statement.setString(2, gameName);
			statement.setInt(3, gameSite);
			
			statement.executeQuery();
			try(ResultSet result = statement.executeQuery()){
				gameHasCat = new GameHasCat();
				if(result.next()) {
					gameHasCat.setCatName(result.getString("name_cat"));
					gameHasCat.setGameName(result.getString("name_game"));
					gameHasCat.setGameSite(result.getInt("site_game"));
				}
				else
					throw new SQLException("Relacao Jogo/Categoria nao encontrada.");
				
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return gameHasCat;
	}

	@Override
	public void update(GameHasCat t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(String nameCat, String nameGame, Integer siteGame) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);){
			statement.setString(1, nameCat);
			statement.setString(2, nameGame);
			statement.setInt(3, siteGame);
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao excluir: Relacao Jogo/Categoria nao encontrada");
			}
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public List<GameHasCat> all() throws SQLException {
		// TODO Auto-generated method stub
		List<GameHasCat> list = new ArrayList<GameHasCat>();
		try(PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
				ResultSet result = statement.executeQuery()){
				while(result.next()) {
					GameHasCat game = new GameHasCat();
					game.setCatName(result.getString("name_cat"));
					game.setGameName(result.getString("name_game"));
					game.setGameSite(result.getInt("site_game"));
					
					list.add(game);
				}
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				
				throw new SQLException("Erro ao listar Desemvolvedoras");
			}
		return list;
	}

	@Override
	public GameHasCat read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
