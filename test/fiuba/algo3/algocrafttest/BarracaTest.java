package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Barraca;
import fiuba.algo3.algocraft.Construccion;
import fiuba.algo3.algocraft.Posicion;
import fiuba.algo3.algocraft.Turno;
import junit.framework.TestCase;

public class BarracaTest extends TestCase {

	public void testCrearUnaBarraca(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion unaBarraca = new Barraca(posicion64);
		assertNotNull(unaBarraca);
	}
	
	public void testCrearUnaBarracaAlPasar12TurnosEstaCreada(){
		Posicion posicion14 = new Posicion(1,4);
		Construccion unaBarraca = new Barraca(posicion14);
		Turno unTurno = new Turno(12);
		unaBarraca.crearEstructura(unTurno);
		assertFalse(unaBarraca.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unaBarraca.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaBarraca.estaTerminada());
	}
	
	public void testCrearMarineAlPasar3TurnosEstaCreadaEstaFinalizada(){
		Posicion posicion123 = new Posicion(12,3);
		Barraca unaBarraca = new Barraca(posicion123);
		Turno unTurno = new Turno(12);
		unaBarraca.crearEstructura(unTurno);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		
		Turno turnoParaUnidad = unaBarraca.crearMarine();
		assertTrue(unaBarraca.estaTrabajando());
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		assertFalse(unaBarraca.estaTrabajando());
	}
}
