package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jdbc.ConnectionFactory;

public class DAOFactory implements AutoCloseable{

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	private Connection connection = null;

    public DAOFactory() throws ClassNotFoundException, IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
    }

    public void beginTransaction() throws SQLException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao abrir transação.");
        }
    }

    public void commitTransaction() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void rollbackTransaction() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao executar transação.");
        }
    }

    public void endTransaction() throws SQLException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao finalizar transação.");
        }
    }

    public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao fechar conexão ao banco de dados.");
        }
    }

    public UserDAO getUsuarioDAO() {
        return new UserDAO(connection);
    }
	 public GameDAO getGameDAO() {
		 return new GameDAO(connection);
	 }
	
	public CategoryDAO getCategoryDAO() {
		return new CategoryDAO(connection);
	}
	
	
	public DeveloperDAO getDeveloperDAO() {
		return new DeveloperDAO(connection);
	}
	
	public PublisherDAO getPublisherDAO() {
		return new PublisherDAO(connection);
	}

	public DevHasCatDAO getDevHasCatDAO() {
		return new DevHasCatDAO(connection);
	}
	
	public GameHasCatDAO getGameHasCatDAO() {
		return new GameHasCatDAO(connection);
	}
	
	public PubHasCatDAO getPubHasCatDAO() {
		return new PubHasCatDAO(connection);
	}
	
	public DiagnosisDAO getDiagnosisDAO() {
		return new DiagnosisDAO(connection);
	}
}
