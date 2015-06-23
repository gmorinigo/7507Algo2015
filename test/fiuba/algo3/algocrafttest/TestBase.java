package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import junit.framework.TestCase;

public abstract class TestBase extends TestCase {
	
	public void setUp() {
		Mapa.getInstance().reset();
		Turno.reset();
	}
	
}
