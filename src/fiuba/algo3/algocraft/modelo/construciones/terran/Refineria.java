package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeGas;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Refineria extends ExtractorDeGas{
	
	public Refineria(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
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