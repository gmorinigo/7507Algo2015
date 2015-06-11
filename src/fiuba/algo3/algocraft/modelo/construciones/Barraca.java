package fiuba.algo3.algocraft.modelo.construciones;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;

public class Barraca extends Construccion {
	
	//private int tamanio = 2;
	protected ArrayList<Unidad> unidadesEnProceso;
	protected ArrayList<Unidad> unidadesFinalizadas;
	
	public Barraca(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
		this.unidadesEnProceso = new ArrayList<Unidad>();
		this.unidadesFinalizadas = new ArrayList<Unidad>();
	}
	
	public Turno crearMarine() {
		Turno turno = new Turno();
		// Obtener dinamicamente la factory
		
		AbstractUnidadFactory factoryUnidades = turno.dameJugador().dameRaza().getFactoryUnidades();
		//new UnidadFactoryTerran();//getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("Marine");
		//unaUnidad.crearUnidad(turno);
		
		this.unidadesEnProceso.add(unaUnidad);
		
		return turno;
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
		return 150;
	}

	@Override
	public void finDeTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}

}
