package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class DepositoDeSuministro extends Construccion {

	public DepositoDeSuministro(Posicion unaPosicion, Jugador jugador) {
		super(unaPosicion, jugador);
	}

	@Override
	public void crearEstructura(Turno unTurno) throws CeldaOcupadaException,NoReuneLosRequisitosException,ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException,ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		if( ! this.reuneLosRequisitos(jugador)) {
			throw new NoReuneLosRequisitosException();
		}
		unTurno.addObserver(this);
		//Mapa.getInstance().agregarConstruccion(this);
		jugador.agregarConstruccion(this);
	}

	@Override
	public boolean reuneLosRequisitos(Jugador jugador) {
		Almacen almacenGas = jugador.dameAlmacenGas();
		try {
			almacenGas.consumirRecurso(this.costoMineral());	
		} catch (Exception e) {
			return false;
		}
		return true;
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
