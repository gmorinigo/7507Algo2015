package fiuba.algo3.algocraft.modelo.construciones.protoss;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;

public class ArchivosTemplarios extends Construccion{

	protected ArrayList<Unidad> unidadesEnProceso;
	protected ArrayList<Unidad> unidadesFinalizadas;
	
	public ArchivosTemplarios(Posicion unaPosicion, Jugador jugador) {
		super(unaPosicion, jugador);
		this.unidadesEnProceso = new ArrayList<Unidad>();
		this.unidadesFinalizadas = new ArrayList<Unidad>();
	}
	
	public Unidad crearUnidad() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException {
		
		AbstractUnidadFactory factoryUnidades = this.jugador.dameRaza().getFactoryUnidades();
		TipoUnidad unTipoDeUnidad = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipoDeUnidad.especial1);
		
		return unaUnidad;
	}

	@Override
	public void crearEstructura(Turno unTurno) throws CeldaOcupadaException,
			NoReuneLosRequisitosException,
			ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException,
			ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		// TODO Auto-generated method stub
		
		if( (! this.reuneLosRequisitos(jugador)) || (! this.hayFabrica())) {
			throw new NoReuneLosRequisitosException();
		}
		unTurno.addObserver(this);
		//Mapa.getInstance().agregarConstruccion(this);
		jugador.agregarConstruccion(this);
	}

	
	private boolean hayFabrica() {
		for(Construccion unaConstruccion: jugador.dameConstruccionesTerminadas()){
			if (unaConstruccion instanceof PuertoEstelarP) return true;
		}
		return false;
	}
	

	@Override
	public boolean reuneLosRequisitos(Jugador jugador) {
		Almacen almacenGas = jugador.dameAlmacenGas();
		Almacen almacenMineral = jugador.dameAlmacenMineral();
		try {
			almacenGas.consumirRecurso(this.costoGas());	
			almacenMineral.consumirRecurso(this.costoMineral());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public int costoGas() {
		return 200;
	}

	@Override
	public int costoMineral() {
		return 150;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(9, this);
	}

}