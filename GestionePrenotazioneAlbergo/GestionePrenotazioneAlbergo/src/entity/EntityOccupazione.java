package entity;

import java.util.Date;

public class EntityOccupazione {
	
	private Date data;
	private int occupato;
	private int numcamera;
	private int cod_albergo;

	public EntityOccupazione() {
		this.data=null;
		this.occupato=-1;
		this.numcamera=0;
		this.cod_albergo=0;
	}
	
	public EntityOccupazione(int numcamera, Date data, int occupato, int cod_albergo) {
		this.data = data;
		this.occupato = occupato;
		this.numcamera=numcamera;
		this.cod_albergo = cod_albergo;	
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public int getOccupato() {
		return occupato;
	}
	
	public void setOccupato(int occupato) {
		this.occupato = occupato;
	}
	
	public int getNumcamera() {
		return numcamera;
	}

	public void setNumcamera(int numcamera) {
		this.numcamera = numcamera;
	}

	public int getCod_albergo() {
		return cod_albergo;
	}

	public void setCod_albergo(int cod_albergo) {
		this.cod_albergo = cod_albergo;
	}
}
