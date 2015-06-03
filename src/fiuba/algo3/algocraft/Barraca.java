package fiuba.algo3.algocraft;

public class Barraca extends Construcciones{
	
	private int tamanio = 2;
	
	public Barraca(Posicion unaPosicion){
		super(unaPosicion);
	}

	@Override
	public boolean estaTerminada() {
		if ((turno.dameTurno() - turnoInicial) == 12){
			construcciones.add(celda);
			return true;
		};
		return false;
	}

}
