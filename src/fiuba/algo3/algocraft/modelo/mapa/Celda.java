package fiuba.algo3.algocraft.modelo.mapa;


public abstract class Celda {
	private Posicion posicion;
	private boolean estaOcupada;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		this.posicion = unaPosicion;
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
	
	
	public boolean tieneGas(){
		return false;
	}

	
	public boolean tieneMineral(){
		return false;
	}
}
