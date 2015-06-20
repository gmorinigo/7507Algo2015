package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoAlucinacion extends AbstractDisparo {

	public DisparoAlucinacion(Unidad unidad, int radio) {
		super(unidad, radio);
	}

	public boolean disparar(Celda objetivo) {	
		return (objetivo.atacarUnidadDelaCeldaConAlucionacion(this.unidad));
	}

}
