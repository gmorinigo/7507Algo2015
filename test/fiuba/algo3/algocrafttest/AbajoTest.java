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
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;

public class AbajoTest extends TestBase{
	
    public void testAbajoDeberiaMoverLaUnidadUnaPosicionParaAbajo() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
       
        Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1,unJugador);
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);

		Movimiento mov = new Movimiento(unaUnidad);
		
        mov.mover(TipoDireccion.Abajo);
		assertNotSame(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(4,4)));
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(5,4)));
        
        mov.mover(TipoDireccion.Abajo);
        mov.mover(TipoDireccion.Abajo);
        mov.mover(TipoDireccion.Abajo);
        
        assertEquals(unaUnidad.dameCelda(),mapa.dameCelda(new Posicion(8,4)));
    }

}
