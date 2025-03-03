package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.EntityCamera;
import exception.DAOException;
import exception.DBConnectionException;

public class CameraDAO{

	public static void createCamera(EntityCamera eC) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO CAMERA VALUES (?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, eC.get_num_camera());
				stmt.setInt(2, eC.get_tipologia());
				stmt.setFloat(3, eC.get_prezzoPerNotte());
				stmt.setInt(4, eC.getCod_albergo());
				

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura camera");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

	}


	public static EntityCamera readCamera(int num_camera, int cod_albergo) throws DAOException, DBConnectionException {
	
		EntityCamera eC = null;
	
			try {
	
			Connection conn = DBManager.getConnection();
	
			try {
				String query = "SELECT * FROM CAMERA WHERE NUM_CAMERA=? AND COD_ALBERGO=?;";
	
	
				PreparedStatement stmt = conn.prepareStatement(query);
	
				stmt.setInt(1, num_camera);
				stmt.setInt(2, cod_albergo);
				
				ResultSet result = stmt.executeQuery();
	
				if(result.next()) {
					eC = new EntityCamera(num_camera,result.getInt(2),result.getFloat(3),result.getInt(4));	
				}
	
			}catch(SQLException e) {
				throw new DAOException("Errore lettura camera");
				}finally {
					DBManager.closeConnection();
				}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}
	
		return eC;
}

	public static void updateCamera(EntityCamera eC) throws DAOException, DBConnectionException {
		
		int id = eC.get_num_camera();
		
		if(id != 0){
		try {	
			Connection c = DBManager.getConnection();
		try {
			PreparedStatement stmt = c.prepareStatement("UPDATE CAMERA SET TIPOLOGIA=? ,PREZZOPERNOTTE=? WHERE NUM_CAMERA=? AND COD_ALBERGO=?;");
			stmt.setInt(3, id);
			stmt.setInt(1, eC.get_tipologia());
			stmt.setFloat(2, eC.get_prezzoPerNotte());
			stmt.setInt(4, eC.getCod_albergo());
			stmt.executeUpdate();
		}catch(SQLException e) {
				throw new DAOException("Errore update camera");
			}finally {
				DBManager.closeConnection();
			}
		}
		catch(SQLException e) {
			throw new DBConnectionException(" ");
		}
		}
	}
	
	public static void deleteCamera (int num_camera,int cod_albergo) throws DAOException {
		
		try {
			Connection conn = DBManager.getConnection();
		
			String sqlquery = "DELETE FROM ALBERGO WHERE NUM_CAMERA=? AND COD_ALBERGO=?;";
		
			PreparedStatement stmt = conn.prepareStatement(sqlquery);
			stmt.setInt(1, num_camera);
			stmt.setInt(2, cod_albergo);
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Errore nell'eliminazione della camera");
		}
	
	}
	
	
	public static ArrayList<EntityCamera> lettura_camere (int cod_albergo, int tipologia) throws DAOException {
		
		ArrayList<EntityCamera> ec= null;
		EntityCamera e=null;
		try {
			Connection conn = DBManager.getConnection();
		
			String query = "SELECT * FROM CAMERA WHERE COD_ALBERGO=? AND TIPOLOGIA=?;";
		
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, cod_albergo);
			pstmt.setInt(2, tipologia);
			ResultSet result = pstmt.executeQuery();
			if(result!=null) {
				ec=new ArrayList<EntityCamera>();
			}
			while(result.next()) {
				e = new EntityCamera(result.getInt(1),result.getInt(2),result.getFloat(3),result.getInt(4));	
				ec.add(e);
			}
				
		
		} catch (SQLException ex) {
			throw new DAOException("Errore nella ricerca delle camere");
		}	
		
		return ec;
	
}
}


	
	



