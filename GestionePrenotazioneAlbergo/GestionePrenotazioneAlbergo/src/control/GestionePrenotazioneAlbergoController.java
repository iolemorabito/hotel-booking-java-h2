package control;

import java.sql.Date;
import java.util.ArrayList;
import database.AlbergoDAO;
import database.CameraDAO;
import database.ClienteDAO;
import database.OccupazioneDAO;
import database.PrenotazioneDAO;
import entity.EntityAlbergo;
import entity.EntityCamera;
import entity.EntityCliente;
import entity.EntityOccupazione;
import entity.EntityPrenotazione;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestionePrenotazioneAlbergoController{
	private static EntityAlbergo albergo=null;
	
	//pattern singleton
	private static GestionePrenotazioneAlbergoController gP = null;
	protected GestionePrenotazioneAlbergoController() {}
	public static GestionePrenotazioneAlbergoController getInstance() 
	{ 
		if (gP == null) 
			gP = new GestionePrenotazioneAlbergoController(); 

		return gP; 
	}
	
	
	
	public static ArrayList<String> ricercaDisponibilità(String citta, Date data_arrivo, Date data_partenza, int tipologia) throws  OperationException {
		ArrayList<EntityCamera> listaCamere = null;
		ArrayList<EntityCamera> listaCamereOccupate = null;
		ArrayList<String> risultati= new ArrayList<String>();
		
		try {
			//controllo esistenza prenotazione
			albergo = AlbergoDAO.readAlbergo(citta);

			if(albergo == null) {
				throw new OperationException("Albergo non trovato");
			}
			
			listaCamere=CameraDAO.lettura_camere(albergo.getCodAlbergo(),tipologia);
			
			listaCamereOccupate=OccupazioneDAO.lettura_occupazione(data_arrivo,data_partenza);
			
			for(int k=0;k<listaCamere.size();k++) {
				for(int j=0;j<listaCamereOccupate.size();j++) {
					if(listaCamere.get(k).confronto(listaCamereOccupate.get(j))) {
						listaCamere.remove(k);
					}
				}
			}
		       
		    for(int i=0;i<listaCamere.size();i++)
		    	{
	            risultati.add(String.valueOf(listaCamere.get(i).get_num_camera()));
	            risultati.add(String.valueOf(listaCamere.get(i).get_tipologia()));
	            risultati.add(String.valueOf(listaCamere.get(i).get_prezzoPerNotte()));
	            }
			
		}catch(DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa è andato storto...ricerca annullata");
		}
		
		return risultati;
	}
	
	
	public static int prenotazione(Date data_arrivo,Date data_partenza, String email, int num_camera) throws OperationException{
		int ok=0;
		int min = 10000;
        int max = 99999;
        int id = (int) Math.floor(Math.random()*(max-min+1)+min);
		EntityPrenotazione eP=null;
		EntityOccupazione occupazione=new EntityOccupazione();
        boolean compreso = true;
        Date data_incr=data_arrivo;
        
		try {
				
	            eP=new EntityPrenotazione(id, data_arrivo,data_partenza,num_camera, email, albergo.getCodAlbergo());
	                      
	            PrenotazioneDAO.createPrenotazione(eP);
	            
	            while(compreso){
	                if((data_incr.compareTo(data_arrivo)>=0)&&(data_incr.compareTo(data_partenza)<=0)){
	                occupazione.setCod_albergo(albergo.getCodAlbergo());
	                occupazione.setNumcamera(num_camera);
	                occupazione.setData(data_incr);
	                occupazione.setOccupato(0);
	                OccupazioneDAO.createOccupazione(occupazione);
	                data_incr=new Date(data_incr.getTime()+(1000 * 60 * 60 * 24));
	                }else compreso=false;
	            }
	            ok=1;
	            
		 }catch(DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa è andato storto...prenotazione annullata");
		}
		
		return ok;
	} 
	

	public int checkIn(int id_prenotazione) throws OperationException{
		int checkin=0;
		int num_camera=0;
		int cod_albergo=0;
		boolean compreso = true;
		EntityPrenotazione prenotazione=null;
		EntityOccupazione occupazione=null;
		
		try {
			//controllo esistenza prenotazione
			prenotazione = PrenotazioneDAO.readPrenotazione(id_prenotazione);
			
			if(prenotazione == null) {
				throw new OperationException("Prenotazione non trovata");
			}
			
			Date data_arrivo=(Date) prenotazione.getData_arrivo();
			Date data_incr=data_arrivo;
			Date data_partenza=(Date) prenotazione.getData_partenza();
			num_camera=prenotazione.getNumcamera();
			cod_albergo=prenotazione.getCod_albergo();
		
			while(compreso){
				if((data_incr.compareTo(data_arrivo)>=0)&&(data_incr.compareTo(data_partenza)<=0)){
					occupazione=OccupazioneDAO.readOccupazione(data_incr,num_camera,cod_albergo);
					occupazione.setOccupato(1);
					OccupazioneDAO.update(occupazione);
          			data_incr=new Date(data_incr.getTime()+(1000 * 60 * 60 * 24));
				}else compreso=false;                     
			}
			checkin=1;
			
		}catch(DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException ex) {
			throw new OperationException("Ops, qualcosa è andato storto...checkIn annullato");
		}
		
		return checkin;
	}

	
	
	
	public static int inserisciDati (String email, String nome,String cognome,String telefono,String indirizzo, int civico,String carta_dicredito) throws OperationException {
		int risultato=0;
		EntityCliente eC=null;
		
		try {
			
			if(!ClienteDAO.query_email(email)) {
				eC=new EntityCliente(nome,cognome,email,indirizzo,civico,telefono,carta_dicredito);
				ClienteDAO.createCliente(eC);
				risultato=1;
			}else risultato=-1;
				
		} catch (DAOException e) {
			throw new OperationException("Ops, qualcosa è andato storto...Inserimento dati annullato");
		} 
		
		catch (DBConnectionException e) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}
		
		return risultato;
	}
	
}