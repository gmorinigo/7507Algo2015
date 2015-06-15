package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import junit.framework.TestCase;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Pilon;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

@SuppressWarnings("static-access")
public class PilonTest extends TestCase{
	public void testCrearUnDepositoDeSuministro() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof Pilon);
	}
	
	public void testCrearUnPilonAlPasar5TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		

		unTurno.addObserver(unaConstruccion);
		
		
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeMineralInsuficiente() throws NoSuchObjectException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		

		unJugador.dameAlmacenMineral().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		}
		catch (CantidadDeMineralInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	public void testAlCrear2PilonElLimiteDePoblacionAumenta10() throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion otraConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 10);
	}
}
