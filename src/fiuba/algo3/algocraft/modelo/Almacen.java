package fiuba.algo3.algocraft.modelo;

public class Almacen {
	
	protected int cantidad;
	
	public Almacen() {
		this.cantidad = 0;
	}
	
	public void almacenarRecurso(int cantidad) {
		this.cantidad += cantidad;
	}
	
	public int cantidad() {
		return cantidad;
	}
}
