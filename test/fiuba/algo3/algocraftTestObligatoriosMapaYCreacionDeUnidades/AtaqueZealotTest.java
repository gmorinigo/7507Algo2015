package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
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
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

@SuppressWarnings("static-access")
public class AtaqueZealotTest extends TestBase{
	public void testRangoAtaqueUnidadZealotDebeSer1() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
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
	
	for (int i = 0; i<24;i++) unTurno.avanzarTurno();
	assertTrue(unaConstruccion.estaOperativa());
	
	TipoConstruccion unTipoConstruccion = null;
	unJugador.dameAlmacenMineral().almacenarRecurso(1000);
	Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
	Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
	Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
	Construccion expansor5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(138,138), otroJugador);
	unTurno.addObserver(unExpansor);
	unTurno.addObserver(expansor3);
	unTurno.addObserver(expansor4);
	unTurno.addObserver(expansor5);
	for (int i=0;i<6;i++) unTurno.avanzarTurno();
	
	
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
	
	public void testRangoAtaqueUnidadZealotNoPuedoAtacarSiEstaAMasDe1() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
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
		
		for (int i = 0; i<24;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(138,138), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		unTurno.addObserver(expansor5);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
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
		mapa.agregarUnidad(new Posicion(5,6), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());	
		}
	
	public void testUnidadZealotNoPuedeAtacarAUnaUnidadAerea() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(14,3);
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<24;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(138,138), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		unTurno.addObserver(expansor5);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.terrestre1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);

		for (int i=0;i<18;i++) unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		unaUnidad.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());	
		}
}
