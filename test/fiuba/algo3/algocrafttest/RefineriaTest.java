package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Construccion;
import fiuba.algo3.algocraft.Posicion;
import fiuba.algo3.algocraft.Refineria;
import fiuba.algo3.algocraft.Turno;
import junit.framework.TestCase;

public class RefineriaTest extends TestCase {

	public void testCrearUnaRefineria(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion refineria = new Refineria(posicion64);
		assertNotNull(refineria);
	}
	
	public void testCrearUnaRefineriaAl12TurnosEstaCreada(){
		Posicion posicion14 = new Posicion(1,4);
		Construccion refineria = new Refineria(posicion14);
		Turno unTurno = new Turno(8);
		refineria.crearEstructura(unTurno);
		assertFalse(refineria.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(refineria.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(refineria.estaTerminada());
	}
	
	public void testComanzarARecolectar() {
		Posicion posicion14 = new Posicion(1,4);
		Construccion refineria = new Refineria(posicion14);
		Turno unTurno = new Turno(8);
		refineria.crearEstructura(unTurno);
		assertFalse(refineria.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(refineria.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(refineria.estaTerminada());
	}
}
