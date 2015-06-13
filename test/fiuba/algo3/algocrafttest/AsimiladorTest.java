package fiuba.algo3.algocrafttest;

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

public class AsimiladorTest extends TestBase{
	
	public void testCrearUnAsimilador() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion asimilador = new Asimilador(posicion64, new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(asimilador);
	}
	
	public void testCrearUnAsimiladorALos6TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Construccion asimilador = new Asimilador(posicion99,unJugador );
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(asimilador);
		unTurno.addObserver(asimilador);
		
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
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(asimilador);
		unTurno.addObserver(asimilador);
		
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(asimilador.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(asimilador.estaTerminada());
		
		for (int i = 0; i < 8; i++) {
			unTurno.avanzarTurno();
		}
		
		assertTrue(unJugador.dameAlmacenGas().cantidad() == 580);	
	}
}
