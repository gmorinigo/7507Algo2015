package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;

public abstract class AbstractUnidadFactory {

	public enum TipoUnidad{terrestre1,terrestre2,volador1,volador2,especial1}
	public abstract Unidad crearUnidad(TipoUnidad tipo, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos;
		
	protected void verificarRecursosParaPoderCrear(TipoUnidad tipo, Jugador unJugador) 
	throws CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException {
		if (unJugador.dameCantidadMineral() < this.dameCostoMineral(tipo)){
			throw new CantidadDeMineralInsuficienteException();
		}
		if (unJugador.dameCantidadGas() < this.dameCostoGas(tipo)){
			throw new CantidadDeGasInsuficienteException();		
		}
		 
	}
	
	protected abstract int dameCostoMineral(TipoUnidad tipo);
	protected abstract int dameCostoGas(TipoUnidad tipo);
}
