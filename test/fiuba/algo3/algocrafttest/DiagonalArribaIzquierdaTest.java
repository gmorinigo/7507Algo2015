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

public class DiagonalArribaIzquierdaTest extends TestBase{
	public void testDeberiaMoverLaUnidadUnaPosicionParaArribaALaIzquierda() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{ 	
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		TipoConstruccion unTipoConstruccion = null;
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
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
		
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);		
		
		//Unidad esta naciendo. Por lo tanto no puede colocarse en el mapa
		assertFalse(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		
		unaUnidad.finalizarNacimiento();
		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		
        unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda);
		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,4)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(3,3)));
        
        unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda);
        unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda);
        
        //La unidad ya realizo la accion del turno
        assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(1,1)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(3,3)));
    }
    
    public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupada() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		TipoConstruccion unTipoConstruccion = null;
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
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
		
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
		Unidad otraUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre2,unJugador);
		unaUnidad.finalizarNacimiento();
		otraUnidad.finalizarNacimiento();
		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		assertTrue(mapa.agregarUnidad(new Posicion(3,3), otraUnidad));
		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda));
    }
    
    public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupadaPorOtroJugador() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		Jugador otroJugador = new Jugador("elNombre",unaRaza,"Rojo");
		
		TipoConstruccion unTipoConstruccion = null;
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Turno unTurno = new Turno(unJugador,otroJugador);
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
		
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
		Unidad otraUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,otroJugador);
		unaUnidad.finalizarNacimiento();
		otraUnidad.finalizarNacimiento();
		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		assertTrue(mapa.agregarUnidad(new Posicion(3,3), otraUnidad));
		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda));
    }
    
    public void testNoSePuedeMoverUnaUnidadAUnaPosicionDondeHayUnaConstruccion() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, ConstruccionNoPermitidaPorSalirseDelMapaException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		Posicion posicion54 = new Posicion(5,4);
		Acceso unaConstruccion = new Acceso(posicion54, unJugador,TipoConstruccion.creadorUnidadesBasicas );
		
		assertTrue(mapa.agregarConstruccion(unaConstruccion));
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Rojo");
		
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		TipoConstruccion unTipoConstruccion = null;
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
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
		
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
		unaUnidad.finalizarNacimiento();
		assertTrue(mapa.agregarUnidad(new Posicion(6,6), unaUnidad));
		
		unTurno.addObserver(unaConstruccion);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertFalse(unaConstruccion.estaOperativa());
		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda));
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());		
		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalArribaIzquierda));
    }
}
