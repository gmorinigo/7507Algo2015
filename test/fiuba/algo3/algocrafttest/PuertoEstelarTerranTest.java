package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarProtoss;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
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
public class PuertoEstelarTerranTest extends TestBase {
	public void testCrearUnPuertoEstelar() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();
		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, otraPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof PuertoEstelarTerran);
	}
	
	public void testCrearUnPuertoEstelarAlPasar10TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);

		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);

		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();
				
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, otraPosicion, unJugador);
		

		//unTurno.addObserver(unaConstruccion);
				
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
		assertTrue(unaConstruccion.estaOperativa());
	}
	
	public void testCrearEspectrosYNavesAlPasarTurnosEstaCreadaEstaFinalizada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unaBarraca = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaBarraca);
		unJugador.agregarConstruccion(unaBarraca);
		for (int i = 0; i < 12 ;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unaConstruccion = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, otraPosicion, unJugador);
	
		unTurno.addObserver(unaConstruccion);
		
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();

		assertTrue(unaConstruccion.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;

		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		
		Unidad unEspectro = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.volador1);
		Unidad unaNaveDeTransporte = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.volador2);
		Unidad unaNaveDeCiencia = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.especial1);
		
		unTurno.addObserver(unEspectro);
		unTurno.addObserver(unaNaveDeTransporte);
		unTurno.addObserver(unaNaveDeCiencia);
		
		for (int i = 0; i < 7 ; i++) unTurno.avanzarTurno();
		assertTrue(unaNaveDeTransporte.estaOperativa());
		
		unTurno.avanzarTurno();
		assertTrue(unEspectro.estaOperativa());
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaNaveDeCiencia.estaOperativa());

	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeGasInsuficiente() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		unJugador.dameAlmacenGas().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, unaPosicion, unJugador);
		}
		catch (CantidadDeGasInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeMineralInsuficiente() throws NoSuchObjectException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		unJugador.dameAlmacenMineral().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, unaPosicion, unJugador);
		}
		catch (CantidadDeMineralInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testNoSePuedeCrearSiNoSeCreoUnaBarracaPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
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
