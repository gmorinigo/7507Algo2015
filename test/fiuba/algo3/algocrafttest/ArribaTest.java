package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import junit.framework.TestCase;

public class ArribaTest extends TestBase{
	
    public void testArribaDeberiaMoverLaUnidadUnaPosicionParaArribaUnaVezPorTurno() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
        
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
		
		
		//Unidad esta naciendo. Por lo tanto no puede colocarse en el mapa
		assertFalse(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		
		unaUnidad.finalizarNacimiento();
		assertTrue(mapa.agregarUnidad(new Posicion(4,4), unaUnidad));
		
        unaUnidad.mover(TipoDireccion.Arriba);
		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,4)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(3,4)));
        
        unaUnidad.mover(TipoDireccion.Arriba);
        unaUnidad.mover(TipoDireccion.Arriba);
        
        //La unidad ya realizo la accion del turno
        assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(1,4)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(3,4)));
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
		assertTrue(mapa.agregarUnidad(new Posicion(3,4), otraUnidad));
		assertFalse(unaUnidad.mover(TipoDireccion.Arriba));
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
		assertTrue(mapa.agregarUnidad(new Posicion(3,4), otraUnidad));
		assertFalse(unaUnidad.mover(TipoDireccion.Arriba));
    }
    
    
}
