package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoTormentaPsionica extends AbstractDisparo implements TurnoObserver{

	private Celda celdaObjetivo;
	private int turnosRestantes = 2;
	
	public DisparoTormentaPsionica(Unidad unidad, int radio, Celda unaCelda) {
		super(unidad, radio);
		this.celdaObjetivo = unaCelda;
		
	}

	public boolean disparar(Celda celdaObjetivo) {
		boolean retornoAtaque = false;
		if (this.turnosRestantes > 0){
			retornoAtaque = celdaObjetivo.atacarRadioDelaCeldaConTormentaPsionica(this.unidad);
			if (retornoAtaque){
				this.turnosRestantes--;
			}
		}
		return retornoAtaque;
	}

	public void finDeTurno(Turno turno) {
		this.disparar(celdaObjetivo);
	}

}
