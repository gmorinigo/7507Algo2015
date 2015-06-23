package fiuba.algo3.algocraft.modelo.unidades.movimientos;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
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
	
	
	public boolean mover(TipoDireccion direccion) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {

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


	protected boolean moverArriba() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
        Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila()-1,posActual.dameColumna());
        
        return aplicarMovimiento(celda, posNueva);
	}
	
	protected boolean moverAbajo() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
        
        Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila() +1,posActual.dameColumna());
        
        return aplicarMovimiento(celda, posNueva);
		
	}
	
	protected boolean moverDerecha() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
        Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila(),posActual.dameColumna()+1);

        return this.aplicarMovimiento(celda, posNueva);
        
	}
	
	protected boolean moverIzquierda() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
        Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila(),posActual.dameColumna()-1);
        
        return this.aplicarMovimiento(celda, posNueva);
	}
	
	protected boolean moverDiagonalArribaDerecha() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila()-1,posActual.dameColumna()+1);
        
        return this.aplicarMovimiento(celda, posNueva);
	}
	
	protected boolean moverDiagonalArribaIzquierda() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila()-1,posActual.dameColumna()-1);
        
        return this.aplicarMovimiento(celda, posNueva);
	}
	
	protected boolean moverDiagonalAbajoDerecha() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
        Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila()+1,posActual.dameColumna()+1);
        
        return this.aplicarMovimiento(celda, posNueva);
        
	}
	
	protected boolean moverDiagonalAbajoIzquierda() throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Celda celda = this.unidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila()+1,posActual.dameColumna()-1);
        
        return aplicarMovimiento(celda, posNueva);
	}

	protected boolean aplicarMovimiento(Celda celda, Posicion posNueva) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Mapa mapa = Mapa.getInstance();
		Celda nuevaCelda = mapa.dameCelda(posNueva);
        boolean agregado = nuevaCelda.agregarUnidad(this.unidad);
        if(agregado) {
        	celda.eliminarUnidad();
        	this.unidad.setCelda(mapa.dameCelda(posNueva));
        	return true;
        }
        
		return false;
	}
	
}
