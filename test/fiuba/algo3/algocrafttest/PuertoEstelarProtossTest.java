package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
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
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;

@SuppressWarnings("static-access")
public class PuertoEstelarProtossTest extends TestBase{
	public void testCrearUnPuertoEstelar() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof PuertoEstelarProtoss);
	}
	
	public void testCrearUnPuertoEstelarAlPasar10TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);

		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);

		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();
				
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		

		//unTurno.addObserver(unaConstruccion);
				
		assertFalse(unaConstruccion.estaOperativa());
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
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaConstruccion.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
	}
	
	public void testCrearScoutYNavesAlPasarTurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,30);
		Posicion otraPosicion = new Posicion(15,15);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = Turno.getInstance(unJugador, otroJugador);
		
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();
		
		PuertoEstelarProtoss unaConstruccion = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
	
		unTurno.addObserver(unaConstruccion);
		
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();

		assertTrue(unaConstruccion.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.volador1);
		Unidad otraUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.volador2);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
    	assertTrue(otraUnidad.estaOperativa());
    	
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
	}
	
	
	@SuppressWarnings("unused")
	public void testCantidadDeGasInsuficiente() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		unJugador.dameAlmacenGas().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		}
		catch (CantidadDeGasInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeMineralInsuficiente() throws NoSuchObjectException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		unJugador.dameAlmacenMineral().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		}
		catch (CantidadDeMineralInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testNoSePuedeCrearSiNoSeCreoUnAccesoPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
	
		try{
			PuertoEstelarProtoss unaConstruccion = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, unaPosicion, unJugador);
		} catch(ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException e){
			return;
		}
		fail();
	}
}
