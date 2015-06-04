package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class RefineriaTest extends TestBase {

	public void testCrearUnaRefineria(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion refineria = new Refineria(posicion64, new Jugador("unNombre",new Raza()));
		assertNotNull(refineria);
	}
	
	public void testCrearUnaRefineriaAl12TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException{
		Posicion posicion14 = new Posicion(1,4);
		Construccion refineria = new Refineria(posicion14, new Jugador("unNombre",new Raza()));
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
		Refineria refineria = new Refineria(posicion14, new Jugador("unNombre",new Raza()));
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
		
		Almacen almacen = new Almacen();
		
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);

		assertTrue(almacen.cantidad() == 40);
		
	}
}
