package fiuba.algo3.algocraft.modelo.unidades;

abstract public class Salud {
	
	protected int hp;
	protected int salud;
	
	public Salud(int hp, int salud) {
		this.hp = hp;
		this.salud = salud;
	}
	
	public boolean tieneVida() {
		return (salud == 0);
	};
	
	abstract public boolean atacar(int cantidad);
}
