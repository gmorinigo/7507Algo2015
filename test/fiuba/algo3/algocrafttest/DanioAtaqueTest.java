package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
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
public class DanioAtaqueTest extends TestBase {
	public void testPruebaDeAtaqueTerrestreDelZealot() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePuedeRealizarAccionException, NoSePudoConstruirException{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.terrestre1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,5), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(52,otraUnidad.obtenerCantidadEscudo());	
	}

	
	public void testPruebaDeAtaqueTerrestreDelDragon() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePuedeRealizarAccionException, NoSePudoConstruirException{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.terrestre2);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre2);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,5), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());	
	}

	public void testPruebaDeAtaqueAereoDelDragon() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePuedeRealizarAccionException, NoSePudoConstruirException{	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);

		otroJugador.dameAlmacenMineral().almacenarRecurso(500);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.terrestre2);
		UnidadProtoss otraUnidad = (UnidadProtoss) unaConstruccion.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,5), otraUnidad);
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());	
	}
	
}
