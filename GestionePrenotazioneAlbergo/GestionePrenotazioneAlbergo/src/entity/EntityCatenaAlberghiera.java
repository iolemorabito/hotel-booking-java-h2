package entity;

public class EntityCatenaAlberghiera {
	private int cod_catena;
	private String nome;
	
	public EntityCatenaAlberghiera() {
		this.cod_catena=0;
		this.nome=null;
	}
	
	public EntityCatenaAlberghiera(int cod_catena, String nome) {
		this.cod_catena= cod_catena;
		this.nome = nome;
	}

	public int getCodice() {
		return cod_catena;
	}

	public void setCodice(int cod_catena) {
		this.cod_catena = cod_catena;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
