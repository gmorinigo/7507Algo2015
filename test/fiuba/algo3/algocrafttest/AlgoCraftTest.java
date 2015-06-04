package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import junit.framework.TestCase;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCantidadDeJugadoresSuperadaException;

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
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza);
		} catch (MaximaCantidadDeJugadoresSuperadaException | JugadorConElMismoNombreException e) {
		}
		
		assertTrue(unJuegoAlgoCraft.obtenerCantidadDeJugadores() == 1); 
		
	}

	//TODO PRUEBA EXCEPCION JUGADOR CON EL MISMO NOMBRE	
	//TODO PRUEBA EXCEPCION MAXIMA CANTIDAD DE JUGADORES
}
