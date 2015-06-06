package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.CentroDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class CentroDeMineralTest extends TestBase {

	public void testCrearUnCentroMineral(){
		Posicion posicion64 = new Posicion(6,4);
		Construccion centroMineral = new CentroDeMineral(posicion64, new Jugador("unNombre",new Raza()));
		assertNotNull(centroMineral);
	}
	
	public void testCrearUnCentroMineralAl8TurnosEstaCreada() throws CeldaOcupadaException, NoReuneLosRequisitosException{
		Posicion posicion14 = new Posicion(1,4);
		Construccion centroMineral = new CentroDeMineral(posicion14, new Jugador("unNombre",new Raza()));
		Turno unTurno = new Turno(8);
	
		centroMineral.crearEstructura(unTurno);
		
		assertFalse(centroMineral.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(centroMineral.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(centroMineral.estaTerminada());
	}
	
	public void testComanzarARecolectar() throws CeldaOcupadaException, NoReuneLosRequisitosException {
		Posicion posicion14 = new Posicion(1,4);
		CentroDeMineral centroMineral = new CentroDeMineral(posicion14, new Jugador("unNombre",new Raza()));
		Turno unTurno = new Turno(8);
		
		centroMineral.crearEstructura(unTurno);
		
		assertFalse(centroMineral.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(centroMineral.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(centroMineral.estaTerminada());
		
		Almacen almacen = new Almacen();
		
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		centroMineral.recolectar(almacen);
		assertTrue(almacen.cantidad() == 40);
		
	}
}
