package fiuba.algo3.algocraftTestObligatoriosPruebasEdificiosDeRecoleccion;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocrafttest.TestBase;

public class RefineriaTest extends TestBase {

	public void testCrearUnaRefineria() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion refineria = new Refineria(posicion64, new Jugador("unNombre",new RazaTerran(),"Rojo"),TipoConstruccion.extractorGas);
		assertNotNull(refineria);
	}
	
	public void testCrearUnaRefineriaALos6TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion99 = new Posicion(9,9);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Construccion refineria = new Refineria(posicion99,unJugador,TipoConstruccion.extractorGas );
		Jugador otroJugador = new Jugador("Nombre2",new RazaProtoss(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(refineria);
		unTurno.addObserver(refineria);
		
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
		Refineria refineria = new Refineria(posicion99, unJugador,TipoConstruccion.extractorGas);
		Jugador otroJugador = new Jugador("Nombre2",new RazaProtoss(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(refineria);
		unTurno.addObserver(refineria);
		
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(refineria.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(refineria.estaTerminada());
		
		for (int i = 0; i < 6; i++) {
			unTurno.avanzarTurno();
		}
		
		assertTrue(unJugador.dameAlmacenGas().cantidad() == 560);	
	}
}
