package fiuba.algo3.algocraft;

public class Celda {
	private Posicion posicion;
	private boolean tieneGas;
	private boolean tieneMineral;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		posicion = unaPosicion;
		tieneGas = false; 
		tieneMineral = false;
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
	
}
