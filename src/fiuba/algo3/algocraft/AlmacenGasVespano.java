package fiuba.algo3.algocraft;

public class AlmacenGasVespano {
	
	protected int cantidad;
	
	public AlmacenGasVespano() {
		this.cantidad = 0;
	}
	
	public void almacenarGas(int cantidad) {
		this.cantidad += cantidad;
	}
	
	public int cantidad() {
		return cantidad;
	}
}
