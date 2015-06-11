package fiuba.algo3.algocrafttest;

import junit.framework.Assert;
import junit.framework.TestCase;
import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.Fabrica;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class FabricaTest extends TestCase {

	public void testCrearUnaFabrica() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion77 = new Posicion(7,7);
		Construccion unaFabrica = new Fabrica(posicion77, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		assertNotNull(unaFabrica);
	}
	
	public void testCrearUnaFabricaAlPasar12TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion175 = new Posicion(17,5);
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Construccion unaFabrica = new Fabrica(posicion175, unJugador );
		Turno unTurno = new Turno(unJugador);
		
		unaFabrica.crearEstructura(unTurno);
		
		assertFalse(unaFabrica.estaTerminada());
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
		assertFalse(unaFabrica.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaFabrica.estaTerminada());
	}
	
	public void testCrearGolliatAlPasarTurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion123 = new Posicion(12,3);
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Fabrica unaFabrica = new Fabrica(posicion123, unJugador);
		Turno unTurno = new Turno(unJugador);
	
		unaFabrica.crearEstructura(unTurno);
		
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unaFabrica.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaFabrica.estaTerminada());
		
		Unidad unGolliat = unaFabrica.crearUnidad();
		unTurno.addObserver(unGolliat);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
//		assertFalse(unGolliat.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unGolliat.estaTerminada());
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		Fabrica unaFabrica = new Fabrica(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unaFabrica.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
}
