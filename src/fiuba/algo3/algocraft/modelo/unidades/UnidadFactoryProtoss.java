package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Dragon;
import fiuba.algo3.algocraft.modelo.unidades.protoss.NaveTransporteProtoss;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Scout;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo, Jugador unJugador,Posicion posicionConstruccion) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);
		this.verificarPoblacionParaCrearUnidad(tipo, unJugador);
		
		unJugador.dameAlmacenMineral().consumirRecurso(this.dameCostoMineral(tipo));
		unJugador.dameAlmacenGas().consumirRecurso(this.dameCostoGas(tipo));
		
		Unidad unaUnidad;
		
		switch(tipo){
		case terrestre1: 
			unaUnidad = new Zealot(unJugador,posicionConstruccion);
			break;
		case terrestre2: 
			unaUnidad = new Dragon(unJugador,posicionConstruccion);
			break;
		case volador1: 
			unaUnidad = new Scout(unJugador,posicionConstruccion);
			break;
		case volador2: 
			unaUnidad = new NaveTransporteProtoss(unJugador,posicionConstruccion);
			break;
		case especial1:	
			unaUnidad =  new AltoTemplario(unJugador,posicionConstruccion);
			break;
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
		
		unJugador.agregarUnidad(unaUnidad);
		return unaUnidad;
	}

	protected int dameCostoMineral(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 100;
		case terrestre2:return 125;
		case volador1:return 300;
		case volador2:return 200;
		case especial1:	return 50;
		default: return 0;
		}
	}

	protected int dameCostoGas(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 0;
		case terrestre2:return 50;
		case volador1:return 150;
		case volador2:return 0;
		case especial1:	return 150;
		default: return 0;
		}
	}
	
	protected int obtenerOcupacionSuministro(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 2;
		case terrestre2:return 2;
		case volador1:return 3;
		case volador2:return 2;
		case especial1:	return 2;
		default: return 2;
		}
	}
}
