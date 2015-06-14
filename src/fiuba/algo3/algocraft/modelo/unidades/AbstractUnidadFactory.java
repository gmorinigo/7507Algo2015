package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;

public abstract class AbstractUnidadFactory {

	public enum TipoUnidad{terrestre1,terrestre2,volador1,volador2,especial1}
	public abstract Unidad crearUnidad(TipoUnidad tipo, int cantMineral, int cantGas) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException;
		
	protected void verificarRecursosParaPoderCrear(TipoUnidad tipo, int cantMineral, int cantGas) 
	throws CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException {
		if (cantMineral < this.dameCostoMineral(tipo)){
			throw new CantidadDeMineralInsuficienteException();
		}
		if (cantGas < this.dameCostoGas(tipo)){
			throw new CantidadDeGasInsuficienteException();		
		}
		 
	}
	
	protected abstract int dameCostoMineral(TipoUnidad tipo);
	protected abstract int dameCostoGas(TipoUnidad tipo);
}
