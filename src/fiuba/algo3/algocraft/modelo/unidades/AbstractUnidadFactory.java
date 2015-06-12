package fiuba.algo3.algocraft.modelo.unidades;

public abstract class AbstractUnidadFactory {

	public enum TipoUnidad{terrestre1,terrestre2,volador1,volador2,especial1,especial2}
	public abstract Unidad crearUnidad(TipoUnidad tipo);
	public abstract boolean puedeCrear(TipoUnidad tipo, int cantMineral, int cantGas);
}
