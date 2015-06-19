package fiuba.algo3.algocrafttest;

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
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionNoPermitidaPorSalirseDelMapaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

@SuppressWarnings("static-access")
public class IzquierdaTest extends TestBase{
	public void testIzquierdaDeberiaMoverLaUnidadUnaPosicionParaLaIzquierda() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{        
	       
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(24,24), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(3,5), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(34,34), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		unTurno.addObserver(unaConstruccion);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());

		assertEquals(mapa.dameCelda(new Posicion(15,14)),unaUnidad.dameCelda());
		
        unaUnidad.mover(TipoDireccion.Izquierda);

		
		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,14)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,13)));
        
        unaUnidad.mover(TipoDireccion.Izquierda);
        unaUnidad.mover(TipoDireccion.Izquierda);
        unaUnidad.mover(TipoDireccion.Izquierda);
        
        assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,14)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,13)));
    }
   /*
    public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupada() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
	
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,17), unJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		Unidad otraUnidad = (Unidad) otraConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,14)));
        assertEquals(otraUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,13)));
		
		assertFalse(unaUnidad.mover(TipoDireccion.Izquierda));
    }
 
    public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupadaPorOtroJugador() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		Jugador otroJugador = new Jugador("elNombre",unaRaza,"Rojo");
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(18,15), unJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		Unidad otraUnidad = (Unidad) otraConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		
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
		
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,14)));
        assertEquals(otraUnidad.dameCelda(),mapa.dameCelda(new Posicion(18,14)));
		
		assertTrue(unaUnidad.mover(TipoDireccion.Abajo));
		unTurno.addObserver(unaUnidad);
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(16,14)));
        assertNotSame(otraUnidad.dameCelda(),mapa.dameCelda(new Posicion(15,14)));
		unTurno.avanzarTurno();

        assertTrue(unaUnidad.mover(TipoDireccion.Abajo));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(17,14)));
        assertNotSame(otraUnidad.dameCelda(),mapa.dameCelda(new Posicion(16,14)));
		unTurno.avanzarTurno();
        
        assertFalse(unaUnidad.mover(TipoDireccion.Abajo));
    }*/
    
    public void testNoSePuedeMoverUnaUnidadAUnaPosicionDondeHayUnaConstruccion() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, ConstruccionNoPermitidaPorSalirseDelMapaException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Rojo");
		
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,12), unJugador);
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		
		assertFalse(unaUnidad.mover(TipoDireccion.Izquierda));
    }
}
