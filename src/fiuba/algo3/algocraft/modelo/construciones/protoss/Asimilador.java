package fiuba.algo3.algocraft.modelo.construciones.protoss;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeGas;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Asimilador extends ExtractorDeGas{
	public Asimilador(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}
	

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 6;
	}


	@Override
	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}
}
