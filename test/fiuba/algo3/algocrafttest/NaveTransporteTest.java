package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;

@SuppressWarnings("static-access")
public class NaveTransporteTest extends TestBase {	
	
	public void testCrearUnidadNaveTransporte() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		unTurno.addObserver(expansor3);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador2,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveTransporte);
	}

	
	public void testAgregarUnidadALaNaveDeTransporte() throws MaximaCapacidadDeTransporteSuperadaException, NoSuchObjectException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		RazaProtoss unaRaza = new RazaProtoss(); 

		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");

		TipoConstruccion unTipoConstruccion = null;

		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2,unJugador);
		assertTrue(unaNaveTrasporte instanceof NaveTransporte);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unMarine instanceof Marine);
		
		unaNaveTrasporte.cargarUnidad(unMarine);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 1);
	}

	
	public void testAgregar3UnidadesALaNaveDeTransporte() throws MaximaCapacidadDeTransporteSuperadaException, NoSuchObjectException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		unTurno.addObserver(expansor3);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2,unJugador/*,new DisparoSuperStrategy()*/);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador/*,new DisparoSuperStrategy()*/);

		Unidad unGoliat = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unGoliat instanceof Golliat);
		
		Unidad otroGoliat = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		
		unaNaveTrasporte.cargarUnidad(unMarine);
		unaNaveTrasporte.cargarUnidad(unGoliat);
		unaNaveTrasporte.cargarUnidad(otroGoliat);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 5);
	}

	public void testLlenarNaveTransporteYAgregarOtraUnidadSeEsperaExcepcion() throws NoSuchObjectException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		// Sumo mas mineral para poder crear todos los golliats
		unJugador.dameAlmacenMineral().almacenarRecurso(500);
		
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2,unJugador/*,new DisparoSuperStrategy()*/);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador/*,new DisparoSuperStrategy()*/);

		Unidad golliat1 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		Unidad golliat2 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		Unidad golliat3 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		Unidad golliat4 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		

		unaNaveTrasporte.cargarUnidad(golliat1);
		unaNaveTrasporte.cargarUnidad(golliat2);
		unaNaveTrasporte.cargarUnidad(golliat3);
		unaNaveTrasporte.cargarUnidad(golliat4);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 8);

		try{
			unaNaveTrasporte.cargarUnidad(unMarine);
		}
		catch (MaximaCapacidadDeTransporteSuperadaException e){
			return;
		}
		
		fail();
	}
	
	
	public void testAgregarUnaUnidadVoladoraSeEsperaExcepcion() throws NoSuchObjectException, MaximaCapacidadDeTransporteSuperadaException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		unTurno.addObserver(expansor3);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2,unJugador/*,new DisparoSuperStrategy()*/);
		
		Unidad unEspectro = (Unidad) factoryUnidades.crearUnidad(unTipo.volador1,unJugador/*,new DisparoSuperStrategy()*/);

		try{
			unaNaveTrasporte.cargarUnidad(unEspectro);
		}
		catch (NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e){
			return;
		}
		
		fail();
	}
	
	public static AbstractUnidadFactory getFactoryUnidades(){
	    
		   return new UnidadFactoryTerran();
	} 
}
