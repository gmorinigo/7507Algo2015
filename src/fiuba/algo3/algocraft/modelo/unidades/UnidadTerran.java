package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.Jugador;

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


}
