package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.Fabrica;
import fiuba.algo3.algocraft.modelo.construciones.PuertoEstelar;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import junit.framework.TestCase;

public class PuertoEstelarTest extends TestCase {
	
	public void testCrearUnPuertoEstelar() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion45 = new Posicion(4,5);
		Construccion unPuertoEstelar = new PuertoEstelar(posicion45, new Jugador("unNombre",new RazaTerran(),"Azul"));
		assertNotNull(unPuertoEstelar);
	}
	
	public void testCrearUnPuertoEstelarAlPasar10TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion175 = new Posicion(17,5);
		Posicion posicion305 = new Posicion(30,5);
		Posicion posicion443 = new Posicion(44,3);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Construccion unaBarraca = new Barraca(posicion305,unJugador);
		Construccion unaFabrica = new Fabrica(posicion175, unJugador );
		Construccion unPuertoEstelar = new PuertoEstelar(posicion443,unJugador);
		Turno unTurno = new Turno(unJugador);
		unaBarraca.crearEstructura(unTurno);
		
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
		assertTrue(unJugador.dameConstruccionesTerminadas().contains(unaBarraca));
		
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
	
	public void testCrearEspectrosYNavesAlPasarTurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion123 = new Posicion(12,3);
		Posicion posicion1273 = new Posicion(12,73);
		Posicion posicion1276 = new Posicion(12,76);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Fabrica unaFabrica = new Fabrica(posicion123, unJugador);
		Construccion unaBarraca = new Barraca(posicion1273,unJugador);
		PuertoEstelar unPuertoEstelar = new PuertoEstelar(posicion1276,unJugador);
		
		Turno unTurno = new Turno(unJugador);
		unaBarraca.crearEstructura(unTurno);
		
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
		assertTrue(unJugador.dameConstruccionesTerminadas().contains(unaBarraca));
		
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
		
		Unidad unEspectro = unPuertoEstelar.crearUnidad();
		unTurno.addObserver(unEspectro);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
//		assertFalse(unEspectro.estaTerminada());
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
//		assertFalse(unaNaveDeCiencia.estaTerminada());
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
//		assertFalse(unaNaveDeTransporte.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaNaveDeTransporte.estaTerminada());
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaTerran(),mineral, gas);
		PuertoEstelar unPuertoEstelar = new PuertoEstelar(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unPuertoEstelar.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
	public void testNoSePuedeCrearSiNoSeCreoUnaBarracaPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion666 = new Posicion(66,6);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		PuertoEstelar unPuertoEstelar = new PuertoEstelar(posicion666, unJugador);	
		Turno unTurno = new Turno(unJugador);
	
		try{
			unPuertoEstelar.crearEstructura(unTurno);
			fail("Deberia lanzar Exception porque no se creo la Fabrica");
		} catch(NoReuneLosRequisitosException e){
		}
	}

}
