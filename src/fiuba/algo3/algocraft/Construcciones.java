package fiuba.algo3.algocraft;

import java.util.ArrayList;

public abstract class Construcciones {

	protected int turnoInicial;
	protected Turno turno;
	protected ArrayList<Celda> construcciones;
	protected Celda celda;
	
	
	public Construcciones(Posicion unaPosicion) {
		Celda unaCelda = new Celda(unaPosicion.dameFila(),unaPosicion.dameColumna());
		construcciones = new ArrayList<Celda>();
		celda = unaCelda;
	}

	public abstract boolean estaTerminada();

	public void crearEstructura(Turno unTurno) {
		turnoInicial = unTurno.dameTurno();
		turno = unTurno;
	}
	
	public ArrayList<Celda> dameConstrucciones(){
		return construcciones;
	}

}
