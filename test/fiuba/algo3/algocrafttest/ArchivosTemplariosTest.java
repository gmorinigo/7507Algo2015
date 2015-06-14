package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

@SuppressWarnings("static-access")
public class ArchivosTemplariosTest extends TestCase{

	public void testCrearUnArchivoTemplario() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
				
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof ArchivosTemplarios);

	}
	
	public void testCrearUnArchivoTemplarioAlPasar9TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(unaConstruccion);
		unTurno.addObserver(unaConstruccion);
		
		
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
	}
	
	
	public void testNoSePuedeCrearSinSuficientesRecursos() throws CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException {
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
				
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		unJugador.agregarConstruccion(unaConstruccion);
		unTurno.addObserver(unaConstruccion);
		
		
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());		
	}
	
	public void testNoSePuedeCrearSiNoSeCreoUnPuertoEstelarPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion666 = new Posicion(66,6);
		Jugador unJugador = new Jugador("otroNombre",new RazaProtoss(),"Rojo");	
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		
		//Usar el factory para validar
//		jugador.dameRaza().getFactoryConstrucciones().crearConstruccion(tipo, unaPosicion, unJugador);
		
		
	}
}
