package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Dragon;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Scout;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporte;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo) throws NoSuchObjectException{
		switch(tipo){
		case especial1:	return new AltoTemplario();
		case terrestre1: return new Zealot();
		case terrestre2:return new Dragon();
		case volador1:return new AltoTemplario();
		case volador2:return new NaveTransporte();
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
	}

	@Override
	public boolean puedeCrear(TipoUnidad tipo, int cantMineral, int cantGas) {

		return false;
	}
}
