package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Posicion;
import junit.framework.TestCase;

public class PosicionTest extends TestCase{

	public void testDameFilaTieneQueDevolverLaFila(){
		Posicion unaPosicion = new Posicion(7,4);
		assertTrue(unaPosicion.dameFila() == 7);
	}
	
	public void testDameColumnaTieneQueDevolverLaColumna(){
		Posicion unaPosicion = new Posicion(7,4);
		assertTrue(unaPosicion.dameColumna() == 4);
	}
}