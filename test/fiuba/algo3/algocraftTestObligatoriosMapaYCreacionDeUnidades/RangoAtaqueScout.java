package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeRealizarAccionException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;

@SuppressWarnings("static-access")
public class RangoAtaqueScout extends TestBase{
	public void testRangoAtaqueUnidadScoutDebeSer4() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444 = new Posicion(44,4);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelar);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelar.estaOperativa());
		
		
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelar.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre2);
		
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
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,8), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(72,otraUnidad.obtenerCantidadEscudo());	
	}
	
	public void testRangoAtaqueUnidadScoutNoCausaDañoSiEstaAMasDe4() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444 = new Posicion(44,4);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelar);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelar.estaOperativa());
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelar.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre2);
		
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
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,9), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());	
	}
	
	public void testRangoAtaqueDelScoutAUnidadAereaDebeSer4() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePuedeRealizarAccionException, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444  = new Posicion(44,4);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1500);
		otroJugador.dameAlmacenMineral().almacenarRecurso(500);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelar);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelar.estaOperativa());
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelar.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) unaConstruccion.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
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
		unTurno.avanzarTurno();
		assertTrue(otraUnidad.estaOperativa());
		assertTrue(unaUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,8), otraUnidad);
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(86,otraUnidad.obtenerCantidadEscudo());	
	}

	public void testRangoAtaqueDelScoutAUnidadAereaNoDañaSiEstaAMasDe4() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePuedeRealizarAccionException, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444  = new Posicion(44,4);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1500);
		otroJugador.dameAlmacenMineral().almacenarRecurso(500);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelar);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelar.estaOperativa());
	
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelar.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) unaConstruccion.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
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
		unTurno.avanzarTurno();
		assertTrue(otraUnidad.estaOperativa());
		assertTrue(unaUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,10), otraUnidad);
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
	}

}
