package fiuba.algo3.algocraft;


public class Marine extends Unidades {


	public boolean estaTerminado() {
		return this.estaOperativo;
	}

	@Override
	public boolean estaListoParaCrearse() {
//falta esta parte -->		return (construcciones.dameConstrucciones().contains(new Barraca()));
		return false;
	}

	@Override
	public void finDeTurnos() {
		this.estaOperativo = true;
	}

}
