package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityCatenaAlberghiera;
import exception.DAOException;
import exception.DBConnectionException;

public class CatenaAlberghieraDAO {

	public static void createCatenaAlberghiera(EntityCatenaAlberghiera eCA) throws DAOException,  DBConnectionException{
    
		try {	
			Connection conn = DBManager.getConnection();
			
			String query = "INSERT INTO CATENA_ALBERGHIERA VALUES (?,?);";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
			
				stmt.setInt(1, eCA.getCodice());
				stmt.setString(1, eCA.getNome());
			
				stmt.executeUpdate();
			
			}catch (SQLException e) {
				throw new DAOException("Errore nell'inserimento della catena alberghiera");
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	
	public static EntityCatenaAlberghiera readCatenaAlberghiera(int cod_catena) throws DAOException, DBConnectionException {

		EntityCatenaAlberghiera eCA = null;

		try {

			Connection conn = DBManager.getConnection();
			
			String query = "SELECT * FROM ALBERGO WHERE COD_ALBERGO=?;";

			try {

				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, cod_catena);
				
				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eCA = new EntityCatenaAlberghiera(cod_catena , result.getString(2));	
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura catena alberghiera");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return eCA;
	}
	
	public static void updateAlberghiera (EntityCatenaAlberghiera eCA) throws DAOException, DBConnectionException {
		
		int id = eCA.getCodice();
		
		if(id != 0){
		try {	
			Connection c = DBManager.getConnection();
		try {
			PreparedStatement stmt = c.prepareStatement("UPDATE ALBERGO SET NOME=?;");
			stmt.setString(1, eCA.getNome());
			stmt.executeUpdate();
		}catch(SQLException e) {
				throw new DAOException("Errore lettura catena alberghiera");
			}finally {
				DBManager.closeConnection();
			}
		}
		catch(SQLException e) {
			throw new DBConnectionException(" ");
		}
		}
	}
	
	
	
	public static void deleteAlbergo (int cod_catena) throws DAOException {
			
			try {
				Connection conn = DBManager.getConnection();
			
				String sqlquery = "DELETE FROM CATENA_ALBERGHIERA WHERE COD_CATENA=?;";
			
				PreparedStatement stmt = conn.prepareStatement(sqlquery);
				stmt.setInt(1, cod_catena);
				
				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new DAOException("Errore nell'eliminazione della catena");
			}
		
		}
	
}