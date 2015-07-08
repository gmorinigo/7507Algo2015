package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;


public class NaveTransporteProtoss extends NaveTransporte {
	
	public NaveTransporteProtoss(Jugador unJugador) {
		super(unJugador);
	}

	public NaveTransporteProtoss(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.posicionDeConstruccion = posicionConstruccion;
	}

	public boolean esUnidadAerea(){
		return true;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(80,60);
	}

	public int turnosNecesariosParaCreacion() {
		return 8;
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
	
	public Unidad crearAlucinacion() {
		NaveTransporteProtoss unaUnidad = (NaveTransporteProtoss) new NaveTransporteProtoss(this.jugador);
		unaUnidad.marcarEstaUnidadComoAlucinacion();
		unaUnidad.estado = new UnidadEstadoViviendo(this);
		return unaUnidad;
	}

	public boolean recibirataqueMisilEMP() {
		this.salud.destruirEscudo();
		return true; 
	}
	
	public String getNombreObjetoDibujable() {
		return "NaveTransporteProtoss";
	}

	protected void vivir() {
		this.estado = new UnidadEstadoViviendo(this);
	}

	public int obtenerCantidadEscudo() {
		SaludProtoss unaSalud = (SaludProtoss) this.salud;
		return unaSalud.valorEscudo();
	}

}
