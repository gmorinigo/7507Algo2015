package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public abstract class Celda {
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
		this.unidad.setCelda(this);
		this.ocuparCelda();
		
		return true;
	}
	
	public void eliminarUnidad() {
		this.unidad = null;
		this.desocuparCelda();
	}


	abstract public boolean puedeMoverse(Unidad unaUnidad);


	public boolean esAtacable() {
		// Si no atacable est� redefinido el m�todo en la clase particular.
		// Al validar si es atacable tambien verifico que est� ocupada
		return this.celdaOcupada();
	}


	public void atacarUnidadDeLaCeldaConUnidad(Unidad unaUnidadAtacante) {
		this.unidad.recibirataque(unaUnidadAtacante);
		if (!this.unidad.estaViva()){
			// Hay que eliminar la unidad del jugador y que reste la cantidad de poblacion
			// this.unidad.obtenerJugador.eliminarUnidad();
			this.eliminarUnidad();
		}
	}
}
