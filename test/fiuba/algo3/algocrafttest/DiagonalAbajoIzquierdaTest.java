package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionNoPermitidaPorSalirseDelMapaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

public class DiagonalAbajoIzquierdaTest extends TestBase{

    public void testDeberiaMoverLaUnidadUnaPosicionParaAbajoALaIzquierda() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{ 	                 
            Mapa mapa = Mapa.getInstance();
    		RazaProtoss unaRaza = new RazaProtoss(); 
    		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
    		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
    		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);    		
    		
    		//Unidad esta naciendo. Por lo tanto no puede colocarse en el mapa
    		assertFalse(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
    		
    		unaUnidad.finalizarNacimiento();
    		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
    		
            unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda);
    		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,4)));
            assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(5,3)));
            
            unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda);
            unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda);
            
            //La unidad ya realizo la accion del turno
            assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(5,1)));
            assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(5,3)));
        }
        
        public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupada() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
            Mapa mapa = Mapa.getInstance();
    		RazaProtoss unaRaza = new RazaProtoss(); 
    		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
    		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
    		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
    		Unidad otraUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre2,unJugador);
    		unaUnidad.finalizarNacimiento();
    		otraUnidad.finalizarNacimiento();
    		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
    		assertTrue(mapa.agregarUnidad(new Posicion(5,3), otraUnidad));
    		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda));
        }
        
        public void testNosePuedeMoverUnaUnidadAUnaPosicionYaOcupadaPorOtroJugador() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
            Mapa mapa = Mapa.getInstance();
    		RazaProtoss unaRaza = new RazaProtoss(); 
    		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
    		Jugador otroJugador = new Jugador("elNombre",unaRaza,"Rojo");
    		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
    		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
    		Unidad otraUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,otroJugador);
    		unaUnidad.finalizarNacimiento();
    		otraUnidad.finalizarNacimiento();
    		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
    		assertTrue(mapa.agregarUnidad(new Posicion(5,3), otraUnidad));
    		assertFalse(unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda));
        }
        
        public void testNoSePuedeMoverUnaUnidadAUnaPosicionDondeHayUnaConstruccion() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, ConstruccionNoPermitidaPorSalirseDelMapaException{
            Mapa mapa = Mapa.getInstance();
    		RazaProtoss unaRaza = new RazaProtoss(); 
    		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
    		Posicion posicion54 = new Posicion(5,4);
    		Acceso unaConstruccion = new Acceso(posicion54, unJugador,TipoConstruccion.creadorUnidadesBasicas );
    		
    		assertTrue(mapa.agregarConstruccion(unaConstruccion));
    		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Rojo");
    		
    		Turno unTurno = new Turno(unJugador,otroJugador);
    		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
    		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
    		unaUnidad.finalizarNacimiento();
    		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
    		
    		unTurno.addObserver(unaConstruccion);
    		
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
    		unTurno.avanzarTurno();
    		unTurno.avanzarTurno();
    		assertTrue(unaConstruccion.estaOperativa());		
    		assertTrue(unaUnidad.mover(TipoDireccion.DiagonalAbajoIzquierda));
        }
}
