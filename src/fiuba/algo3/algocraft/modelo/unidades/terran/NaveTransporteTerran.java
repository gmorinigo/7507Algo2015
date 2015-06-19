package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class NaveTransporteTerran extends NaveTransporte {

	public NaveTransporteTerran(Jugador unJugador) {
		super(unJugador);
	}

	public NaveTransporteTerran(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		posicionDeConstruccion = posicionConstruccion;
	}
	
	public boolean esUnidadAerea(){
		return true;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(150);
	}

	@Override
	public int turnosNecesariosParaCreacion() {
		return 7;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
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

	public int getRangoAtaque(Unidad unaUnidad) {
		return 0;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 0;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}
}
