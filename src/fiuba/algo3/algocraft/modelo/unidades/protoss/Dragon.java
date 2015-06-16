package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Dragon extends UnidadProtoss {

	public Dragon() {
		this.tamanioTransporte = 4;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(80,100);
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	public int turnosNecesariosParaCreacion() {
		return 6;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	public int DanioAtaque() {
		return 20;
	} 

}
