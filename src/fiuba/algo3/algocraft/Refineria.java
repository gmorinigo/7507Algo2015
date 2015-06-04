package fiuba.algo3.algocraft;

public class Refineria extends Construccion implements TurnoObserver{
	
	private int tamanio = 2;

	
	public Refineria(Posicion unaPosicion){
		super(unaPosicion);
	}

	@Override
	public boolean estaTerminada() {
		return this.estaOperativa;
	}

	@Override
	public void finDeTurnos() {
		this.terminarConstruccion();
	}

	@Override
	public void terminarConstruccion() {
		this.estaOperativa = true;
	}

}