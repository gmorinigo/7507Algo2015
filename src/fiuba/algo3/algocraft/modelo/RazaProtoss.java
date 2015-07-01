package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.ConstruccionFactoryProtoss;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Pilon;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryProtoss;

public class RazaProtoss extends Raza {
	
	public RazaProtoss() {
		super(new ConstruccionFactoryProtoss(), new UnidadFactoryProtoss());
		this.raza = TipoRaza.Protoss;
	}

	@Override
	public int dameCapacidadDePoblacion(ArrayList<Construccion> construccionesTerminadas) {
		int contador = 0;
		for(Construccion construccion: construccionesTerminadas){
			if ((construccion instanceof Pilon) && (contador < 200) && (construccion.estaOperativa())) contador += 5;
		}
		return contador;
	}

	public boolean esRazaProtoss() {
		return true;
	}
	    		  
}
