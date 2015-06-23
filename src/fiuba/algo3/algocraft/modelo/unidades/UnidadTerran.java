package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public abstract class UnidadTerran extends Unidad {

	protected abstract Salud saludInicial();

	public 	abstract int turnosNecesariosParaCreacion();

	public UnidadTerran(Jugador unJugador) {
		super(unJugador);
	}
	
	protected void vivir() {
		this.estado = new UnidadEstadoViviendo(this);
	}
	
	public boolean recibirataque(Unidad unaUnidadAtacante){
		if(! this.verificarSiPuedeAtacar(unaUnidadAtacante))
			return false;
		
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
		if(! this.salud.tieneVida()) {
			this.destruirUnidad();
			Turno unTurno = Turno.getInstance();
			unTurno.removeObserver(this);
		}
		return true;
	}

	public Unidad crearAlucinacion() {
		return null;
	}

	public boolean atacar(Celda unaCelda){
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean disparoRealizado = false;;
		try {
			disparoRealizado = this.disparo.disparar(unaCelda);
		} catch (MaximaCapacidadDeTransporteSuperadaException | NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e) {
		}
		
		if(! disparoRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}

	public int obtenerCantidadEscudo(){
		return 0;
	}
	
	public boolean recibirataqueMisilEMP() {
		return false;
	}
}
