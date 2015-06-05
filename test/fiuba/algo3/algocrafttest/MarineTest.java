package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Marine;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class MarineTest extends TestBase {
	
	public void testCrearMarine(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion unaBarraca = new Barraca(posicion64, new Jugador("unNombre",new Raza()));
		unaBarraca.terminarConstruccion();
		
		Unidad unMarine = new Marine((Barraca)unaBarraca);
		assertNotNull(unMarine);
	}
	public void testCrearMarineAlPasar3TurnosEstaFinalizada(){
		Posicion posicion64 = new Posicion(6,4);
		Jugador jugador = new Jugador("pepe",new Raza());
		Construccion unaBarraca = new Barraca(posicion64, jugador);
		unaBarraca.terminarConstruccion();
	
		Unidad unMarine = new Marine((Barraca)unaBarraca); 
		assertTrue(jugador.verificarPoblacion());
		Turno turnoParaUnidad = new Turno(3);
		unMarine.crearUnidad(turnoParaUnidad);
		assertFalse(unMarine.estaTerminado());
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		assertTrue(unMarine.estaTerminado());
	}
}
