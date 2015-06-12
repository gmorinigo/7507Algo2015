package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarP;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class PuertoEstelarPTest extends TestCase{

	
	public void testCrearUnPuertoEstelar() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion45 = new Posicion(4,5);
		Construccion unPuertoEstelar = new PuertoEstelarP(posicion45, new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(unPuertoEstelar);
	}
	
	public void testCrearUnPuertoEstelarAlPasar10TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion305 = new Posicion(30,5);
		Posicion posicion443 = new Posicion(44,3);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Construccion unAcceso = new Acceso(posicion305,unJugador);
		Construccion unPuertoEstelar = new PuertoEstelarP(posicion443,unJugador);
		Turno unTurno = new Turno(unJugador);
		unAcceso.crearEstructura(unTurno);
		
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unJugador.dameConstruccionesTerminadas().contains(unAcceso));

		unPuertoEstelar.crearEstructura(unTurno);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unPuertoEstelar.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unPuertoEstelar.estaTerminada());
	}
	
	public void testCrearScoutYNavesAlPasarTurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion123 = new Posicion(12,3);
		Posicion posicion1276 = new Posicion(12,76);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Acceso unAcceso = new Acceso(posicion123, unJugador);
		PuertoEstelarP unPuertoEstelar = new PuertoEstelarP(posicion1276,unJugador);
		
		Turno unTurno = new Turno(unJugador);
		unAcceso.crearEstructura(unTurno);
		
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
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unJugador.dameConstruccionesTerminadas().contains(unAcceso));
		
		unPuertoEstelar.crearEstructura(unTurno);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unPuertoEstelar.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unPuertoEstelar.estaTerminada());
		
/*		Unidad unEspectro = unPuertoEstelar.crearUnidad();
		unTurno.addObserver(unEspectro);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unEspectro.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unEspectro.estaTerminada());
		
		Unidad unaNaveDeCiencia = unPuertoEstelar.crearUnidad();
		unTurno.addObserver(unaNaveDeCiencia);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unaNaveDeCiencia.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaNaveDeCiencia.estaTerminada());
		
		Unidad unaNaveDeTransporte = unPuertoEstelar.crearUnidad();
		unTurno.addObserver(unaNaveDeTransporte);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unaNaveDeTransporte.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaNaveDeTransporte.estaTerminada());*/
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		PuertoEstelarP unPuertoEstelar = new PuertoEstelarP(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unPuertoEstelar.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
	public void testNoSePuedeCrearSiNoSeCreoUnaBarracaPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion666 = new Posicion(66,6);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		PuertoEstelarP unPuertoEstelar = new PuertoEstelarP(posicion666, unJugador);	
		Turno unTurno = new Turno(unJugador);
	
		try{
			unPuertoEstelar.crearEstructura(unTurno);
			fail("Deberia lanzar Exception porque no se creo la Fabrica");
		} catch(NoReuneLosRequisitosException e){
		}
	}
}
