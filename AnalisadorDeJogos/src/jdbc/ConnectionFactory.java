package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.omg.CORBA.portable.InputStream;


public class ConnectionFactory {
	
	private static ConnectionFactory instance = null;
	
	private String dbHost;
	private String dbPort;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private static String status = "Não conectou...";
	
	public void readProperties() throws IOException{
		Properties properties = new Properties();
		try {
			String path = "jdbc/datasource.properties";
			java.io.InputStream input = this.getClass().getClassLoader().getResourceAsStream(path);
			properties.load(input);
			
			this.dbHost = properties.getProperty("host");
			this.dbPort = properties.getProperty("port");
			this.dbName = properties.getProperty("name");
			this.dbUser = properties.getProperty("user");
			this.dbPassword = properties.getProperty("password");
			
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			
			throw new IOException("Erro ao obter informações do banco de dados através do arquivo de configuração.");
		}
	}
	
	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public static String getStatus() {
		return status;
	}

	public static void setStatus(String status) {
		ConnectionFactory.status = status;
	}

	public static void setInstance(ConnectionFactory instance) {
		ConnectionFactory.instance = instance;
	}

	public static ConnectionFactory getInstance() {
		if (instance == null)
			instance = new ConnectionFactory();
		
		return instance;
	}
	
	public java.sql.Connection getConnection() throws IOException, ClassNotFoundException, SQLException{


		
		Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            readProperties();

            String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());

            throw new ClassNotFoundException("Erro de conexão ao banco de dados.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro de conexão ao banco de dados.");
        }

        return connection;
		

	}
	
	
	public boolean closeConnection() throws IOException, ClassNotFoundException {
		try {
			this.getConnection().close();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
}

