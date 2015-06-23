package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class SaludProtoss extends Salud{
	
	//protected int salud;
	protected int escudo;
	protected int escudoInicial;
	
	public SaludProtoss(int hp, int escudo) {
		super(hp, hp);
		this.escudo = escudo;
		this.escudoInicial = escudo;
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

	public void regenerarEscudo() {
		if (this.escudo == this.escudoInicial) return;
		
		if ((this.escudo + 5) >= this.escudoInicial){
			this.escudo = escudoInicial;
		} else {
			this.escudo += 5;	
		}
	}

	public void settearSaludAlucinacionAcero() {
		this.hp = 0;
		this.salud = 0;
	}

	public boolean tieneEscudo() {
		return (escudo > 0);	}
	
	public void destruirEscudo(){
		this.escudo = 0;
	}	
	
}
