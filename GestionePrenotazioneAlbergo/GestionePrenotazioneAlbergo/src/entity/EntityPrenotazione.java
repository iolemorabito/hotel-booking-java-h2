package entity;

import java.util.Date;

public class EntityPrenotazione {
	
	private int id_prenotazione;
	private Date data_arrivo;
	private Date data_partenza;
	private int numcamera;
	private String email;
	private int cod_albergo;

	public EntityPrenotazione() {
		this.id_prenotazione = 0;
		this.data_arrivo = null;
		this.data_partenza = null;
		this.numcamera=0;
		this.email=null;
		this.cod_albergo=0;
	}
	
	public EntityPrenotazione(int id_prenotazione, Date data_arrivo, Date data_partenza, int numcamera, String email,int cod_albergo) {
		this.id_prenotazione = id_prenotazione;
		this.data_arrivo = data_arrivo;
		this.data_partenza = data_partenza;
		this.numcamera = numcamera;
		this.email = email;
		this.cod_albergo = cod_albergo;
	}
	
	public int getId_prenotazione() {
		return id_prenotazione;
	}
	public void setId_prenotazione(int id_prenotazione) {
		this.id_prenotazione = id_prenotazione;
	}
	public Date getData_arrivo() {
		return data_arrivo;
	}
	public void setData_arrivo(Date data_arrivo) {
		this.data_arrivo = data_arrivo;
	}
	public Date getData_partenza() {
		return data_partenza;
	}
	public void setData_partenza(Date data_partenza) {
		this.data_partenza = data_partenza;
	}

	public int getNumcamera() {
		return numcamera;
	}

	public void setNumcamera(int numcamera) {
		this.numcamera = numcamera;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCod_albergo() {
		return cod_albergo;
	}

	public void setCod_albergo(int cod_albergo) {
		this.cod_albergo = cod_albergo;
	}
}