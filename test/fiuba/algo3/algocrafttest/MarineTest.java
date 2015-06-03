package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Barraca;
import fiuba.algo3.algocraft.Marine;
import fiuba.algo3.algocraft.Unidades;
import junit.framework.TestCase;

public class MarineTest extends TestCase {
	
	public void testCrearMarine(){
		Barraca unaBarraca = new Barraca();
		Unidades unMarine = new Marine();
		assertNotNull(unMarine);
	}
}
