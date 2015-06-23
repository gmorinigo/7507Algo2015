package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoTormentaPsionica extends AbstractDisparo implements TurnoObserver{

	private Celda celdaObjetivo;
	private int turnosRestantes = 2;
	private boolean primerAtaqueRealizado;
	private boolean esperarTurno;
	
	public DisparoTormentaPsionica(Unidad unidad, int radio, Celda unaCelda) {
		super(unidad, radio);
		this.celdaObjetivo = unaCelda;
		this.primerAtaqueRealizado = false;
		this.esperarTurno = true;
	}

	public boolean disparar(Celda celdaObjetivo) {
		boolean retornoAtaque = false;
		if (this.turnosRestantes > 0){
			if (this.esperarTurno && this.primerAtaqueRealizado){
				this.esperarTurno = false;
				return false;
			}
			
			retornoAtaque = celdaObjetivo.atacarRadioDelaCeldaConTormentaPsionica(this.unidad);
			if (retornoAtaque){
				if (!this.primerAtaqueRealizado) this.primerAtaqueRealizado = true;
				this.turnosRestantes--;
			}
		}
		/*if (this.turnosRestantes == 0){
			Turno unTurno = Turno.getInstance();
			unTurno.removeObserver(this);
		}*/
		
		return retornoAtaque;
	}

	public void finDeTurno(Turno turno) {
		this.disparar(celdaObjetivo);
	}


	public int turnosRestantes () {
		return this.turnosRestantes;
	}
	
}

