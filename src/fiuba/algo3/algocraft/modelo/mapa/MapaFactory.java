package fiuba.algo3.algocraft.modelo.mapa;

public class MapaFactory {

	protected Mapa mapa;
	
	public MapaFactory() {
		this.mapa = Mapa.getInstance();
	}
	
	public Mapa crearMapa10x10Hardcodeado() {
		
		this.mapa.nuevoMapa(10);
		
		return this.mapa;
	}
	
}
