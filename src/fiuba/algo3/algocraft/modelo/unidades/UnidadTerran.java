package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;

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
		}
		return true;
	}

	public Unidad crearAlucinacion() {
		return null;
	}

	public boolean atacar(Celda unaCelda) {
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean disparoRealizado = this.disparo.disparar(unaCelda);
		
		if(! disparoRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}

		
}
