package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Zealot extends UnidadProtoss{

	public Zealot() {
		this.tamanioTransporte = 2;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(100,60);
	}

	public int turnosNecesariosParaCreacion() {
		return 4;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}
	
	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 8;
	} 

	protected boolean atacaUnidadesAereas() {
		return false;
	} 
}
