package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarP;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public class ArchivosTemplariosTest extends TestCase{

	public void testCrearUnArchivoTemplario() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion45 = new Posicion(4,5);
		Construccion unArchivoTemplario = new ArchivosTemplarios(posicion45, new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		assertNotNull(unArchivoTemplario);
	}
	
	public void testCrearUnArchivoTemplarioAlPasar9TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion305 = new Posicion(30,5);
		Posicion posicion443 = new Posicion(44,3);
		Posicion posicion999 = new Posicion(99,9);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		Construccion unAcceso = new Acceso(posicion305,unJugador);
		Construccion unPuertoEstelar = new PuertoEstelarP(posicion999,unJugador);
		Construccion unArchivoTemplario = new ArchivosTemplarios(posicion443,unJugador);
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
		unTurno.aumentarTurno();		
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unJugador.dameConstruccionesTerminadas().contains(unPuertoEstelar));
		
		unArchivoTemplario.crearEstructura(unTurno);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertFalse(unArchivoTemplario.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unArchivoTemplario.estaTerminada());
	}
	
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		ArchivosTemplarios unArchivoTemplario = new ArchivosTemplarios(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unArchivoTemplario.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
	
	public void testNoSePuedeCrearSiNoSeCreoUnPuertoEstelarPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion666 = new Posicion(66,6);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");
		ArchivosTemplarios unArchivoTemplario = new ArchivosTemplarios(posicion666, unJugador);	
		Turno unTurno = new Turno(unJugador);
	
		try{
			unArchivoTemplario.crearEstructura(unTurno);
			fail("Deberia lanzar Exception porque no se creo la Fabrica");
		} catch(NoReuneLosRequisitosException e){
		}
	}
}
