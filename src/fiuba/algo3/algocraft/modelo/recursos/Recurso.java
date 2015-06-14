package fiuba.algo3.algocraft.modelo.recursos;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.CeldaTerrestre;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public abstract class Recurso {

//	private ArrayList<Celda> recursos = new ArrayList<Celda>();
	protected int cantidad; 
	
	public Recurso(Posicion unaPosicion){
		Celda unaCelda = new CeldaTerrestre(unaPosicion.dameFila(),unaPosicion.dameColumna());
		//recursos.add(unaCelda);
	}
	
	
	protected void setRecurso(int rec){this.cantidad = rec; }	
	protected int getCantidadDelRecurso(){return this.cantidad;	}
	
	
	//public void recolectar() hacerlo abstracto
		
	 
	

}
