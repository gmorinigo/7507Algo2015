package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class SaludProtoss extends Salud{
	
	protected int salud;
	protected int escudo;
	
	public SaludProtoss(int hp, int escudo) {
		super(hp, hp);
		this.escudo = escudo;
	}

	@Override
	public boolean atacar(int cantidad) {
		// TODO Auto-generated method stub
		return false;
	}
}
