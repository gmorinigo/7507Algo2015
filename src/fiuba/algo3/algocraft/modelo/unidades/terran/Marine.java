package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;


public class Marine extends UnidadTerran {

	//private DisparoStrategy disparoStrategy;


	public Marine(Jugador unJugador) {
		super(unJugador);
		this.tamanioTransporte = 1;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(40);
	}

	public int turnosNecesariosParaCreacion() {
		return 3;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
	}
	
	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 6;
	} 

	public int DanioAtaque(Construccion construccion) {
		return 6;
	}
	
	protected boolean atacaUnidadesAereas() {
		return true;
	}

	public int getRangoAtaque(Unidad unaUnidad) {
		return 4;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 4;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 1;
	}
}
