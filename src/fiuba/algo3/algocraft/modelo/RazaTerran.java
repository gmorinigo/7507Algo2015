package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.ConstruccionFactoryTerran;
import fiuba.algo3.algocraft.modelo.construciones.terran.DepositoDeSuministro;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;


public class RazaTerran extends Raza{

	public RazaTerran() {
		super(new ConstruccionFactoryTerran(), new UnidadFactoryTerran());
	}

	@Override
	public int dameCapacidadDePoblacion(ArrayList<Construccion> construccionesTerminadas) {
		int contador = 0;
		for(Construccion construccion: construccionesTerminadas){
			if (construccion instanceof DepositoDeSuministro) contador += 5;
		}
		return contador;
	}
	    		  
}
