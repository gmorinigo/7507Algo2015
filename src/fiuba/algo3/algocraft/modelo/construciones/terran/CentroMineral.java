package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class CentroMineral extends ExtractorDeMineral{
	
	public CentroMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
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
