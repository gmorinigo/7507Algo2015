package fiuba.algo3.algocraft.modelo.turnos;

import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoTormentaPsionica;

public class Turno {
	
	private static Turno INSTANCE = null;

	protected int turno;	
	protected Jugador jugador1;
	protected Jugador jugador2;
	protected Jugador jugadorConTurno;
	protected ArrayList<TurnoObserver> observadores;
	protected boolean partidaEnProceso;
	
	public synchronized static Turno getInstance() {
		return INSTANCE;
	}
	public synchronized static Turno getInstance(Jugador jugador1, Jugador jugador2) {
		if (INSTANCE == null)
			INSTANCE = new Turno(jugador1, jugador2);
		return INSTANCE;
	}
	
	public Turno(Jugador jugador1, Jugador jugador2) {
		this.turno = 1;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		jugador1.setRival(jugador2);
		jugador2.setRival(jugador1);
		this.jugadorConTurno = jugador1;
		this.observadores = new ArrayList<TurnoObserver>();
		this.partidaEnProceso = false;
		INSTANCE = this;
	}

	public void avanzarTurno(){
		if(this.partidaEnProceso && !this.jugadoresEstanEnJuego())
			return;
		Iterator<TurnoObserver> it = this.observadores.iterator();
		
		while (it.hasNext()) {
			try {
				it.next().finDeTurno(this);
				if (it instanceof Unidad){
					Unidad unaUnidad = (Unidad) it;
					if (!unaUnidad.estaViva()) it.remove();
				}
				if (it instanceof Construccion){
					Construccion unaConstruccion = (Construccion) it;
					if (!unaConstruccion.estaViva()) it.remove();					
				}
				if (it instanceof DisparoTormentaPsionica){
					DisparoTormentaPsionica unDisparoTormentaPsionica = (DisparoTormentaPsionica) it;
					if (unDisparoTormentaPsionica.turnosRestantes()==0) it.remove();	
				}
			} catch (MaximaCapacidadDeTransporteSuperadaException| NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e) {

			}	
		}
		
		this.jugadorConTurno = (this.jugadorConTurno == this.jugador1) ? this.jugador2 : this.jugador1;
		this.turno++;
	}
	
	protected boolean jugadoresEstanEnJuego() {
		return (this.jugador1.estaEnJuego() && this.jugador2.estaEnJuego());
	}

	public void addObserver(TurnoObserver observer) {
		this.observadores.add(observer);
	}

	public void removeObserver(TurnoObserver observer) {
		this.observadores.remove(observer);
	}
	
	public int dameTurno() {
		return this.turno;
	}
	
    public Jugador dameJugador(){
    	return this.jugadorConTurno; 	
    }
    
    public void vaciarObservers(){
    	this.observadores.clear(); 	
    }
    public void comenzar() {
    	this.partidaEnProceso = true;;
		this.jugador1.empezarPartida();
		this.jugador2.empezarPartida();
	}
    
    public synchronized static void reset() {
		INSTANCE = null;
	}

}
