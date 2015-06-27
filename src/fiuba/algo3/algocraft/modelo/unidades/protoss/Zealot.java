package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Zealot extends UnidadProtoss{

	public Zealot(Jugador unJugador) {
		super(unJugador);
		this.tamanioTransporte = 2;
	}

	public Zealot(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 2;
		this.posicionDeConstruccion = posicionConstruccion;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(100,60);
	}

	public int turnosNecesariosParaCreacion() {
		return 4;
	}
	
	public int DanioAtaque(Unidad unaUnidadAtacada) {
		if (this.esUnaAlucinacion) return 0;
		if (unaUnidadAtacada.esUnidadAerea()){
			return 0;
		}
		else {
			return 8;			
		}
	} 
	
	public int DanioAtaque(Construccion construccion) {
		if (this.esUnaAlucinacion) return 0;
		return 8;
	}
	
	protected boolean atacaUnidadesAereas() {
		return false;
	}

	public int getRangoAtaque(Unidad unidad) {
		return 1;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 1;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}

	public Unidad crearAlucinacion() {
		Zealot unaUnidad = (Zealot) new Zealot(this.jugador);
		unaUnidad.marcarEstaUnidadComoAlucinacion();
		unaUnidad.estado = new UnidadEstadoViviendo(this);
		return unaUnidad;
	}
	
	public String getNombreObjetoDibujable() {
		return "Zealot";
	}

}
