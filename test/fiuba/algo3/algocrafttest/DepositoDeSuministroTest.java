package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.DepositoDeSuministro;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class DepositoDeSuministroTest extends TestCase {

	public void testCrearUnDepositoDeSuministro() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion unDepositoDeSuministro = new DepositoDeSuministro(posicion64, new Jugador("unNombre",new RazaTerran(),"Azul"));
		assertNotNull(unDepositoDeSuministro);
	}
	
	public void testCrearUnDepositoDeSuministrosAlPasar6TurnosEstaCreada() 
			throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
				Posicion posicion124 = new Posicion(12,4);
				Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
				Construccion unDeposito = new DepositoDeSuministro(posicion124, unJugador );
				Turno unTurno = new Turno(unJugador);
				
				unDeposito.crearEstructura(unTurno);
				assertFalse(unDeposito.estaTerminada());
				unTurno.aumentarTurno();
				unTurno.aumentarTurno();
				unTurno.aumentarTurno();
				unTurno.aumentarTurno();
				assertFalse(unDeposito.estaTerminada());
				unTurno.aumentarTurno();
				unTurno.aumentarTurno();
				assertTrue(unDeposito.estaTerminada());
			}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaTerran(),mineral, gas);
		DepositoDeSuministro unDeposito = new DepositoDeSuministro(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unDeposito.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
	public void testAlCrear2DepositosDeSuministrosElLimiteDePoblacionAumenta10() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
			Posicion posicion124 = new Posicion(12,4);
			Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
			Construccion unDeposito = new DepositoDeSuministro(posicion124, unJugador );
			Turno unTurno = new Turno(unJugador);

			unDeposito.crearEstructura(unTurno);
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();			

			unDeposito.crearEstructura(unTurno);
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			unTurno.aumentarTurno();
			assertTrue(unJugador.dameLimiteDePoblacion() == 10);
	}
}
