package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.EntityPrenotazione;
import exception.DAOException;
import exception.DBConnectionException;

public class PrenotazioneDAO {
	


public static void createPrenotazione(EntityPrenotazione eP) throws DAOException, DBConnectionException {
	
		try {
			
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO PRENOTAZIONE VALUES (?,?,?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setInt(1,eP.getId_prenotazione());
				stmt.setDate(2, (Date) eP.getData_arrivo());
				stmt.setDate(3, (Date) eP.getData_partenza());
				stmt.setString(4,eP.getEmail());
				stmt.setInt(5,eP.getNumcamera());
				stmt.setInt(6, eP.getCod_albergo());
				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura prenotazione");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

	}
	
	public static EntityPrenotazione readPrenotazione(int id_prenotazione) throws DAOException, DBConnectionException {
		EntityPrenotazione eP = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM PRENOTAZIONE WHERE ID_PRENOTAZIONE=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, id_prenotazione);

				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eP = new EntityPrenotazione( id_prenotazione, result.getDate(2), result.getDate(3),result.getInt(5),result.getString(4),result.getInt(6));	
				}
			}catch(SQLException e) {
				throw new DAOException("Id Prenotazione non valido");
			} finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}



		return eP;

	}
	
	public static void update(EntityPrenotazione eP) throws SQLException, DAOException {   
       int id_prenotazione=eP.getId_prenotazione();

       
        if (id_prenotazione != 0){
        	try {
            Connection c = DBManager.getConnection();
            PreparedStatement stmt = c.prepareStatement("UPDATE PRENOTAZIONE SET DATA_ARRIVO=?, DATA_PARTENZA=? WHERE ID_PRENOTAZIONE=?;");
                
                stmt.setDate(1, (Date) eP.getData_arrivo());
                stmt.setDate(2, (Date) eP.getData_partenza());
                stmt.setInt(3, id_prenotazione);
                stmt.executeUpdate();
            stmt.close();
            c.close();
        		}catch (SQLException e) {
        			throw new DAOException("Errore nell'aggiornamento della prenotazione");
        		}
        }
        	
    }
	
public static void delete (int id_prenotazione) throws DAOException {
		
	try {
			Connection conn = DBManager.getConnection();
		
			String sqlquery = "DELETE FROM PRENOTAZIONE WHERE ID_PRENOTAZIONE=?;";
		
			PreparedStatement stmt = conn.prepareStatement(sqlquery);
			stmt.setInt(1, id_prenotazione);
			
			stmt.executeUpdate();
		
	
	} catch (SQLException e) {
		throw new DAOException("Errore nell'eliminazione del cliente");
	}

}




}
