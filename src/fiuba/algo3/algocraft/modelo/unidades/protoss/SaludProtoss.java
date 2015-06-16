package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class SaludProtoss extends Salud{
	
	protected int salud;
	protected int escudo;
	
	public SaludProtoss(int hp, int escudo) {
		super(hp, hp);
		this.escudo = escudo;
	}

	public void atacar(int ataque) {
		int ataqueEfectivo = this.absorverAtaque(ataque);
		int vida = this.valorDeSalud();
		
		if (vida >= ataqueEfectivo){
			this.setValorDeSalud(vida - ataqueEfectivo);
		}
		else{
			this.setValorDeSalud(0);	
		}
	}
	
	protected int absorverAtaque(int ataque) {
		int ataqueEfectivo = 0;
		if (this.escudo > ataque){
			this.escudo = this.escudo - ataque;
		}
		else {
			ataqueEfectivo = ataque - this.escudo;
			this.escudo = 0;
		}
		return ataqueEfectivo;
	}

	public void regenerarEscudo(int cantidadDeEscudoARegenerar) {
		if ((this.escudo + cantidadDeEscudoARegenerar) > this.hp ){
			this.escudo = this.hp;
		}
		else {
			this.escudo = this.escudo + cantidadDeEscudoARegenerar;
		}
	}

	public int valorEscudo() {
		return (this.escudo);
	}
	
	public int porcentajeEscudo() {
		return (this.escudo *100) / this.hp;
	}

	public void pasarTurno() {
		this.regenerarEscudo(5);
	}
	
}

