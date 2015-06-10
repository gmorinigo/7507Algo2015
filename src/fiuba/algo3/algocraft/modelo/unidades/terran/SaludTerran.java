package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class SaludTerran extends Salud {

	public SaludTerran(int hp) {
		super(hp, hp);
	}

	@Override
	public void atacar(int ataque) {
		int vida = this.valorDeSalud();
		this.setValorDeSalud(vida - ataque);
	}
}
