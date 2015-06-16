package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Salud;

public class NaveTransporteTerran extends NaveTransporte {

	public boolean esUnidadAerea(){
		return true;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(150);
	}
	
	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int turnosNecesariosParaCreacion() {
		return 7;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean atacar(Celda unaCelda) {
		return false;
	}
	
	public int DanioAtaque() {
		return 0;
	} 
}
