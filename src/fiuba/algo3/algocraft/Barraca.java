package fiuba.algo3.algocraft;

public class Barraca extends Construcciones{
	
	private int tamanio = 2;
	
	public Barraca(Posicion unaPosicion){
		
	}

	@Override
	public boolean estaTerminada() {
		return ((turno.dameTurno() - turnoInicial) >= 12);

	}

}
