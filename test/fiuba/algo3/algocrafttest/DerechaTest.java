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
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Derecha;
import junit.framework.TestCase;

public class DerechaTest extends TestCase{
	
    public void testDerechaDeberiaMoverLaUnidadUnaPosicionParaLaDerecha() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
        Derecha moverDerecha= new Derecha();
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador);
		moverDerecha.setCelda(unaUnidad);
		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,4)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,5)));
    }
}
