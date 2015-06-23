package fiuba.algo3.algocraft.modelo.unidades.ataques;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.turnos.TurnoObserver;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DisparoConRadiacion extends AbstractDisparo implements TurnoObserver{

	private boolean primerAtaqueRealizado;
	private boolean esperarTurno;
	private Unidad unidadObjetivo;
	
	public DisparoConRadiacion(Unidad unidad, int radio, Unidad unidadObjetivo) {
		super(unidad, radio);
		this.primerAtaqueRealizado = false;
		this.esperarTurno = true;
		this.unidadObjetivo = unidadObjetivo;
	}

	public boolean disparar() {
		boolean retornoAtaque = false;
		if (this.esperarTurno && this.primerAtaqueRealizado){
			this.esperarTurno = false;
			return false;
		}
		
		retornoAtaque = unidadObjetivo.dameCelda().atacarRadioDeLaCeldaConRadiacion(this.unidad);
		
		if (retornoAtaque){
			if (!this.primerAtaqueRealizado) this.primerAtaqueRealizado = true;
		}
		
		return retornoAtaque;
	}

	public void finDeTurno(Turno turno) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		this.disparar();
	}

	public boolean disparar(Celda objetivo) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		return false;
	}

	public Unidad obtenerUnidadObjetivo() {
		return this.unidadObjetivo;
	}

}
