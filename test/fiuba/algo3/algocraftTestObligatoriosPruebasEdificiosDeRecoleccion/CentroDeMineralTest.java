package fiuba.algo3.algocraftTestObligatoriosPruebasEdificiosDeRecoleccion;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroMineral;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocrafttest.TestBase;

public class CentroDeMineralTest extends TestBase {

	public void testCrearUnCentroMineral() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion centroMineral = new CentroMineral(posicion64, new Jugador("unNombre",new RazaTerran(),"Azul"),TipoConstruccion.extractorMineral);
		assertNotNull(centroMineral);
	}
	
	public void testCrearUnCentroMineralAl8TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion911 = new Posicion(9,11);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Construccion centroMineral = new CentroMineral(posicion911, unJugador,TipoConstruccion.extractorMineral);
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unTurno.addObserver(centroMineral);
		
		assertFalse(centroMineral.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(centroMineral.estaTerminada());
		unTurno.avanzarTurno();
		assertTrue(centroMineral.estaTerminada());
	}
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion911 = new Posicion(9,11);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		CentroMineral centroMineral = new CentroMineral(posicion911, unJugador,TipoConstruccion.extractorMineral);
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unTurno.addObserver(centroMineral);
		
		
		assertFalse(centroMineral.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(centroMineral.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(centroMineral.estaTerminada());
		
		Almacen almacen = new Almacen();
		
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		assertTrue(almacen.cantidad() == 40);
	}
}
