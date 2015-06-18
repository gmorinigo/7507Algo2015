package fiuba.algo3.algocraft.modelo.mapa;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Mapa {

	private static Mapa INSTANCE = null;
	private Celda[][] matriz;
	private ArrayList<Construccion> construcciones;
	private int nroColumnaMaxima;
	private int nroFilaMaxima;

	public Mapa() {
		this.matriz = new Celda[200][200];
		this.nroColumnaMaxima = 199;
		this.nroFilaMaxima = 199;
		
		this.agregarCeldasFilas_0_a_9_Columnas_0_a_199();
		this.agregarCeldasFilas_190_a_199_Columnas_0_a_199();		
		this.agregarCeldasFilas_10_a_189_Columnas_0_a_199();
		
		construcciones = new ArrayList<Construccion>();
	}

	private void agregarCeldasFilas_0_a_9_Columnas_0_a_199() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 200; j++) {
				if (((j==9) || (j==190)) && (i==9)){
					agregarCeldaConGas(i, j);
				} else {
					if (((j==11) || (j==188)) && (i==9)){
						agregarCeldaConMineral(i, j);
					} else {
						agregarCeldaTerrestre(i, j);						
					}
				}
			}
		}
	}

	private void agregarCeldasFilas_190_a_199_Columnas_0_a_199() {
		for (int i = 190; i < 200; i++) {
			for (int j = 0; j < 200; j++) {
				if (((j==9) || (j==190)) && (i==190)){
					agregarCeldaConGas(i, j);
				} else {
					if ((j==11) && (i==190)){
						agregarCeldaConMineral(i, j);
					} else {
						agregarCeldaTerrestre(i, j);						
					}
				}
			}
		}
	}

	private void agregarCeldasFilas_10_a_189_Columnas_0_a_199() {
		for (int i = 10; i < 190; i++) {
			this.agregarCeldasFilas_10_a_189_Columnas_0_a_79(i);
			this.agregarCeldasFilas_10_a_189_Columnas_80_a_119(i);
			this.agregarCeldasFilas_10_a_189_Columnas_120_a_199(i);
		}
	}

	private void agregarCeldasFilas_10_a_189_Columnas_0_a_79(int i) {
		for (int j = 0; j < 80; j++) {
			if ((j==30) && (i==30)){
				agregarCeldaConGas(i, j);
			} else {
				if (((j==32) || (j==188)) && (i==30)){
					agregarCeldaConMineral(i, j);
				} else {
					agregarCeldaTerrestre(i, j);
				}
			}
		}
	}
	
	
	private void agregarCeldasFilas_10_a_189_Columnas_80_a_119(int i) {
		for (int j = 80; j < 120; j++) {
			CeldaAerea unaCelda = new CeldaAerea(i, j);
			this.matriz[i][j] = unaCelda;
		}
	}
	
	private void agregarCeldasFilas_10_a_189_Columnas_120_a_199(int i) {
		for (int j = 120; j < 200; j++) {
			if ((j==170) && (i==170)){
				agregarCeldaConGas(i, j);
			} else {
				if ((j==172) && (i==170)){
					agregarCeldaConMineral(i, j);
				} else {
					agregarCeldaTerrestre(i, j);
				}
			}
		}
	}
	private void agregarCeldaTerrestre(int i, int j) {
		CeldaTerrestre unaCelda = new CeldaTerrestre(i, j);
		this.matriz[i][j] = unaCelda;
	}

	private void agregarCeldaConMineral(int i, int j) {
		CeldaConMineral unaCelda = new CeldaConMineral(i, j);
		this.matriz[i][j] = unaCelda;
	}

	private void agregarCeldaConGas(int i, int j) {
		CeldaConGas unaCelda = new CeldaConGas(i, j);
		this.matriz[i][j] = unaCelda;
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

	public ArrayList<Construccion> dameConstrucciones() {
		return this.construcciones;
	}


	public boolean agregarConstruccion(Construccion construccion) {

		Posicion unaPosicion = construccion.damePosicionCeldaSupIzquierda();
		Celda unaCelda = this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()];
		
		if(! this.verificarFinDeMapa(construccion, unaPosicion) 
				|| ! this.verificarSiPuedoConstruir(construccion, unaCelda))
			return false;
		
		this.agregarCeldasConstruccion(construccion, unaCelda.obtenerPosicion());
		this.asignarCeldas(construccion);
		this.construcciones.add(construccion);

		return true;
	}

	private boolean verificarSiPuedoConstruir(Construccion construccion, Celda celda) {
		//Para construccion de una sola celda
		if (!construccion.construccionRecolectoraDeMineral() &&
			!construccion.construccionRecolectoraDeGas())
			return celda.esPosbibleConstruir(construccion);
		
		//Para construcciones de 4celdas
		Posicion unaPosicion = celda.obtenerPosicion();
		if( !this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].esPosbibleConstruir(construccion)){
			return false;	
		}
		if ( ! this.matriz[unaPosicion.dameFila() + 1][unaPosicion.dameColumna()].esPosbibleConstruir(construccion)) 
		{
			return false;
		}
		if (! this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna() + 1].esPosbibleConstruir(construccion)) 
		{
			return false;
		}
		if (!this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna() + 1].esPosbibleConstruir(construccion)) 
		{
			return false ;
		}
		return true;
		
	}
	
	private boolean verificarFinDeMapa(Construccion construccion, Posicion unaPosicion)  {
		if (!construccion.construccionRecolectoraDeGas() && !construccion.construccionRecolectoraDeMineral() 
		&& (unaPosicion.dameColumna() == this.nroColumnaMaxima || unaPosicion.dameFila() == this.nroFilaMaxima) ){
			return false;
		}
		return true;
	}
	
	
	public boolean verificarCeldaOcupada(Posicion unaPosicion){
		return (this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].celdaOcupada());
	}
	
	private void agregarCeldasConstruccion(Construccion construccion, Posicion unaPosicion){
		this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()].agregarConstruccion(construccion);
		
		if (!construccion.construccionRecolectoraDeGas() && !construccion.construccionRecolectoraDeMineral()){
			this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()].agregarConstruccion(construccion);
			this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()+1].agregarConstruccion(construccion);
			this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()+1].agregarConstruccion(construccion);
		}
	}

	public void asignarCeldas(Construccion construccion){

		Posicion unaPosicion = construccion.damePosicionCeldaSupIzquierda();

		ArrayList<Celda> celdas = new ArrayList<>();
		
		
		celdas.add(this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()]);
		
		if (!construccion.construccionRecolectoraDeGas() && !construccion.construccionRecolectoraDeMineral()){
			celdas.add(this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()]);
			celdas.add(this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()+1]);
			celdas.add(this.matriz[unaPosicion.dameFila()+1][unaPosicion.dameColumna()+1]);	
		}
		
		construccion.agregarCeldas(celdas);

	}
	
	
	public void reset() {
		INSTANCE = null;
	}

	public boolean agregarUnidad(Posicion unaPosicion, Unidad unaUnidad) {
		Celda unaCelda = this.matriz[unaPosicion.dameFila()][unaPosicion.dameColumna()];
		unaUnidad.setCelda(unaCelda);
		return unaCelda.agregarUnidad(unaUnidad);
		
	}
}

