package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

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
	
	public void testVerificarCantidadDePoblacionSegunLaCantidadDConstruccionesAlmacenDeSuministros() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		
		Construccion DepositoDeSuministros1 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion DepositoDeSuministros2 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion DepositoDeSuministros3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion DepositoDeSuministros4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion DepositoDeSuministros5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion DepositoDeSuministros6 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		
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
	

	public void testVerificarLaCantidadMaximaDePoblacionCon40AlmacenesDeSuministrosCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 40; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	

	public void testVerificarQueNoSeSupereLaCantidadMaximaDePoblacionSinImportarLaCantidadAlmacenesDeSuministrosCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaTerran unaRaza = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 100; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	
	
	public void testVerificarCantidadDePoblacionSegunLaCantidadDConstruccionesPilones() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		
		Construccion Pilon1 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion Pilon2 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion Pilon3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion Pilon4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion Pilon5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		Construccion Pilon6 = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador);
		
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
	

	public void testVerificarLaCantidadMaximaDePoblacionCon40PilonesCreados() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 40; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	


	public void testVerificarQueNoSeSupereLaCantidadMaximaDePoblacionSinImportarLaCantidadPilonesCreadas() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(1,1);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
			
		for (int i = 0; i < 100; i++){ 
			unTurno.addObserver((Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, unaPosicion, unJugador));
		}
		
		for (int i = 0 ; i < 6 ; i++) unTurno.avanzarTurno();
		
		assertTrue(unJugador.dameLimiteDePoblacion() == 200);
	}
	
	
	public void testVerificarCantidadDePoblacionSegunLaCantidadDConstruccionesPilon() throws JugadorConNombreDemasiadoCortoException{
		Jugador unJugador = new Jugador("NombreJugador", new RazaProtoss(), "unColor");
		assertEquals("unColor", unJugador.dameColor());
	}
}
