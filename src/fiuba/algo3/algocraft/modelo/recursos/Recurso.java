package fiuba.algo3.algocraft.modelo.recursos;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Recurso {

	private ArrayList<Celda> recursos = new ArrayList<Celda>();

	public Recurso(Posicion unaPosicion){
		Celda unaCelda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
		recursos.add(unaCelda);
	}	

}