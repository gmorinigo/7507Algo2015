package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoTormentaPsionica extends AbstractDisparo implements TurnoObserver{

	public DisparoTormentaPsionica(Unidad unidad, int radio) {
		super(unidad, radio);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean disparar(Celda objetivo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finDeTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}

}
