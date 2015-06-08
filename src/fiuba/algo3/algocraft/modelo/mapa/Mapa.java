package fiuba.algo3.algocraft.modelo.mapa;

import java.util.ArrayList;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.recursos.Recurso;

public class Mapa {

	private static Mapa INSTANCE = null;
	private Celda[][] matriz;
	private ArrayList<Recurso> recursos;
	private ArrayList<Construccion> construcciones;

	public Mapa() {
		Celda unaMatriz[][] = new Celda[25][25];
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 200; j++) {
				Celda unaCelda = new CeldaTerrestre(i, j);
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
		this.asignarCeldas(construccion);
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

	public void asignarCeldas(Construccion construccion){

		Posicion unaPosicion = construccion.dameCeldaSupIzquierda().obtenerPosicion();

		ArrayList<Celda> celdas = new ArrayList<>();
		
		celdas.add(this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()]);
		celdas.add(this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()]);
		celdas.add(this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()+1]);
		celdas.add(this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()+1]);
		
		construccion.agregarCeldas(celdas);

	}
	
	
	public void reset() {
		INSTANCE = null;
	}

}

