package fiuba.algo3.algocraft.modelo.mapa;

public class MapaFactory {

	protected Mapa mapa;
	
	public MapaFactory() {
		this.mapa = Mapa.getInstance();
	}
	
	public Mapa crearMapa30x30Hardcodeado() {
		
		this.mapa.nuevoMapa(30);
		
		return this.mapa;
	}
	
}
