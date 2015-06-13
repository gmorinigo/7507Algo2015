package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class DepositoDeSuministro extends Construccion {

	public DepositoDeSuministro(Posicion unaPosicion, Jugador jugador) {
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
		return 6;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

}
