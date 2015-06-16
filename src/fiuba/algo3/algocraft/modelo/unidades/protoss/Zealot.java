package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Zealot extends UnidadProtoss{

	public Zealot() {
		this.tamanioTransporte = 2;
	}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	public int turnosNecesariosParaCreacion() {
		return 4;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}
	
	public int DanioAtaque() {
		return 8;
	} 

}
