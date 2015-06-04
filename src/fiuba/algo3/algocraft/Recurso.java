package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Recurso {

	private ArrayList<Celda> recursos = new ArrayList<Celda>();

	public Recurso(Posicion unaPosicion){
		Celda unaCelda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
		recursos.add(unaCelda);
	}	

}
