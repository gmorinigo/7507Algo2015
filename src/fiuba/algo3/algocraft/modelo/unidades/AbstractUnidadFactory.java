package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

public abstract class AbstractUnidadFactory {

	public enum TipoUnidad{terrestre1,terrestre2,volador1,volador2,especial1}
	public abstract Unidad crearUnidad(TipoUnidad tipo) throws NoSuchObjectException;
	public abstract boolean puedeCrear(TipoUnidad tipo, int cantMineral, int cantGas);
}
