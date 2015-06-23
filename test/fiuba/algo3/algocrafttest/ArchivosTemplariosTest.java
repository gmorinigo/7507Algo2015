package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
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
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

@SuppressWarnings("static-access")
public class ArchivosTemplariosTest extends TestBase{

	public void testCrearUnArchivoTemplario() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);		

		assertTrue(unaConstruccion instanceof ArchivosTemplarios);

	}
	
	public void testCrearUnArchivoTemplarioAlPasar9TurnosEstaCreada() 
	throws CeldaOcupadaException, NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);

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
	public void testCantidadDeGasInsuficiente() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, CantidadDeGasInsuficienteException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias

		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);
			Construccion otraConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);
		}
		catch (CantidadDeGasInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testCantidadDeMineralInsuficiente() throws NoSuchObjectException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, CantidadDeMineralInsuficienteException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actual
		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		//unJugador.dameAlmacenMineral().consumirRecurso(500);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, terceraPosicion, unJugador);
			Construccion otraConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, terceraPosicion, unJugador);
		}
		catch (CantidadDeMineralInsuficienteException e){
			return;
		}
		fail();
		
	}
	
	@SuppressWarnings("unused")
	public void testNoSePuedeCrearSiNoSeCreoUnPuertoEstelarPreviamente() throws NoReuneLosRequisitosException, JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		
		try{
			Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, unaPosicion, unJugador);
		}
		catch (ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException e){
			return;
		}
		fail();
		
	}
}
