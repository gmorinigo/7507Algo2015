package fiuba.algo3.algocraft.modelo.unidades;

abstract public class Salud {
	
	protected int hp;
	private int salud;
	
	public Salud(int hp, int salud) {
		this.hp = hp;
		this.salud = salud;
	}
	
	public boolean tieneVida() {
		return (salud > 0);
	};
	
	public int porcentajeSalud() {
		return (this.salud *100) / this.hp;
	}
	
	public int valorDeSalud() {
		return this.salud;
	}
	
	protected void setValorDeSalud(int salud) {
		if(salud < 0 )
			this.salud = 0;
		this.salud = salud;
	};
	
	abstract public void atacar(int ataque);
	public abstract void regenerarEscudo();
	public abstract void settearSaludAlucinacionAcero();
	public abstract boolean tieneEscudo();
	public abstract void destruirEscudo();
	public abstract void SacarmeTodaLaEnergia();

}
