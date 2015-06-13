package fiuba.algo3.algocraft.modelo.construciones.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class Pilon extends Construccion{
	
	public Pilon(Posicion unaPosicion, Jugador jugador) {
		super(unaPosicion, jugador);
	}


	@Override
	public int costoGas() {
		return 0;
	}

	@Override
	public int costoMineral() {	
		return 100;
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 5;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}
}
