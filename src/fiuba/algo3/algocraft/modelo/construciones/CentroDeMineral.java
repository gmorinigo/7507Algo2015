package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
//import fiuba.algo3.algocraft.modelo.mapa.Celda;
//import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class CentroDeMineral extends ExtractorDeMineral{
	
	//private int tamanio = 2;

	
	public CentroDeMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);		
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(8, this);
	}
	
	/* lo paso a la clase madre
	@Override
	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	@Override
	public int costoGas() {
		return 0;
	}

	@Override
	public int costoMineral() {
		return 100;
	}
*/



}
