package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;

public abstract class AbstractUnidadFactory {

	public abstract Unidad crearUnidad(String nombre/*,DisparoStrategy estrategiaDisparo*/);

}
