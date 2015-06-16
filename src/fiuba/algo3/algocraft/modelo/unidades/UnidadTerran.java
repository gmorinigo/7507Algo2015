package fiuba.algo3.algocraft.modelo.unidades;

public abstract class UnidadTerran extends Unidad {

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
