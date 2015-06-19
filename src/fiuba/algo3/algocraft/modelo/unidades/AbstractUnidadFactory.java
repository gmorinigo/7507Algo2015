package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;

public abstract class AbstractUnidadFactory {

	public enum TipoUnidad{terrestre1,terrestre2,volador1,volador2,especial1}
	public abstract Unidad crearUnidad(TipoUnidad tipo, Jugador unJugador,Posicion posicionConstruccion) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada;
		
	protected void verificarRecursosParaPoderCrear(TipoUnidad tipo, Jugador unJugador) 
	throws CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException {
		if (unJugador.dameCantidadMineral() < this.dameCostoMineral(tipo)){
			throw new CantidadDeMineralInsuficienteException();
		}
		if (unJugador.dameCantidadGas() < this.dameCostoGas(tipo)){
			throw new CantidadDeGasInsuficienteException();		
		}
		 
	}
	
	protected void verificarPoblacionParaCrearUnidad(TipoUnidad unTipo, Jugador unJugador) throws CapacidadDePoblacionMaximaSuperada{
		int cantidadPoblacionDisponible = unJugador.obtenerCantidadPoblacionDisponible();
		
		if (cantidadPoblacionDisponible < this.obtenerOcupacionSuministro(unTipo)){
			throw new CapacidadDePoblacionMaximaSuperada();
		}
		
	}
	
	protected abstract int dameCostoMineral(TipoUnidad tipo);
	protected abstract int dameCostoGas(TipoUnidad tipo);
	
	protected abstract int obtenerOcupacionSuministro(TipoUnidad tipo);

}
