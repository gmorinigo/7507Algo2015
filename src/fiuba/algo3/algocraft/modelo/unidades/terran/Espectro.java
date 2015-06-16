package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class Espectro extends UnidadTerran {

	
	public Espectro(){
		this.tamanioTransporte = 0;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(120);
	}

	public boolean esUnidadAerea(){
		return true;
	}


	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	public int turnosNecesariosParaCreacion() {
		return 8;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	public int DanioAtaque() {
		return 20;
	} 

}
