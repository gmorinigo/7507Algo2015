package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporteTerran;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);

		unJugador.dameAlmacenMineral().consumirRecurso(this.dameCostoMineral(tipo));
		unJugador.dameAlmacenGas().consumirRecurso(this.dameCostoGas(tipo));
		
		switch(tipo){
		case terrestre1: return new Marine();
		case terrestre2:return new Golliat();
		case volador1:return new Espectro();
		case volador2:return new NaveTransporteTerran();
		case especial1:	return new NaveCiencia();
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
		
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
}
