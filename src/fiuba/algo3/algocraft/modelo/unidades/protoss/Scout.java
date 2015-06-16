package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Scout extends UnidadProtoss {

	public Scout() {	
		this.tamanioTransporte = 0;
	}
	
	@Override
	protected Salud saludInicial() {
		return null;
	}

	public boolean esUnidadAerea(){
		return true;
	}

	public int turnosNecesariosParaCreacion() {
		return 9;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	public int DanioAtaque() {
		return 14;
	} 

}
