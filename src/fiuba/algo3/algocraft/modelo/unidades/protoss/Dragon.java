package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Dragon extends Unidad {

	public Dragon() {
		this.tamanioTransporte = 4;
	}

	@Override
	public void disparar() {
	}

	@Override
	protected Salud saludInicial() {
		return null;
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacar(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
