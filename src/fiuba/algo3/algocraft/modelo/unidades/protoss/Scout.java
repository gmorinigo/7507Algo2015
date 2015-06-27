package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Scout extends UnidadProtoss {

	public Scout(Jugador unJugador) {
		super(unJugador);
		this.tamanioTransporte = 0;
	}
	
	public Scout(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 0;
		this.posicionDeConstruccion = posicionConstruccion;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(150,100);
	}

	public boolean esUnidadAerea(){
		return true;
	}

	public int turnosNecesariosParaCreacion() {
		return 9;
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		if (unaUnidadAtacada.esUnidadAerea()){
			return 14;
		}
		else {
			return 8;			
		}
	} 
	
	public int DanioAtaque(Construccion construccion) {
		return 8;
	}
	
	protected boolean atacaUnidadesAereas() {
		return true;
	}

	public int getRangoAtaque(Unidad unidad) {
		return 4;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 4;
	}
	
	public int obtenerOcupacionSuministro() {
		return 3;
	}

	public Unidad crearAlucinacion() {
		Scout unaUnidad = (Scout) new Scout(this.jugador);
		unaUnidad.marcarEstaUnidadComoAlucinacion();
		unaUnidad.estado = new UnidadEstadoViviendo(this);
		return unaUnidad;
	}
	
	public String getNombreObjetoDibujable() {
		return "Scout";
	}


}
