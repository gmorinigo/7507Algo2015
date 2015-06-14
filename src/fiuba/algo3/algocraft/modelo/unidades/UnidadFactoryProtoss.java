package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Dragon;
import fiuba.algo3.algocraft.modelo.unidades.protoss.NaveTransporteProtoss;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Scout;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo, int cantMineral, int cantGas) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException{
		this.verificarRecursosParaPoderCrear(tipo, cantMineral, cantGas);
		
		switch(tipo){
		case terrestre1: return new Zealot();
		case terrestre2:return new Dragon();
		case volador1:return new Scout();
		case volador2:return new NaveTransporteProtoss();
		case especial1:	return new AltoTemplario();
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
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
}
