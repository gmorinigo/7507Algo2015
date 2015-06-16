package fiuba.algo3.algocraft.modelo.unidades.movimientos;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Movimiento {
	
	protected int cantidadPasosPorTurno;
	protected Unidad unidad;
	
	public enum TipoDireccion{
		Arriba,Abajo,Derecha,Izquierda,
		DiagonalAbajoDerecha,DiagonalAbajoIzquierda,DiagonalArribaDerecha,DiagonalArribaIzquierda
	}
	
	public Movimiento(Unidad unidad) {
		this.unidad = unidad;
	}
	
	
	public boolean mover(TipoDireccion direccion) {

		switch (direccion) {
		case Arriba:
			return this.moverArriba();
		case Abajo:
			return this.moverAbajo();
		case Izquierda:
			return this.moverIzquierda();
		case Derecha:
			return this.moverDerecha();
		case DiagonalArribaIzquierda:
			return this.moverDiagonalArribaIzquierda();
		case DiagonalArribaDerecha:
			return this.moverDiagonalArribaDerecha();
		case DiagonalAbajoIzquierda:
			return this.moverDiagonalAbajoIzquierda();
		case DiagonalAbajoDerecha:
			return this.moverDiagonalAbajoDerecha();
		default:
			break;
		}
		
		return false;
		
	}


	protected boolean moverArriba() {
		return true;
	}
	
	protected boolean moverAbajo() {
		return false;
		
	}
	
	protected boolean moverDerecha() {
		return false;
		
	}
	
	protected boolean moverIzquierda() {
		return false;
	}
	
	protected boolean moverDiagonalArribaDerecha() {
		return false;
	}
	
	protected boolean moverDiagonalArribaIzquierda() {
		return false;
		
	}
	
	protected boolean moverDiagonalAbajoDerecha() {
		return false;
	}
	
	protected boolean moverDiagonalAbajoIzquierda() {
		return false;	
	}

}
