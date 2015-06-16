package fiuba.algo3.algocraft.modelo.unidades;

import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeRealizarAccionException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;

public abstract class UnidadTerran extends Unidad {

	@Override
	public boolean atacar(Celda unaCelda) throws NoSePuedeRealizarAccionException {
		// TODO Auto-generated method stub
		return false;
	}

	protected abstract Salud saludInicial();

	public 	abstract int turnosNecesariosParaCreacion();

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub

	}
	
	public abstract int DanioAtaque();

	public void recibirataque(Unidad unaUnidadAtacante){
		this.salud.atacar(unaUnidadAtacante.DanioAtaque());
	}


}
