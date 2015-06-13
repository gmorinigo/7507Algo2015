package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
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
		Construccion unaBarraca = new Barraca(posicion64, new Jugador("unNombre",new RazaTerran(),"Azul"));
		assertNotNull(unaBarraca);
	}
	
	public void testCrearUnaBarracaAlPasar12TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion14 = new Posicion(1,4);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Construccion unaBarraca = new Barraca(posicion14, unJugador );
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		
		unaBarraca.crearEstructura(unTurno);
		
		assertFalse(unaBarraca.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaBarraca.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaBarraca.estaOperativa());
	}
	
	public void testCrearMarineAlPasar3TurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion123 = new Posicion(12,3);
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Barraca unaBarraca = new Barraca(posicion123, unJugador);
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
	
		unaBarraca.crearEstructura(unTurno);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaBarraca.estaTerminada());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaBarraca.estaTerminada());
		
/*		Unidad marine = unaBarraca.crearUnidad();
		unTurno.addObserver(marine);
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		unTurno.aumentarTurno();
		
		assertTrue(marine.estaTerminada());*/
		
	}
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, JugadorConNombreDemasiadoCortoException {
		Posicion posicion123 = new Posicion(12,3);
		Almacen gas = new Almacen(0);
		Almacen mineral = new Almacen(0);
		
		Jugador jugador = new Jugador(new RazaTerran(),mineral, gas);
		Barraca unaBarraca = new Barraca(posicion123, jugador);
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(jugador,otroJugador);
		
	
		try {
			unaBarraca.crearEstructura(unTurno);
			fail("Deberia lanzar Exception");
		} catch (NoReuneLosRequisitosException e) {
			
		}
	}
}
