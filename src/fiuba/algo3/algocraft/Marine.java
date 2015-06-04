package fiuba.algo3.algocraft;


public class Marine extends Unidad {

	protected Barraca barraca;

	public Marine(Barraca barraca) {
		super();
		this.barraca = barraca;
	}

	public boolean estaTerminado() {
		return this.estaOperativo;
	}

	@Override
	public void finDeTurnos() {
		this.estaOperativo = true;
		this.barraca.finalizarUnidad(this);
	}

}
