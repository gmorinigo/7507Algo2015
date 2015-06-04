package fiuba.algo3.algocraft;

public class Refineria extends Construccion implements TurnoObserver{
	
	private int tamanio = 2;

	
	public Refineria(Posicion unaPosicion){
		super(unaPosicion);
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
	
/*	public Turno crearMarine() {
		Turno turno = new Turno(3);
		Marine marine = new Marine(this);
		marine.crearUnidad(turno);
		
		this.unidadesEnProceso.add(marine);
		
		return turno;
	}*/




}