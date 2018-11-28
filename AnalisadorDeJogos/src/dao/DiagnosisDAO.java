package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CMF;
import model.DMF;
import model.Diagnosis;
import model.PriceYear;

public class DiagnosisDAO extends DAO<Diagnosis>{

	
	

	private static final String ALL_QUERY = 
			"SELECT site_sold,name_dev,COUNT(*)"
			+ " FROM game GROUP BY "
			+ "name_dev,site_sold order"
			+ " by count DESC; ";
	
	private static final String DEVELOPERS_MOST_FREQUENTLY_STEAM = 
			"SELECT * FROM get_developers_most_frequently_steam()"
			+ " AS f(site_sold smallint, name_dev varchar(50),"
			+ " count bigint) LIMIT 10;";
	
	private static final String DEVELOPERS_MOST_FREQUENTLY_NUUVEM = 
			"SELECT * FROM get_developers_most_frequently_nuuvem()"
			+ " AS f(site_sold smallint, name_dev varchar(50), "
			+ "count bigint) LIMIT 10;";
	
	private static final String DEVELOPERS_MOST_FREQUENTLY_GAMERSGATE =
			"SELECT * FROM get_developers_most_frequently_gamersgate()"
			+ " AS f(site_sold smallint, name_dev varchar(50), "
			+ "count bigint) LIMIT 11;";

	private static final String CATEGORY_MOST_FREQUENTLY_STEAM =
			"SELECT * FROM get_category_most_frequently_steam() "
			+ "AS f(name_cat varchar(50), count bigint) LIMIT 10;";
	
	private static final String CATEGORY_MOST_FREQUENTLY_NUUVEM =
			"SELECT * FROM get_category_most_frequently_nuuvem()"
			+ " AS f(name_cat varchar(50), count bigint) LIMIT 10;";
	
	private static final String CATEGORY_MOST_FREQUENTLY_GAMERSGATE =
			"SELECT * FROM get_category_most_frequently_gamersgate()"
			+ " AS f(name_cat varchar(50), count bigint) LIMIT 10;";
	
	private static final String AVG_PRICE_STEAM = 
			"SELECT AVG(price) from game where site_sold = 1;";
	
	private static final String AVG_PRICE_NUUVEM =
			"select AVG(price) from game where site_sold = 2;";
	
	private static final String AVG_PRICE_GAMERSGATE =
			"select AVG(price) from game where site_sold = 1;";
	
	private static final String AVG_PRICE_BY_YEAR_STEAM =
			"select foo.year, avg(foo.price) from (\n" + 
			"		select date_part('year',date_release) AS year, price from game WHERE site_sold = 1\n" + 
			"	) AS foo GROUP BY foo.year ORDER BY foo.year;";
	
	private static final String AVG_PRICE_BY_YEAR_NUUVEM =
			"select foo.year, avg(foo.price) from (\n" + 
			"	select date_part('year',date_release) AS year, price from game WHERE site_sold = 2\n" + 
			") AS foo GROUP BY foo.year ORDER BY foo.year;";
	
	private static final String AVG_PRICE_BY_YEAR_GAMERSGATE =
			"select foo.year, avg(foo.price) from (\n" + 
			"	select date_part('year',date_release) AS year, price from game WHERE site_sold = 3\n" + 
			") AS foo GROUP BY foo.year ORDER BY foo.year;";
	
	public ArrayList<PriceYear> getAVGPriceByYearGamersGate(){
		ArrayList<PriceYear> list = new ArrayList<PriceYear>();
		PriceYear py;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_BY_YEAR_GAMERSGATE);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				py = new PriceYear();
				py.setPrice(result.getDouble("avg"));
				py.setYear(result.getInt("year"));
				list.add(py);
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<PriceYear> getAVGPriceByYearNuuvem(){
		ArrayList<PriceYear> list = new ArrayList<PriceYear>();
		PriceYear py;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_BY_YEAR_NUUVEM);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				py = new PriceYear();
				py.setPrice(result.getDouble("avg"));
				py.setYear(result.getInt("year"));
				list.add(py);
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<PriceYear> getAVGPriceByYearSteam(){
		ArrayList<PriceYear> list = new ArrayList<PriceYear>();
		PriceYear py;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_BY_YEAR_STEAM);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				py = new PriceYear();
				py.setPrice(result.getDouble("avg"));
				py.setYear(result.getInt("year"));
				list.add(py);
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public double get_AVG_GamersGate() {
		double avg = -1;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_GAMERSGATE);
				ResultSet result = statement.executeQuery()){
			while(result.next())
				avg = result.getDouble("avg");
			
			return avg;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avg;
	}
	
	public double get_AVG_Nuuvem() {
		double avg = -1;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_NUUVEM);
				ResultSet result = statement.executeQuery()){
			while(result.next())
				avg = result.getDouble("avg");
			
			return avg;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avg;
	}
	
	public double get_AVG_Steam() {
		double avg = -1;
		try(PreparedStatement statement = connection.prepareStatement(AVG_PRICE_STEAM);
				ResultSet result = statement.executeQuery()){
			while(result.next())
				avg = result.getDouble("avg");
			
			return avg;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avg;
	}
	
	public void set_CMFGamersGate(Diagnosis diag) {
		ArrayList<CMF> list = new ArrayList<CMF>();
		try(PreparedStatement statement = connection.prepareStatement(CATEGORY_MOST_FREQUENTLY_GAMERSGATE);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				CMF cmf = new CMF();
				cmf.setName_cat(result.getString("name_cat"));
				cmf.setCount(result.getInt("count"));
				list.add(cmf);
			}
			diag.setCMFGamersGateList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void set_CMFNuuvem(Diagnosis diag) {
		ArrayList<CMF> list = new ArrayList<CMF>();
		try(PreparedStatement statement = connection.prepareStatement(CATEGORY_MOST_FREQUENTLY_NUUVEM);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				CMF cmf = new CMF();
				cmf.setName_cat(result.getString("name_cat"));
				cmf.setCount(result.getInt("count"));
				list.add(cmf);
			}
			diag.setCMFNuuvemList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void set_CMFSteam(Diagnosis diag) {
		ArrayList<CMF> list = new ArrayList<CMF>();
		try(PreparedStatement statement = connection.prepareStatement(CATEGORY_MOST_FREQUENTLY_STEAM);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				CMF cmf = new CMF();
				cmf.setName_cat(result.getString("name_cat"));
				cmf.setCount(result.getInt("count"));
				list.add(cmf);
			}
			diag.setCMFSteamList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void set_DMFGamersGate(Diagnosis diag) {
		ArrayList<DMF> list = new ArrayList<DMF>();
		try(PreparedStatement statement = connection.prepareStatement(DEVELOPERS_MOST_FREQUENTLY_GAMERSGATE);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				DMF dmf= new DMF();
				dmf.setName_dev(result.getString("name_dev"));
				dmf.setCount(result.getInt("count"));
				dmf.setSite_sold(result.getInt("site_sold"));
				list.add(dmf);
			}
			diag.setDMFGamersGateList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void set_DMFNuuvem(Diagnosis diag) {
		ArrayList<DMF> list = new ArrayList<DMF>();
		try(PreparedStatement statement = connection.prepareStatement(DEVELOPERS_MOST_FREQUENTLY_NUUVEM);
				ResultSet result = statement.executeQuery()){
			while(result.next()) {
				DMF dmfNuuvem = new DMF();
				dmfNuuvem.setName_dev(result.getString("name_dev"));
				dmfNuuvem.setCount(result.getInt("count"));
				dmfNuuvem.setSite_sold(result.getInt("site_sold"));
				list.add(dmfNuuvem);
			}
			diag.setDMFNuuvemList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void set_DMFSteam(Diagnosis diag) {
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
