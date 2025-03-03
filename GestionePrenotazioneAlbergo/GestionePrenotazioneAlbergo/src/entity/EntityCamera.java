package entity;

public class EntityCamera{
	private int num_camera;
	private int tipologia;
	private float prezzoPerNotte;
	private int cod_albergo; 
	
	public EntityCamera() {
		this.num_camera=0;
		this.tipologia=0;
		this.prezzoPerNotte=0;
		this.cod_albergo=0;
	}
	
	public EntityCamera(int num_camera, int tipologia, float prezzoPerNotte,int cod_albergo) {
		this.num_camera=num_camera;
		this.tipologia=tipologia;
		this.prezzoPerNotte=prezzoPerNotte;
		this.cod_albergo=cod_albergo;
	}
	
	public boolean confronto(EntityCamera eC ) {
		return (get_num_camera()==eC.get_num_camera() &&  get_tipologia()==eC.get_tipologia() && get_prezzoPerNotte()==eC.get_prezzoPerNotte() && getCod_albergo()== eC.getCod_albergo()) ;
	}
	
	public int get_num_camera() {return num_camera;}
	
	public void set_num_camera(int num_camera) {
		this.num_camera=num_camera;
	}
	
	public int get_tipologia() {return tipologia;}
	
	public void set_tipologia(int tipologia) {
		this.tipologia=tipologia;
	}
	
	public float get_prezzoPerNotte() {return prezzoPerNotte;}
	
	public void set_prezzoPerNotte(float prezzoPerNotte) {
		this.prezzoPerNotte=prezzoPerNotte;
	}

	public int getCod_albergo() {
		return cod_albergo;
	}

	public void setCod_albergo(int cod_albergo) {
		this.cod_albergo = cod_albergo;
	}
}
