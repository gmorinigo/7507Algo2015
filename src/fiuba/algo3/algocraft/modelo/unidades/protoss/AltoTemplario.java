package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class AltoTemplario extends UnidadProtoss {

	public AltoTemplario() {
		this.tamanioTransporte = 2;
	}
	
	
	protected Salud saludInicial() {
		return new SaludProtoss(40,40);
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

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
