package fiuba.algo3.algocraft.modelo;

import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;

public class Almacen {
	
	protected int cantidad;
	
	public Almacen() {
		this.cantidad = 0;
	}
	
	public Almacen(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	public void almacenarRecurso(int cantidad) {
		this.cantidad += cantidad;
	}
	
	public int cantidad() {
		return cantidad;
	}
	
	public boolean puedeConsumir(int cantidadAConsumir) {
		return (this.cantidad >= cantidadAConsumir) ? true: false;
	}
	
	public void consumirRecurso(int cantidad) throws NoHaySuficientesRecursos {
		if(cantidad > this.cantidad)
			throw new NoHaySuficientesRecursos();
		
		this.cantidad -= cantidad;
	}
}
