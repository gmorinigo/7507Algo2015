package fiuba.algo3.algocraft;

public class Barraca extends Construccion implements TurnoObserver{
	
	private int tamanio = 2;
	
	public Barraca(Posicion unaPosicion){
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

	private void terminarConstruccion() {
		this.estaOperativa = true;
	}
	
	public Unidad crearMarine() {
		return null;
	}

}
