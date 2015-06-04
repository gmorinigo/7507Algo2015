package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import junit.framework.TestCase;

public class AlgoCraftTest extends TestCase {
	public void testCrearAlgoCraft(){
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		assertNotNull(unJuegoAlgoCraft);
	}

	public void testVerificarJuegoConMapaCreado(){
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		
		assertNotNull(unJuegoAlgoCraft.dameElMapaDelJuego());
	}
	
	public void testVerificarCreacionDeLosJugadores(){
		//TODO SEGUIR DESDE ACA
		
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		
		assertNotNull(unJuegoAlgoCraft.dameElMapaDelJuego());
	}
	
}
