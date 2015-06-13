package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.NexoMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroMineral;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class NexoMineralTest extends TestCase{


	public void testCrearUnNexoMineral() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion centroMineral = new CentroMineral(posicion64, new Jugador("OtroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(centroMineral);
	}
	
	public void testCrearUnNexoMineralAl4TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion911 = new Posicion(9,11);
		Jugador unJugador = new Jugador("OtroNombre",new RazaProtoss(),"Rojo");
		Construccion unNexo = new NexoMineral(posicion911, unJugador );
		Jugador otroJugador = new Jugador("Nombre2",new RazaProtoss(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(unNexo);
		unTurno.addObserver(unNexo);
		
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		assertTrue(unNexo.estaTerminada());
	}
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion911 = new Posicion(9,11);
		Jugador unJugador = new Jugador("OtroNombre",new RazaProtoss(),"Rojo");
		NexoMineral unNexo = new NexoMineral(posicion911, unJugador);
		Jugador otroJugador = new Jugador("Nombre2",new RazaProtoss(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(unNexo);
		unTurno.addObserver(unNexo);
		
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unNexo.estaTerminada());
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		//Los jugadores empiezan con 500 de cada recurso
		assertTrue(unJugador.dameAlmacenMineral().cantidad() == 540);
	}
}
