package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Mapa {

	private static Mapa INSTANCE = null; 
	private Celda[][] matriz;
	private ArrayList<Recurso> recursos;
	private ArrayList<Construccion> construcciones;

	
	public Mapa(){
		Celda unaMatriz[][] = new Celda[25][25];
		for(int i = 0; i < 25 ; i++){
			for(int j = 0; j < 25 ; j++){
				Celda unaCelda = new Celda(i,j);
				unaMatriz[i][j] = unaCelda;
			}
		}
		matriz = unaMatriz;
		recursos = new ArrayList<Recurso>();
		construcciones = new ArrayList<Construccion>();
	}

	public synchronized static Mapa getInstance() {
		if (INSTANCE == null) INSTANCE = new Mapa();
	return INSTANCE;
	}

	public Celda dameCelda(Posicion unaPosicion) {
		int fila = unaPosicion.dameFila();
		int columna = unaPosicion.dameColumna();
		return matriz[fila][columna];
	}

	public void agregarRecurso(Recurso unRecurso) {
		recursos.add(unRecurso);
	}

	public ArrayList<Recurso> dameRecursos() {
		return recursos;
	}
	
	public void agregarConstruccion(Construccion construccion) {
		this.construcciones.add(construccion);
		
		//pedir posicion a la construccion
		//construccion.damePosicion();
		//implementar calculo de celdas
		//asignar celdas a la construccion
		// construccion.agregarCeldas(celdasParaConstruccion);
	}
	
	public ArrayList<Construccion> dameContrucciones() {
		return this.construcciones;
	}

}
