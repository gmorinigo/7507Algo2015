package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.AlmacenGasVespano;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Refineria extends Construccion{
	
	//private int tamanio = 2;

	
	public Refineria(Posicion unaPosicion){
		super(unaPosicion);
	}

	public void recolectar(AlmacenGasVespano almacen) {
		almacen.almacenarGas(10);
	}

}