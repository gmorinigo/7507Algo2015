package fiuba.algo3.algocraft.modelo.mapa;

import java.util.ArrayList;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
//import fiuba.algo3.algocraft.modelo.recursos.Recurso;

public class Mapa {

	private static Mapa INSTANCE = null;
	private Celda[][] matriz;
	private ArrayList<Construccion> construcciones;

	public Mapa() {
		this.matriz = new Celda[200][200];
		
		this.agregarCeldasFilas_0_a_9_Columnas_0_a_199();
		this.agregarCeldasFilas_190_a_199_Columnas_0_a_199();		
		this.agregarCeldasFilas_10_a_189_Columnas_0_a_199();
		
		construcciones = new ArrayList<Construccion>();
	}

	private void agregarCeldasFilas_0_a_9_Columnas_0_a_199() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 200; j++) {
				if (((j==10) || (j==190)) && (i==9)){
					agregarCeldaConGas(i, j);
				} else {
					if (((j==12) || (j==188)) && (i==9)){
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
				if (((j==10) || (j==190)) && (i==190)){
					agregarCeldaConGas(i, j);
				} else {
					if (((j==12) || (j==188)) && (i==190)){
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
				if ((j==32) && (i==30)){
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

	/*public void agregarRecurso(Recurso unRecurso) {
		recursos.add(unRecurso);
	}

	public ArrayList<Recurso> dameRecursos() {
		return recursos;
	}
	*/
	
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

