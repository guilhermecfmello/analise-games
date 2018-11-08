package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T>{
	protected Connection connection;
	
	DAO(Connection connection){
		this.connection = connection;
	}
	
	public abstract void create(T t) throws SQLException;
	public abstract T read(Integer id) throws SQLException;
	public abstract void update(T t) throws SQLException;
	public abstract void delete(Integer id) throws SQLException;
	
	public abstract List<T> all() throws SQLException;



}