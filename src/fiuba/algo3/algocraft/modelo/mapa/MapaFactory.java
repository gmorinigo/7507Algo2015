package fiuba.algo3.algocraft.modelo.mapa;

public class MapaFactory {

	protected Mapa mapa;
	
	public MapaFactory() {
		this.mapa = Mapa.getInstance();
	}
	
	public Mapa crearMapa20x20Hardcodeado() {
		
		this.mapa.nuevoMapa(20);
		
		return this.mapa;
	}
	
}
