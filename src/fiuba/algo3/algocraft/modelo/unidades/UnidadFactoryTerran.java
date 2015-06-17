package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporteTerran;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);
		this.verificarPoblacionParaCrearUnidad(tipo, unJugador);


		unJugador.dameAlmacenMineral().consumirRecurso(this.dameCostoMineral(tipo));
		unJugador.dameAlmacenGas().consumirRecurso(this.dameCostoGas(tipo));
		
		Unidad unaUnidad;
		
		switch(tipo){
		case terrestre1: 
			unaUnidad = new Marine(unJugador);
			break;
		case terrestre2:
			unaUnidad = new Golliat(unJugador);
			break;
		case volador1: 
			unaUnidad = new Espectro(unJugador);
			break;
		case volador2: 
			unaUnidad = new NaveTransporteTerran(unJugador);
			break;
		case especial1:	
			unaUnidad = new NaveCiencia(unJugador);
			break;
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
		
		unJugador.agregarUnidad(unaUnidad);
		
		return unaUnidad;
		
	}

	protected int dameCostoMineral(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 50;
		case terrestre2:return 100;
		case volador1:return 150;
		case volador2:return 100;
		case especial1:	return 100;
		default: return 0;
		}
	}

	protected int dameCostoGas(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 0;
		case terrestre2:return 50;
		case volador1:return 100;
		case volador2:return 100;
		case especial1:	return 225;
		default: return 0;
		}
	}

	protected int obtenerOcupacionSuministro(TipoUnidad tipo) {
		switch(tipo){
		case terrestre1: return 1;
		case terrestre2:return 2;
		case volador1:return 2;
		case volador2:return 2;
		case especial1:	return 2;
		default: return 2;
		}
	}
}
