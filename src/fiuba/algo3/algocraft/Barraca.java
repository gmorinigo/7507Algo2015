package fiuba.algo3.algocraft;

import java.util.ArrayList;

public class Barraca extends Construccion implements TurnoObserver{
	
	private int tamanio = 2;
	protected ArrayList<Unidad> unidadesEnProceso;
	
	public Barraca(Posicion unaPosicion){
		super(unaPosicion);
		this.unidadesEnProceso = new ArrayList<Unidad>();
	}

	@Override
	public boolean estaTerminada() {
		return this.estaOperativa;
	}

	@Override
	public void finDeTurnos() {
		this.terminarConstruccion();
	}

	@Override
	public void terminarConstruccion() {
		this.estaOperativa = true;
	}
	
	public Turno crearMarine() {
		Turno turno = new Turno(3);
		Marine marine = new Marine(this);
		marine.crearUnidad(turno);
		
		this.unidadesEnProceso.add(marine);
		
		return turno;
	}

	public boolean estaTrabajando() {
		return ! this.unidadesEnProceso.isEmpty(); 
	}

	public void finalizarUnidad(Unidad unidad) {
		this.unidadesEnProceso.remove(unidad);
	}

}
