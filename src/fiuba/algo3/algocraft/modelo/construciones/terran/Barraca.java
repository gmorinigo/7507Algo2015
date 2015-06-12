package fiuba.algo3.algocraft.modelo.construciones.terran;

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
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Barraca extends Construccion {

	protected ArrayList<Unidad> unidadesEnProceso;
	protected ArrayList<Unidad> unidadesFinalizadas;
	
	public Barraca(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
		this.unidadesEnProceso = new ArrayList<Unidad>();
		this.unidadesFinalizadas = new ArrayList<Unidad>();
	}

	
	public Unidad crearUnidad() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException {
		// Obtener dinamicamente la factory
		AbstractUnidadFactory factoryUnidades = this.jugador.dameRaza().getFactoryUnidades();
		TipoUnidad unTipoDeUnidad = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipoDeUnidad.terrestre1);
		
		return unaUnidad;
	}

	public boolean estaTrabajando() {
		return ! this.unidadesEnProceso.isEmpty(); 
	}

	public void finalizarUnidad(Unidad unidad) {
		this.unidadesEnProceso.remove(unidad);
		this.unidadesFinalizadas.add(unidad);
	}

	public Unidad dameUnidad() {
		Unidad unidad = this.unidadesFinalizadas.get(this.unidadesFinalizadas.size()-1);
		this.unidadesFinalizadas.remove(unidad);
		return unidad;
	}

	@Override
	public boolean reuneLosRequisitos(Jugador jugador) {
		Almacen almacenMineral = jugador.dameAlmacenMineral();
		try {
			almacenMineral.consumirRecurso(this.costoMineral());	
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
		return 150;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(12, this);
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
		
	
}
