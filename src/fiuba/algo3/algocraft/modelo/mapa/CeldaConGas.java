package fiuba.algo3.algocraft.modelo.mapa;

public class CeldaConGas extends Celda {
	public CeldaConGas(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneGas(){
		return true;
	}
}
