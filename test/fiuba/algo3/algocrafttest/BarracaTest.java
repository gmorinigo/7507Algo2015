package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class BarracaTest extends TestBase {
	

	public void testCrearUnaBarraca() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion64 = new Posicion(6,4);
		Construccion unaBarraca = new Barraca(posicion64, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		assertNotNull(unaBarraca);
	}
	
	public void testCrearUnaBarracaAlPasar12TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion14 = new Posicion(1,4);
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Construccion unaBarraca = new Barraca(posicion14, unJugador );
		Turno unTurno = new Turno(unJugador);
		
		unaBarraca.crearEstructura(unTurno);
		
		assertFalse(unaBarraca.estaTerminada());
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
		assertFalse(unaBarraca.estaTerminada());
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		assertTrue(unaBarraca.estaTerminada());
	}
	
	public void testCrearMarineAlPasar3TurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion123 = new Posicion(12,3);
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Barraca unaBarraca = new Barraca(posicion123, unJugador);
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
		
		Turno turnoParaUnidad = unaBarraca.crearMarine();
		assertTrue(unaBarraca.estaTrabajando());
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		turnoParaUnidad.aumentarTurno();
		assertFalse(unaBarraca.estaTrabajando());
		
		Unidad unidad = unaBarraca.dameUnidad();
		//assertTrue(unidad.estaTerminado());
		
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaProtoss(),mineral, gas);
		Barraca unaBarraca = new Barraca(posicion123, jugador);
		Turno unTurno = new Turno(jugador);
	
		try {
			unaBarraca.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
}
