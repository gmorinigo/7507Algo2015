package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Dragon;
import fiuba.algo3.algocraft.modelo.unidades.protoss.NaveTransporteProtoss;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo) throws NoSuchObjectException{
		switch(tipo){
		case especial1:	return new AltoTemplario();
		case terrestre1: return new Zealot();
		case terrestre2:return new Dragon();
		case volador1:return new AltoTemplario();
		case volador2:return new NaveTransporteProtoss();
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
	}

	@Override
	public boolean puedeCrear(TipoUnidad tipo, int cantMineral, int cantGas) {

		return false;
	}

	@Override
	public int dameCostoMineral(TipoUnidad tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dameCostoGas(TipoUnidad tipo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
