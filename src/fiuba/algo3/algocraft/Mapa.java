package fiuba.algo3.algocraft;

import java.util.ArrayList;

import fiuba.algo3.algocraftexceptions.CeldaOcupadaException;


public class Mapa {

	private static Mapa INSTANCE = null;
	private Celda[][] matriz;
	private ArrayList<Recurso> recursos;
	private ArrayList<Construccion> construcciones;

	public Mapa() {
		Celda unaMatriz[][] = new Celda[25][25];
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				Celda unaCelda = new Celda(i, j);
				unaMatriz[i][j] = unaCelda;
			}
		}
		matriz = unaMatriz;
		recursos = new ArrayList<Recurso>();
		construcciones = new ArrayList<Construccion>();
	}

	public synchronized static Mapa getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Mapa();
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
	
	public ArrayList<Construccion> dameConstrucciones() {
		return this.construcciones;
	}


	public void agregarConstruccion(Construccion construccion) throws CeldaOcupadaException{

		Celda unaCelda = construccion.dameCeldaSupIzquierda();

		try {
			this.verificarCeldasOcupadas(unaCelda.obtenerPosicion());
		} catch (CeldaOcupadaException e) {
			throw e;
		}
		
		this.ocuparCeldasConstruccion(unaCelda.obtenerPosicion());
		//this.asignarCeldas(construccion);
		System.out.print("Agrega la construccion\n");
		this.construcciones.add(construccion);

	}

	private void verificarCeldasOcupadas(Posicion unaPosicion) throws CeldaOcupadaException {
		if (this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].celdaOcupada()) 
		{
			throw new CeldaOcupadaException(); 
		}
		if (this.matriz[unaPosicion.dameFila() + 1][unaPosicion.dameColumna()].celdaOcupada()) 
		{
			throw new CeldaOcupadaException(); 
		}
		if (this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna() + 1].celdaOcupada()) 
		{
			throw new CeldaOcupadaException(); 
		}
		if (this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna() + 1].celdaOcupada()) 
		{
			throw new CeldaOcupadaException(); 
		}
	}
	
	public boolean verificarCeldaOcupada(Posicion unaPosicion){
		return (this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].celdaOcupada());
	}
	
	private void ocuparCeldasConstruccion(Posicion unaPosicion){
		this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].ocuparCelda();
		this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()].ocuparCelda();
		this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()+1].ocuparCelda();
		this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()+1].ocuparCelda();
	}


}
