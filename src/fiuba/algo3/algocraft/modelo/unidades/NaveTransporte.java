package fiuba.algo3.algocraft.modelo.unidades;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

public abstract class NaveTransporte extends UnidadTerran {
	private ArrayList<Unidad> unidadesCargadas;
	private int capacidadCargada;
	private int capacidadMaximaCarga;
	
	public NaveTransporte(Jugador unJugador){
		super(unJugador);
		this.unidadesCargadas = new ArrayList<Unidad>();
		this.capacidadCargada = 0;
		this.tamanioTransporte = 0;
		this.capacidadMaximaCarga = 8;
	}

	protected Salud saludInicial() {
		return null;
	}
	
	public boolean esUnidadAerea(){
		return true;
	}

	public void cargarUnidad(Unidad unaUnidad) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{
		this.verificarSiEsUnaUnidadVoladora(unaUnidad);
		
		if ((capacidadMaximaCarga - capacidadCargada) >= unaUnidad.getTamanioTransporte()){
			unidadesCargadas.add(unaUnidad);
			this.capacidadCargada += unaUnidad.getTamanioTransporte(); 
		}
		else {
				throw new MaximaCapacidadDeTransporteSuperadaException();
		}
	}

	
	private void verificarSiEsUnaUnidadVoladora(Unidad unaUnidad) throws NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if (unaUnidad.esUnidadAerea()){
			throw new NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora();
		}
	}

	public int getCapacidadOcupada(){
		
		return this.capacidadCargada;
	}

	public void descargarUnidades() {
		Iterator<Unidad> it = unidadesCargadas.iterator();
		
		while (it.hasNext()) {
			Unidad unaUnidad = it.next();
			unaUnidad.dameCelda().bajarDeLaNaveDeTransporte(unaUnidad);
		}
	}
	
	public boolean recibirataque(Unidad unaUnidadAtacante){
		if(! this.verificarSiPuedeAtacar(unaUnidadAtacante))
			return false;
		
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
		if(! this.salud.tieneVida()) {
			Iterator<Unidad> it = unidadesCargadas.iterator();
			
			while (it.hasNext()) {
				Unidad unaUnidad = it.next();
				unaUnidad.destruirUnidad();
			}
			this.destruirUnidad();
		}
		return true;
	}
	
	public boolean esUnaNaveTransporte() {
		return true;
	}

	public boolean mover(TipoDireccion direccion) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if(! this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean movAplicado = this.movimiento.mover(direccion);
		if( ! movAplicado ) {
			return false;
		}
		else{
			Iterator<Unidad> it = unidadesCargadas.iterator();
			
			while (it.hasNext()) {
				Unidad unaUnidad = it.next();
				unaUnidad.setCelda(this.celda);
			}
		}
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
}
