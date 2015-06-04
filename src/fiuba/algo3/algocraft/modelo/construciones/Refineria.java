package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Refineria extends Construccion{
	
	//private int tamanio = 2;

	
	public Refineria(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	@Override
	public boolean reuneLosRequisitos(Jugador jugador2) {
		return true;
	}

}