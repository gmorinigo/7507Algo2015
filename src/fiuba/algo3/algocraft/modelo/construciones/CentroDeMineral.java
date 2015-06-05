package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class CentroDeMineral extends Construccion{
	
	//private int tamanio = 2;

	
	public CentroDeMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	@Override
	public boolean reuneLosRequisitos(Jugador jugador) {
		return true;
	}

	public void recolectar(Almacen almacen, Posicion unaPosicion) {
		Mapa unMapa = Mapa.getInstance();				
		Celda unaCelda = unMapa.dameCelda(unaPosicion);
		if(unaCelda.celdaTieneGas()) 
		almacen.almacenarRecurso(10);
		
	}

	@Override
	public int costoGas() {
		return 0;
	}

	@Override
	public int costoMineral() {
		return 100;
	}




}
