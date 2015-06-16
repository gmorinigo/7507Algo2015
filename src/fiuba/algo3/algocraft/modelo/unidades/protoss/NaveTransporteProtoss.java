package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Salud;


public class NaveTransporteProtoss extends NaveTransporte {

	public boolean esUnidadAerea(){
		return true;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(80,60);
	}

	public int turnosNecesariosParaCreacion() {
		return 8;
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
