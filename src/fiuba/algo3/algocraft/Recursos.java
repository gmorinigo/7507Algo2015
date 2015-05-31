package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Recursos {

	private ArrayList<Celda> recursos = new ArrayList<Celda>();

	public Recursos(Posicion unaPosicion){
		Celda unaCelda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
		recursos.add(unaCelda);
	}	

}
