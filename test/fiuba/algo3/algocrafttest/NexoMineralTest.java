package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.NexoMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroDeMineral;
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
		Construccion centroMineral = new CentroDeMineral(posicion64, new Jugador("OtroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(centroMineral);
	}
	
	public void testCrearUnNexoMineralAl8TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion911 = new Posicion(9,11);
		Jugador unJugador = new Jugador("OtroNombre",new RazaProtoss(),"Rojo");
		Construccion unNexo = new NexoMineral(posicion911, unJugador );
		Turno unTurno = new Turno(unJugador);
	
		unNexo.crearEstructura(unTurno);
		
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
		Turno unTurno = new Turno(unJugador);
		
		unNexo.crearEstructura(unTurno);
		
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unNexo.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unNexo.estaTerminada());
		
		Almacen almacen = new Almacen();
		
		unNexo.recolectar(almacen);
		unNexo.recolectar(almacen);
		unNexo.recolectar(almacen);
		unNexo.recolectar(almacen);
		assertTrue(almacen.cantidad() == 40);
	}
}
