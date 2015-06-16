package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public abstract class Celda /*implements ICeldaVisitable*/{
	private Posicion posicion;
	private boolean estaOcupada;
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
		this.unidad = null;
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


	public boolean agregarUnidad(Unidad unaUnidad) {
		if (!this.puedeMoverse(unaUnidad) && this.celdaOcupada()) {
			return false;
		}
		this.unidad = unaUnidad;
		this.unidad.mover(this);
		this.ocuparCelda();
		
		return true;
	}
	
	public void eliminarUnidad() {
		this.unidad = null;
		this.desocuparCelda();
	}


	abstract public boolean puedeMoverse(Unidad unaUnidad);
}
