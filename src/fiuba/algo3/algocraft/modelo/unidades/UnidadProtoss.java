package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public abstract class UnidadProtoss extends Unidad {

	protected abstract Salud saludInicial();

	public 	abstract int turnosNecesariosParaCreacion();

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub

	}
	
	public boolean recibirataque(Unidad unaUnidadAtacante){
		if(! this.verificarSiPuedeAtacar(unaUnidadAtacante))
			return false;
		
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
		return true;
	}
	
	public int obtenerCantidadEscudo(){
		SaludProtoss unaSalud = (SaludProtoss) this.salud;
		return unaSalud.valorEscudo();
	}
}
