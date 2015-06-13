package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;


public class Marine extends Unidad {

	//private DisparoStrategy disparoStrategy;


	public Marine() {
		this.tamanioTransporte = 1;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
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
	public void atacar(Posicion posicion) {
		// TODO Auto-generated method stub
		
	}

	public int turnosNecesariosParaCreacion() {
		return 3;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
	}
}
