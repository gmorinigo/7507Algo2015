package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public abstract class Celda {
	private Posicion posicion;
	private boolean estaOcupada;
	@SuppressWarnings("unused")
	private Unidad unidad;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		this.posicion = unaPosicion;
		this.unidad = null;
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
	
	public boolean celdaLibre(){
		return (!this.estaOcupada);  
	}
	
	public boolean tieneGas(){
		return false;
	}

	public boolean tieneMineral(){
		return false;
	}


	public void agregarUnidad(Unidad unaUnidad) {
		this.unidad = unaUnidad;
		this.ocuparCelda();
	}
	
	public void eliminarUnidad() {
		this.unidad = null;
		this.desocuparCelda();
	}


	public void puedeMoverse(Unidad unaUnidad) {
		// TODO Auto-generated method stub
		
	}
}
