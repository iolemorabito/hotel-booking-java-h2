package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.EntityAlbergo;
import exception.DAOException;
import exception.DBConnectionException;

public class AlbergoDAO {

	public static void createAlbergo(EntityAlbergo eA) throws DAOException,  DBConnectionException{
    
		try {	
			Connection conn = DBManager.getConnection();
			
			String query = "INSERT INTO ALBERGO VALUES (?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
			
				stmt.setInt(1, eA.getCodAlbergo());
				stmt.setString(2, eA.getNome());
				stmt.setString(3, eA.getIndirizzo());
				stmt.setInt(4, eA.getCivico());
				stmt.setString(5, eA.getCitta());
				stmt.setInt(6, eA.getCap());
				stmt.setString(7, eA.getNum_tel());
				stmt.setInt(8, eA.getCod_catena());
				stmt.executeUpdate();
			
			}catch (SQLException e) {
				throw new DAOException("Errore nell'inserimento dell'albergo");
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	
	public static EntityAlbergo readAlbergo(String citta) throws DAOException, DBConnectionException {

		EntityAlbergo eA = null;

		try {

			Connection conn = DBManager.getConnection();
			
			String query = "SELECT * FROM ALBERGO WHERE CITTA=?;";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, citta);
				
				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					
					eA = new EntityAlbergo(result.getInt(1) , result.getString(2), result.getString(3),result.getInt(4),  citta, result.getInt(6),result.getString(7),result.getInt(8));	
				
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura albergo");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return eA;
	}
	
	public static void updateAlbergo (EntityAlbergo eA) throws DAOException, DBConnectionException {
		
		int id = eA.getCodAlbergo();
		
		if(id != 0){
		try {	
			Connection c = DBManager.getConnection();
		try {
			PreparedStatement stmt = c.prepareStatement("UPDATE ALBERGO SET NOME=?, INDIRIZZO=?, CITTA=?, CAP=?, NUM_TEL=?, CIVICO=? WHERE COD_ALBERGO=? AND COD_CATENA=?;");
			stmt.setString(1, eA.getNome());
			stmt.setString(2, eA.getIndirizzo());
			stmt.setInt(6, eA.getCivico());
			stmt.setString(3, eA.getCitta());
			stmt.setInt(4, eA.getCap());
			stmt.setString(5, eA.getNum_tel());
			stmt.setInt(7, id);
			stmt.setInt(8, eA.getCod_catena());
			stmt.executeUpdate();
		}catch(SQLException e) {
				throw new DAOException("Errore update albergo");
			}finally {
				DBManager.closeConnection();
			}
		}
		catch(SQLException e) {
			throw new DBConnectionException(" ");
		}
		}
	}
	
	
	
	public static void deleteAlbergo (int cod_albergo) throws DAOException {
			
			try {
				Connection conn = DBManager.getConnection();
			
				String sqlquery = "DELETE FROM ALBERGO WHERE COD_ALBERGO=?;";
			
				PreparedStatement stmt = conn.prepareStatement(sqlquery);
				stmt.setInt(1, cod_albergo);
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Errore nell'eliminazione dell'albergo");
			}
		
		}
	
	
	
}