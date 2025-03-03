package entity;

public class EntityCliente {
	
	private String email;
	private String nome;
	private String cognome;
    private String indirizzo;
    private int civico;
	private String num_tel;
	private String carta_dicredito;
	
	public EntityCliente() {
		this.email = null;
		this.nome = null;
		this.cognome = null;
		this.indirizzo = null;
		this.civico=0;
		this.num_tel = null;
		this.carta_dicredito=null;
	}
	
	public EntityCliente(String nome, String cognome,String email, String indirizzo,int civico,
			String num_tel, String carta_dicredito) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.civico=civico;
		this.num_tel = num_tel;
		this.carta_dicredito=carta_dicredito;
	}
	


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public int getCivico() {
		return civico;
	}
	
	public void setCivico(int civico) {
		this.civico=civico;
	}
	public String getNum_tel() {
		return num_tel;
	}
	
	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}

	public String getCarta_dicredito() {
		return carta_dicredito ;
	}
	
	public void setCarta_dicredito(String carta_dicredito) {
		this.carta_dicredito=carta_dicredito;
	}
}
