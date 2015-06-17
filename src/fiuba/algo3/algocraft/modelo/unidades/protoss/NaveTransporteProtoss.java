package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public class NaveTransporteProtoss extends NaveTransporte {

	public NaveTransporteProtoss(Jugador unJugador) {
		super(unJugador);
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
