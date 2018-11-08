package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DMF;
import model.Diagnosis;

public class DiagnosisDAO extends DAO<Diagnosis>{

	
	

	private static final String ALL_QUERY = 
			"SELECT site_sold,name_dev,COUNT(*)"
			+ " FROM game GROUP BY "
			+ "name_dev,site_sold order"
			+ " by count DESC; ";
	
	private static final String DEVELOPERS_MOST_FREQUENTLY_STEAM = 
			"SELECT * FROM get_developers_most_frequently_steam()"
			+ " AS f(site_sold smallint, name_dev varchar(50),"
			+ " count bigint);";

	public Diagnosis get_DMFSteam() {
		Diagnosis diag = new Diagnosis();
		ArrayList<DMF> list = new ArrayList<DMF>();
		try(PreparedStatement statement = connection.prepareStatement(DEVELOPERS_MOST_FREQUENTLY_STEAM);
			ResultSet result = statement.executeQuery()){
			while(result.next()) {
				DMF dmfSteam = new DMF();
				dmfSteam.setName_dev(result.getString("name_dev"));
				dmfSteam.setCount(result.getInt("count"));
				dmfSteam.setSite_sold(result.getInt("site_sold"));
				list.add(dmfSteam);
				
				
			}
			diag.setDMFSteamList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return diag;
		
	}
	
	
	DiagnosisDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create(Diagnosis t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Diagnosis read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Diagnosis t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Diagnosis> all() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
