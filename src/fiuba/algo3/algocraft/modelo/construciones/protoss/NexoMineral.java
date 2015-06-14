package fiuba.algo3.algocraft.modelo.construciones.protoss;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class NexoMineral extends ExtractorDeMineral {

	public NexoMineral(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 4;
	}
	
	@Override
	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}
}
