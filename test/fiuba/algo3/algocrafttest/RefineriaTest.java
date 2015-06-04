package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.AlmacenGasVespano;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class RefineriaTest extends TestCase {

	public void testCrearUnaRefineria(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion refineria = new Refineria(posicion64, new Jugador(new Raza()));
		assertNotNull(refineria);
	}
	
	public void testCrearUnaRefineriaAl12TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException{
		Posicion posicion14 = new Posicion(1,4);
		Construccion refineria = new Refineria(posicion14, new Jugador(new Raza()));
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
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException {
		Posicion posicion14 = new Posicion(1,4);
		Refineria refineria = new Refineria(posicion14, new Jugador(new Raza()));
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
		
		AlmacenGasVespano almacen = new AlmacenGasVespano();
		
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);

		assertTrue(almacen.cantidad() == 40);
		
	}
}
