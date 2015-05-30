package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class CampoDeBatalla {

	private static CampoDeBatalla INSTANCE = null; 
	private Celda[][] matriz;
	private ArrayList<Recursos> recursos;

	
	public CampoDeBatalla(){
		Celda unaMatriz[][] = new Celda[25][25];
		for(int i = 0; i < 25 ; i++){
			for(int j = 0; j < 25 ; j++){
				Celda unaCelda = new Celda(i,j);
				unaMatriz[i][j] = unaCelda;
			}
		}
		matriz = unaMatriz;
		recursos = new ArrayList<Recursos>();
	}

	public synchronized static CampoDeBatalla getInstance() {
		if (INSTANCE == null) INSTANCE = new CampoDeBatalla();
	return INSTANCE;
	}

	public Celda dameCelda(Posicion unaPosicion) {
		int fila = unaPosicion.dameFila();
		int columna = unaPosicion.dameColumna();
		return matriz[fila][columna];
	}

	public void agregarRecurso(Recursos unRecurso) {
		recursos.add(unRecurso);
	}

	public ArrayList<Recursos> dameRecursos() {
		return recursos;
	}


}
