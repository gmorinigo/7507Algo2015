package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
//import fiuba.algo3.algocraft.modelo.mapa.Celda;
//import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class CentroDeMineral extends ExtractorDeMineral{
	
	public CentroDeMineral(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);		
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

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 4;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

}
