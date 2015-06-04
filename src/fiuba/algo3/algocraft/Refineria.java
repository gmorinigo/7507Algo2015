package fiuba.algo3.algocraft;

public class Refineria extends Construccion{
	
	//private int tamanio = 2;

	
	public Refineria(Posicion unaPosicion){
		super(unaPosicion);
	}

	public void recolectar(AlmacenGasVespano almacen) {
		almacen.almacenarGas(10);
	}

}