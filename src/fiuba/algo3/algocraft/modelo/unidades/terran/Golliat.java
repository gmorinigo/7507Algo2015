package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Golliat extends Unidad {

	//private DisparoStrategy disparoStrategy;
	
	public Golliat() {
		this.tamanioTransporte = 2;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
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

	protected int turnosNecesariosParaCreacion() {
		return 6;
	} 
}
