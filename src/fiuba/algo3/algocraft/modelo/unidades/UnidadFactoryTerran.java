package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporteTerran;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(TipoUnidad tipo) throws NoSuchObjectException{
		switch(tipo){
		case especial1:	return new NaveCiencia();
		case terrestre1: return new Marine();
		case terrestre2:return new Golliat();
		case volador1:return new Espectro();
		case volador2:return new NaveTransporteTerran();
		default:throw new NoSuchObjectException("Objeto Sin Tipo");
		}
	}

	@Override
	public boolean puedeCrear(TipoUnidad tipo, int cantMineral, int cantGas) {
		return false;
	}
}
