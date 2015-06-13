package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Asimilador;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class AsimiladorTest extends TestCase{
	
	public void testCrearUnAsimilador() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion asimilador = new Asimilador(posicion64, new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(asimilador);
	}
	
	public void testCrearUnAsimiladorALos6TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Construccion asimilador = new Asimilador(posicion99,unJugador );
		Turno unTurno = new Turno(unJugador);
	
		asimilador.crearEstructura(unTurno);
		
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(asimilador.estaTerminada());
	}
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Asimilador asimilador = new Asimilador(posicion99, unJugador);
		Turno unTurno = new Turno(unJugador);
		
		asimilador.crearEstructura(unTurno);
		
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(asimilador.estaTerminada());
		
		Almacen almacen = new Almacen();
		
		asimilador.recolectar(almacen);
		asimilador.recolectar(almacen);
		asimilador.recolectar(almacen);
		asimilador.recolectar(almacen);

		assertTrue(almacen.cantidad() == 40);	
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		Asimilador asimilador = new Asimilador(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			asimilador.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	

}
