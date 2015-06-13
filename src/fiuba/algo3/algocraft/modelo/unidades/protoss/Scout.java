package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Scout extends Unidad {

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
	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void atacar(Posicion posicion) {
		// TODO Auto-generated method stub
		
	}

	public int turnosNecesariosParaCreacion() {
		return 9;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

}
