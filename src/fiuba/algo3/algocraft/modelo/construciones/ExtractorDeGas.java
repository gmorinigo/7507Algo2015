package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

abstract public class ExtractorDeGas extends Construccion{
	public ExtractorDeGas(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	public int costoGas() {
		return 0;
	}

	public boolean construccionRecolectoraDeGas(){
		return true;
	}
	
	
	public int costoMineral() {
		return 100;
	}
	
	abstract public void recolectar(Almacen almacen);
	
	
	@Override
	protected void vivir() {
		this.recolectar(this.jugador.dameAlmacenGas());
	}
}
