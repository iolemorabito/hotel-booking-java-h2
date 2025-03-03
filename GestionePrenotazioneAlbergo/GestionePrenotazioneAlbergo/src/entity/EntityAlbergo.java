package entity;

public class EntityAlbergo {
	private int cod_albergo;
	private String nome;
	private String indirizzo;
	private int civico;
	private String citta;
	private int cap;
	private String num_tel;
	private int cod_catena;
	
	public EntityAlbergo() {
		this.cod_albergo = 0;
		this.nome = null;
		this.indirizzo = null;
		this.civico=0;
		this.citta = null;
		this.cap = 0;
		this.num_tel = null;
		this.cod_catena=0;
	}
	
	public EntityAlbergo(int cod_albergo, String nome, String indirizzo,int civico, String citta, int cap, String num_tel, int cod_catena) {
		this.cod_albergo = cod_albergo;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.civico=civico;
		this.citta = citta;
		this.cap = cap;
		this.num_tel = num_tel;
		this.cod_catena=cod_catena;
	}
	
	public int getCodAlbergo() {
		return cod_albergo;
	}

	public void setCodAlbergo(int cod_albergo) {
		this.cod_albergo = cod_albergo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}
	
	public String getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}

	public int getCivico() {
		return civico;
	}
	
	public void setCivico(int civico) {
		this.civico=civico;
	}
	
	public int getCod_catena() {
		return cod_catena;
	}

	public void setCod_catena(int cod_catena) {
		this.cod_catena = cod_catena;
	}
}
