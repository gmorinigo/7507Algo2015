package fiuba.algo3.algocraft.modelo.construciones;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class Refineria extends ExtractorDeGas{
	
	public Refineria(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	// TODO Me parece que esto puede ir a la clase madre
	public boolean reuneLosRequisitos(Jugador jugador) {
		Almacen almacenGas = jugador.dameAlmacenGas();
		try {
			almacenGas.consumirRecurso(this.costoMineral());	
		} catch (Exception e) {
			return false;
		}
		return true;
	}
/* lo mando a la clase madre
	@Override
	public int costoGas() {
		return 0;
	}

	@Override
	public int costoMineral() {
		return 100;
	}
*/

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(8, this);
	}

	@Override
	public void crearEstructura(Turno unTurno) throws CeldaOcupadaException,
			NoReuneLosRequisitosException,
			ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException,
			ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		if( ! this.reuneLosRequisitos(jugador)) {
			throw new NoReuneLosRequisitosException();
		}
		
		unTurno.addObserver(this);
		//Mapa.getInstance().agregarConstruccion(this);
		jugador.agregarConstruccion(this);
	}
		
	
}