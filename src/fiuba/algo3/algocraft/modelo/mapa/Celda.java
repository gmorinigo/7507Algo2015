package fiuba.algo3.algocraft.modelo.mapa;


public class Celda {
	private Posicion posicion;
	private boolean tieneGas;
	private boolean tieneMineral;
	private boolean estaOcupada;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		this.posicion = unaPosicion;
		tieneGas = false; 
		tieneMineral = false;
		estaOcupada = false;
	}

	public void cargarCeldaConGas(){
	this.tieneGas = true; 	
	} 
	
	public boolean celdaTieneGas(){
		return this.tieneGas;  
	}
	
	public void cargarCeldaConMineral() {
	this.tieneMineral = true;
	}
	
	public boolean celdaTieneMineral(){
		return this.tieneMineral;  
	}
	
	public Posicion obtenerPosicion(){
		return this.posicion;  
	}
	
	public boolean celdaOcupada(){
		return (this.estaOcupada);  
	}
	
	public void ocuparCelda(){
		this.estaOcupada = true;  
	}

	public void desocuparCelda(){
		this.estaOcupada = false;  
	}
	
}
