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
	public void atacar(int ataque) {
		int ataqueEfectivo = this.absorverAtaque(ataque);
		int vida = this.valorDeSalud();
		
		this.setValorDeSalud(vida - ataqueEfectivo);
	}
	
	protected int absorverAtaque(int ataque) {
		return (this.escudo > ataque) ? 0: this.escudo - ataque;
	}
	
}
