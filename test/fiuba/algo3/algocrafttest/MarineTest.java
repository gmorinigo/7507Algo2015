package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Barraca;
import fiuba.algo3.algocraft.Marine;
import fiuba.algo3.algocraft.Posicion;
import fiuba.algo3.algocraft.Turno;
import fiuba.algo3.algocraft.Unidades;
import junit.framework.TestCase;

public class MarineTest extends TestCase {
	
	public void testCrearMarine(){
		Posicion posicion35 = new Posicion(3,5);
		Barraca unaBarraca = new Barraca(posicion35);
		Unidades unMarine = new Marine();
		assertNotNull(unMarine);
	}
	public void testCrearMarineSeCreaCuandoUnaBarracaEstaFinalizada(){
		Posicion posicion123 = new Posicion(12,3);
		Barraca unaBarraca = new Barraca(posicion123);
		Marine unMarine = new Marine(); 
		Turno unTurno = new Turno();
		unaBarraca.agregarTurno(unTurno);
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
		assertFalse(unMarine.estaListoParaCrearse());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unMarine.estaListoParaCrearse());
		unMarine.crearMarine();
		assertFalse(unMarine.estaTerminado());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unMarine.estaTerminado());
	}
}
