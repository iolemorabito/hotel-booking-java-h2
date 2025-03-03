package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityCliente;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteDAO {
	


public static void createCliente(EntityCliente eC) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();
			
			String query = "INSERT INTO CLIENTE  VALUES (?,?,?,?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(2, eC.getNome());
				stmt.setString(3, eC.getCognome());
				stmt.setString(1, eC.getEmail());
				stmt.setString(4, eC.getIndirizzo());
				stmt.setInt(7, eC.getCivico());
				stmt.setString(6, eC.getNum_tel());
				stmt.setString(5, eC.getCarta_dicredito());
			
				stmt.executeUpdate();
			} 
			catch(SQLException e) {
				throw new DAOException("Errore scrittura cliente");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		
	}
	
	public static EntityCliente readCliente(String email) throws DAOException, DBConnectionException {
		EntityCliente eC = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTE WHERE EMAIL_CLIENTE=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, email);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eC = new EntityCliente( result.getString(1), result.getString(2), email, result.getString(4),result.getInt(5),result.getString(6),result.getString(7));	
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Cliente");
			} finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}



		return eC;

	}
	
	public static void updateCliente(EntityCliente eC) throws DAOException {   
	
		
        String email=eC.getEmail();

       
        if(email != null){
        	try {
            Connection c = DBManager.getConnection();
            PreparedStatement stmt = c.prepareStatement("UPDATE CLIENTE WHERE EMAIL=? SET NOME=?, COGNOME=?, INDIRIZZO=?,CIVICO=?, NUM_TEL=?, NUM_CARTA=?;");
                stmt.setString(1,eC.getNome());
                stmt.setString(2,eC.getCognome());
                stmt.setString(4, eC.getIndirizzo());
                stmt.setInt(5, eC.getCivico());
                stmt.setString(6,eC.getNum_tel());
                stmt.setNString(7, eC.getCarta_dicredito());
                stmt.executeUpdate();
            stmt.close();
            c.close();
        } catch (SQLException e) {
        throw new DAOException("Errore nell'aggiornamento del cliente");
        }}
    }
	
public static void delete (String email) throws DAOException, DBConnectionException {
		
		try {
			Connection conn = DBManager.getConnection();
		
			String sqlquery = "DELETE FROM CLIENTE WHERE EMAIL_CLIENTE=?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlquery);
			stmt.setString(1, email);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Errore nell'eliminazione del cliente");
		} finally {
			DBManager.closeConnection();
		}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}

public static boolean query_email (String email) throws DAOException, DBConnectionException {
	
	boolean result;
	
	try {
		Connection conn = DBManager.getConnection();
	
		String query = "SELECT * FROM CLIENTE WHERE EMAIL_CLIENTE=?;";
	try {
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, email);
	
		ResultSet rs = pstmt.executeQuery(); 
	
		
		if(rs.next())
			result = true; //esiste
		else
			result = false; //non esiste 
		} 
	
	catch(SQLException e) {
		throw new DAOException("Errore scrittura cliente");
	} finally {
		DBManager.closeConnection();
	}

}catch(SQLException e) {
	throw new DBConnectionException("Errore connessione database");
}
	return result;
	
}



}