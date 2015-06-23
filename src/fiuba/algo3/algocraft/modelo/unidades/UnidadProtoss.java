package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public abstract class UnidadProtoss extends Unidad {

	protected abstract Salud saludInicial();

	public 	abstract int turnosNecesariosParaCreacion();

	protected void vivir() {
		this.salud.regenerarEscudo();
		this.estado = new UnidadEstadoViviendo(this);
	}
	
	public UnidadProtoss(Jugador unJugador) {
		super(unJugador);
	}
	
	public boolean recibirataque(Unidad unaUnidadAtacante){
		if(! this.verificarSiPuedeAtacar(unaUnidadAtacante))
			return false;
		
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
		if((!this.salud.tieneVida() && !this.esUnaAlucinacion) || (!this.salud.tieneEscudo()&& this.esUnaAlucinacion)) {
			this.destruirUnidad();
			Turno unTurno = Turno.getInstance();
			unTurno.removeObserver(this);
		}
		return true;
	}
	
	public int obtenerCantidadEscudo(){
		SaludProtoss unaSalud = (SaludProtoss) this.salud;
		return unaSalud.valorEscudo();
	}

	public boolean estaViva(){
		if (this.esUnaAlucinacion){
			return this.salud.tieneEscudo();
		} else{
			return this.salud.tieneVida();
		}
	}
	
	public boolean atacar(Celda unaCelda)  {
		if (this.esUnaAlucinacion()) {
			return true;
		}
		
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean disparoRealizado = false;
		try {
			disparoRealizado = this.disparo.disparar(unaCelda);
		} catch (MaximaCapacidadDeTransporteSuperadaException | NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e) {
		}		
		if(! disparoRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
	public boolean recibirataqueMisilEMP(){
		this.salud.destruirEscudo();
		return true; 
	}	

}
