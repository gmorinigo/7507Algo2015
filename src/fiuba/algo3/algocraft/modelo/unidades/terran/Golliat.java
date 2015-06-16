package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class Golliat extends UnidadTerran {

	//private DisparoStrategy disparoStrategy;
	
	public Golliat() {
		this.tamanioTransporte = 2;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	@Override
	protected Salud saludInicial() {
		return new SaludTerran(125);
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
		return 10;
	} 
}
