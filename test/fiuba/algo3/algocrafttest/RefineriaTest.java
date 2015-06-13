package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class RefineriaTest extends TestBase {

	public void testCrearUnaRefineria() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion refineria = new Refineria(posicion64, new Jugador("unNombre",new RazaTerran(),"Rojo"));
		assertNotNull(refineria);
	}
	
	public void testCrearUnaRefineriaALos6TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Construccion refineria = new Refineria(posicion99,unJugador );
		Turno unTurno = new Turno(unJugador);
	
		refineria.crearEstructura(unTurno);
		
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(refineria.estaTerminada());
	}
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Refineria refineria = new Refineria(posicion99, unJugador);
		Turno unTurno = new Turno(unJugador);
		
		refineria.crearEstructura(unTurno);
		
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(refineria.estaTerminada());
		
		Almacen almacen = new Almacen();
		
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);
		refineria.recolectar(almacen);

		assertTrue(almacen.cantidad() == 40);	
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaTerran(),mineral, gas);
		Refineria unaRefineria = new Refineria(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unaRefineria.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
}
