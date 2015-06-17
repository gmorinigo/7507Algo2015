package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
//import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
//import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
//import fiuba.algo3.algocraft.modelo.unidades.Unidad;

@SuppressWarnings("static-access")
public class JugadorTest extends TestBase {

	public void testJugadorVerificaElNombreConElQueFueCreado() throws JugadorConNombreDemasiadoCortoException{
		Jugador unJugador = new Jugador("NombreJugador", new RazaProtoss(), "unColor");
		assertEquals("NombreJugador", unJugador.dameNombre());
	}
	
	public void testJugadorVerificaElColorConElQueFueCreado() throws JugadorConNombreDemasiadoCortoException{
		Jugador unJugador = new Jugador("NombreJugador", new RazaProtoss(), "unColor");
		assertEquals("unColor", unJugador.dameColor());
	}
	
	public void testVerificarCantidadDePoblacionSegunLaCantidadDConstruccionesAlmacenDeSuministros() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		int j = 3;
		Construccion DepositoDeSuministros1 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		Construccion DepositoDeSuministros2 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		Construccion DepositoDeSuministros3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		Construccion DepositoDeSuministros4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		Construccion DepositoDeSuministros5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		Construccion DepositoDeSuministros6 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(10,++j*4), unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		unTurno.addObserver(DepositoDeSuministros1);
		unTurno.addObserver(DepositoDeSuministros2);
		unTurno.addObserver(DepositoDeSuministros3);
		unTurno.addObserver(DepositoDeSuministros4);
		unTurno.addObserver(DepositoDeSuministros5);
		unTurno.addObserver(DepositoDeSuministros6);
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 30);
	}
	

	public void testVerificarLaCantidadMaximaDePoblacionCon40AlmacenesDeSuministrosCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(0,0);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 80; i++){ 
			
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion,  new Posicion(2,++i), unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		int cant = unJugador.dameLimiteDePoblacion();
		assertTrue( cant == 200);
		
	}
	

	public void testVerificarQueNoSeSupereLaCantidadMaximaDePoblacionSinImportarLaCantidadAlmacenesDeSuministrosCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 100; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++i), unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	
	
	public void testVerificarCantidadDePoblacionSegunLaCantidadDConstruccionesPilones() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		int w= 5;
		Construccion Pilon1 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		Construccion Pilon2 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		Construccion Pilon3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		Construccion Pilon4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		Construccion Pilon5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		Construccion Pilon6 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++w*6), unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		unTurno.addObserver(Pilon1);
		unTurno.addObserver(Pilon2);
		unTurno.addObserver(Pilon3);
		unTurno.addObserver(Pilon4);
		unTurno.addObserver(Pilon5);
		unTurno.addObserver(Pilon6);
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 30);
	}
	

	public void testVerificarLaCantidadMaximaDePoblacionCon40PilonesCreados() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 80; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++i), unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	


	public void testVerificarQueNoSeSupereLaCantidadMaximaDePoblacionSinImportarLaCantidadPilonesCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 100; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(2,++i), unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	
	/*
	public void testContarConLimiteDePoblacionParaCrearUnaUnidad() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		
		Barraca unaConstruccion = (Barraca) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 0);

		unTurno.addObserver(unaConstruccion);
		
		for (int i=0; i<12;i++) unTurno.avanzarTurno();
				
		try{
			Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador);
			unTurno.addObserver(unaUnidad);
			unJugador.agregarUnidad(unaUnidad);
		}
		catch (CapacidadDePoblacionMaximaSuperada e){
			return;
		}
		fail();
	}*/	
}
