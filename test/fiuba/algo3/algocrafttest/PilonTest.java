package fiuba.algo3.algocrafttest;

import junit.framework.TestCase;
import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Pilon;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class PilonTest extends TestCase{
	public void testCrearUnDepositoDeSuministro() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion unPilon = new Pilon(posicion64, new Jugador("unNombre",new RazaTerran(),"Azul"));
		assertNotNull(unPilon);
	}
	
	public void testCrearUnPilonAlPasar5TurnosEstaCreada() 
			throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
				Posicion posicion124 = new Posicion(12,4);
				Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
				Construccion unPilon = new Pilon(posicion124, unJugador );
				Turno unTurno = new Turno(unJugador);
				
				unPilon.crearEstructura(unTurno);
				assertFalse(unPilon.estaTerminada());
				unTurno.avanzarTurno();
				unTurno.avanzarTurno();
				unTurno.avanzarTurno();
				assertFalse(unPilon.estaTerminada());
				unTurno.avanzarTurno();
				unTurno.avanzarTurno();
				assertTrue(unPilon.estaTerminada());
			}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		Pilon unPilon = new Pilon(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unPilon.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
	public void testAlCrear2PilonElLimiteDePoblacionAumenta10() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
			Posicion posicion124 = new Posicion(12,4);
			Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
			Pilon unPilon = new Pilon(posicion124, unJugador );
			Turno unTurno = new Turno(unJugador);

			unPilon.crearEstructura(unTurno);
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();			

			unPilon.crearEstructura(unTurno);
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			assertTrue(unJugador.dameLimiteDePoblacion() == 10);
	}
}
