package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class Golliat extends UnidadTerran {

	//private DisparoStrategy disparoStrategy;
	
	public Golliat(Jugador unJugador) {
		super(unJugador);
		this.tamanioTransporte = 2;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	public Golliat(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 2;
		posicionDeConstruccion = posicionConstruccion;
	}
	
	@Override
	protected Salud saludInicial() {
		return new SaludTerran(125);
	}

	public int turnosNecesariosParaCreacion() {
		return 6;
	}

	public int DanioAtaque(Construccion construccion) {
		return 6;
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		if (unaUnidadAtacada.esUnidadAerea()){
			return 10;
		}
		else {
			return 12;			
		}
	} 
	
	protected boolean atacaUnidadesAereas() {
		return true;
	}

	public int getRangoAtaque(Unidad unaUnidad) {
		if (unaUnidad.esUnidadAerea()){
			return 5;
		}
		else{
			return 6;
		}
		
	}

	public int getRangoAtaque(Construccion construccion) {
		return 6;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}

	@Override
	public boolean recibirataqueMisilEMP(Unidad unaUnidadAtacante) {
		// TODO Auto-generated method stub
		return false;
	}
}
