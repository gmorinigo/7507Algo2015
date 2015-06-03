package fiuba.algo3.algocraft;


public class Marine extends Unidades {

	private Barraca unaBarraca;

	public boolean estaTerminado() {
		if ((turno.dameTurno() - turnoInicial) == 3){
			unidades.add(celda);
			return true;
		};
		return false;
	}

	@Override
	public boolean estaListoParaCrearse() {
//falta esta parte -->		return (construcciones.dameConstrucciones().contains(new Barraca()));
		return false;
	}

}
