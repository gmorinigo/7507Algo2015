package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class AltoTemplario extends UnidadProtoss {

	public AltoTemplario(Jugador unJugador) {
		super(unJugador);
		this.tamanioTransporte = 2;
	}
	
	public AltoTemplario(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 2;
		this.posicionDeConstruccion = posicionConstruccion;
	}
	
	
	protected Salud saludInicial() {
		return new SaludProtoss(40,40);
	}
	
	public int turnosNecesariosParaCreacion() {
		return 7;
	}

	public boolean atacar(Celda unaCelda) {
		return false;
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 0;
	}

	public int DanioAtaque(Construccion construccion) {
		return 0;
	}
	
	protected boolean atacaUnidadesAereas() {
		return false;
	}

	public int getRangoAtaque(Unidad unidad) {
		return 0;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 0;
	}

	public int obtenerOcupacionSuministro() {
		return 2;
	}
}
