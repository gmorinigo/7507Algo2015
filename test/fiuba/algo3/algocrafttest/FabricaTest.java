package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import junit.framework.TestCase;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Fabrica;
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
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

@SuppressWarnings("static-access")
public class FabricaTest extends TestCase {

	public void testCrearUnArchivoTemplario() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		unJugador.agregarConstruccion(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof Fabrica);

	}
	
	public void testCrearUnArchivoTemplarioAlPasar9TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException,  ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		unJugador.agregarConstruccion(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);

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
	
	
	@SuppressWarnings("unused")
	public void testCantidadDeGasInsuficiente() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		unJugador.agregarConstruccion(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenGas().consumirRecurso(400);
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);

		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);
		}
		catch (CantidadDeGasInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeMineralInsuficiente() throws NoSuchObjectException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, CantidadDeMineralInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		unJugador.agregarConstruccion(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		//unJugador.dameAlmacenMineral().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);
		}
		catch (CantidadDeMineralInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testNoSePuedeCrearSiNoSeCreoUnPuertoEstelarPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion, unJugador);
		}
		catch (ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException e){
			return;
		}
		fail();
		
	}
}