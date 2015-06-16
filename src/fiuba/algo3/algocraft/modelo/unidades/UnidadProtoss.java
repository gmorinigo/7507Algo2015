package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public abstract class UnidadProtoss extends Unidad {

	protected abstract Salud saludInicial();

	public 	abstract int turnosNecesariosParaCreacion();

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub

	}
	
	public void recibirataque(Unidad unaUnidadAtacante){
		this.salud.atacar(unaUnidadAtacante.DanioAtaque(this));
	}
	
	public int obtenerCantidadEscudo(){
		SaludProtoss unaSalud = (SaludProtoss) this.salud;
		return unaSalud.valorEscudo();
	}
}
