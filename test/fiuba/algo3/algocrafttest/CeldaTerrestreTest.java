package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.CeldaTerrestre;

public class CeldaTerrestreTest extends TestBase {

	public void testVerificarPosicionDeLaCelda(){
		Celda unaCelda = new CeldaTerrestre(1,1);
		assertTrue(unaCelda.obtenerPosicion().dameFila()==1);
		assertTrue(unaCelda.obtenerPosicion().dameColumna()==1);
	}
	
	public void testVerificarCeldaOcupada(){
		Celda unaCelda = new CeldaTerrestre(1,1);
		unaCelda.ocuparCelda();
		assertTrue(unaCelda.celdaOcupada());
	}
	
	public void testVerificarDesocuparCelda(){
		Celda unaCelda = new CeldaTerrestre(1,1);
		unaCelda.ocuparCelda();
		assertTrue(unaCelda.celdaOcupada());
		unaCelda.desocuparCelda();
		assertFalse(unaCelda.celdaOcupada());
	}
}
