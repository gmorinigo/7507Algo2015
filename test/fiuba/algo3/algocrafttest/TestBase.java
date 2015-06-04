package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import junit.framework.TestCase;

abstract class TestBase extends TestCase {
	
	public void setUp() {
		Mapa.getInstance().reset();
	}
	
}
