package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public abstract class Celda {
	private Posicion posicion;
	private boolean estaOcupada;
	private Unidad unidad;
	protected Construccion construccion;
	
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
	
	public boolean tieneGas(){
		return false;
	}

	public boolean tieneMineral(){
		return false;
	}


	public boolean agregarUnidad(Unidad unaUnidad) {
		if (!this.puedeMoverse(unaUnidad) || this.celdaOcupada() || !unaUnidad.estaOperativa()) {
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


	abstract public boolean esAtacable();
	
	abstract public boolean esPosbibleConstruir(Construccion construccion);
	abstract public boolean agregarConstruccion(Construccion construccion);

	public boolean atacarUnidadDeLaCeldaConUnidad(Unidad unaUnidadAtacante) {
		if(!this.esAtacable())
			return false;
		
		if(this.unidad.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		return this.unidad.recibirataque(unaUnidadAtacante);
	}
}
