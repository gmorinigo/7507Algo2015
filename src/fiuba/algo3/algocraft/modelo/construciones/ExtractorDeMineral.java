package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

abstract public class ExtractorDeMineral extends Construccion {
	public ExtractorDeMineral(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	public int costoGas() {
		return 0;
	}

	public int costoMineral() {
		return 50;
	}

	public boolean construccionRecolectoraDeMineral(){
		return true;
	}
	
	abstract public void recolectar(Almacen almacen);
	
	
	
	@Override
	protected void vivir() {
		if(this.celdas.get(0).tieneMineral()) {
			this.recolectar(this.jugador.dameAlmacenMineral());	
		}
	}
}
