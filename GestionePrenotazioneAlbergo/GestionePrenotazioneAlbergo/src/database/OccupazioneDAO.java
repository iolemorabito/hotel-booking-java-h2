package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.EntityCamera;
import entity.EntityOccupazione;
import exception.DAOException;
import exception.DBConnectionException;

public class OccupazioneDAO {
	


public static void createOccupazione(EntityOccupazione eO) throws DAOException, DBConnectionException {

		try {
			
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO OCCUPAZIONE VALUES (?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(3, eO.getNumcamera());
				stmt.setDate(1, (Date) eO.getData());
				stmt.setInt(2, eO.getOccupato());
				stmt.setInt(4, eO.getCod_albergo());
				
				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura occupazione");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

	}
	
	public static EntityOccupazione readOccupazione(Date data, int num_camera, int cod_albergo) throws DAOException, DBConnectionException {
		EntityOccupazione eO = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM OCCUPAZIONE WHERE DATA=? AND NUM_CAMERA=? AND COD_ALBERGO=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, data);
				stmt.setInt(2, num_camera);
				stmt.setInt(3, cod_albergo);
				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					eO = new EntityOccupazione( num_camera ,data, result.getInt(3), cod_albergo);	
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura occupazione");
			} finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}



		return eO;

	}
	
	public static EntityOccupazione update(EntityOccupazione eO) throws DAOException {   

       
        	try {
            Connection c = DBManager.getConnection();
            PreparedStatement stmt = c.prepareStatement("UPDATE OCCUPAZIONE SET OCCUPATO=1 WHERE NUM_CAMERA=? AND DATA=? AND COD_ALBERGO=?;");
            	stmt.setInt(1, eO.getNumcamera());
                stmt.setDate(2,(Date) eO.getData());
                stmt.setInt(3, eO.getCod_albergo());
                
                stmt.executeUpdate();
            stmt.close();
            c.close();
        } catch (SQLException e) {
        throw new DAOException("Errore nell'aggiornamento dell'occupazione");
        }
        return eO;
    }
	
public static void delete (int numcamera, Date data, int cod_albergo) throws DAOException {
		
		try {
			Connection conn = DBManager.getConnection();
		
			String sqlquery = "DELETE FROM OCCUPAZIONE WHERE NUM_CAMERA=? AND DATA=? AND COD_ALBERGO=?;";
		
			PreparedStatement stmt = conn.prepareStatement(sqlquery);
			stmt.setInt(1,numcamera);
			stmt.setDate(1, data);
			stmt.setInt(2, cod_albergo);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Errore nell'eliminazione dell'occupazione");
		}
	
	}


public static ArrayList<EntityCamera> lettura_occupazione (Date data_arrivo, Date data_partenza) throws DAOException {
	
	ArrayList<EntityCamera> ec = null;
	EntityCamera e=null;
	try {
		Connection conn = DBManager.getConnection();
		
		String query = "SELECT OCCUPAZIONE.NUM_CAMERA,CAMERA.TIPOLOGIA,CAMERA.PREZZOPERNOTTE,OCCUPAZIONE.COD_ALBERGO FROM CAMERA JOIN OCCUPAZIONE ON"
						+ "(OCCUPAZIONE.NUM_CAMERA=CAMERA.NUM_CAMERA AND OCCUPAZIONE.COD_ALBERGO=CAMERA.COD_ALBERGO AND OCCUPAZIONE.DATA BETWEEN ? AND ?)"
						+ "GROUP BY OCCUPAZIONE.NUM_CAMERA,CAMERA.TIPOLOGIA,CAMERA.PREZZOPERNOTTE,OCCUPAZIONE.COD_ALBERGO;";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setDate(1, data_arrivo);
		stmt.setDate(2, data_partenza);
		
	
		ResultSet result = stmt.executeQuery();
		if(result!=null) {
			ec=new ArrayList<EntityCamera>();
		}
		while(result.next()) {
			e = new EntityCamera(result.getInt(1),result.getInt(2),result.getFloat(3),result.getInt(4));	
			ec.add(e);
		}
			
	
	} catch (SQLException ex) {
		throw new DAOException("Errore nella ricerca del cliente");
	}	
	
	return ec;
}

}
