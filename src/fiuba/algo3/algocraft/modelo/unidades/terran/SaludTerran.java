package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class SaludTerran extends Salud {

	public SaludTerran(int hp) {
		super(hp, hp);
	}

	public void atacar(int ataque) {
		int vida = this.valorDeSalud();
		if (vida >= ataque){
			this.setValorDeSalud(vida - ataque);
		}
		else{
			this.setValorDeSalud(0);	
		}
	}

	public void regenerarEscudo() {
		// No regenero escudo, solamente en los protoss
	}

	public void settearSaludAlucinacionAcero() {
		// Este metodo no tiene sentido en los terran. Es para las alucionaciones de los protoss		
	}

	public boolean tieneEscudo() {
		return false;
	}
}
