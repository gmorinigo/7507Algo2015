package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import junit.framework.TestCase;

abstract class TestBase extends TestCase {
	
	public void setUp() {
		Mapa.getInstance().reset();
	}
	
}
