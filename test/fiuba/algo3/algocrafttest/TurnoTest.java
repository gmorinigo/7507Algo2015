package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class TurnoTest extends TestCase {
	
	public void testCrearUnTurnoEsElPrimero(){
		Turno unTurno = new Turno();
		assertTrue(unTurno.dameTurno() == 1);
	}
	
	public void testAumentarUnTurnoAumentaElTurno(){
		Turno unTurno = new Turno();
		unTurno.aumentarTurno();
		assertTrue(unTurno.dameTurno() == 2);
	}
}
