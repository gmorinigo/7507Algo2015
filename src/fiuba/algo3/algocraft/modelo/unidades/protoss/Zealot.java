package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Zealot extends UnidadProtoss{

	public Zealot(Jugador unJugador) {
		super(unJugador);
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
	
	public int DanioAtaque(Construccion construccion) {
		return 8;
	}
	
	protected boolean atacaUnidadesAereas() {
		return false;
	}

	public int getRangoAtaque(Unidad unidad) {
		return 1;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 1;
	} 
}
