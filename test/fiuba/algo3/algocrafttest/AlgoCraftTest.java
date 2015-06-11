package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoColorException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCantidadDeJugadoresSuperadaException;

public class AlgoCraftTest extends TestBase {
	public void testCrearAlgoCraft(){
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		assertNotNull(unJuegoAlgoCraft);
	}

	public void testVerificarJuegoConMapaCreado(){
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		
		assertNotNull(unJuegoAlgoCraft.dameElMapaDelJuego());
	}
	
	public void testVerificarCreacionDeLosJugadores() throws JugadorConNombreDemasiadoCortoException {
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza,"Rojo");
		} catch (MaximaCantidadDeJugadoresSuperadaException | JugadorConElMismoNombreException | JugadorConElMismoColorException e) {
		}
		
		assertTrue(unJuegoAlgoCraft.obtenerCantidadDeJugadores() == 1); 
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador2",unaRaza,"Azul");
		} catch (MaximaCantidadDeJugadoresSuperadaException | JugadorConElMismoNombreException | JugadorConElMismoColorException e) {
		}
		
		assertTrue(unJuegoAlgoCraft.obtenerCantidadDeJugadores() == 2);
		
	}

	
	public void testVerificarExcepcionJugadorConElMismoNombre() 
	throws MaximaCantidadDeJugadoresSuperadaException, JugadorConElMismoColorException, JugadorConNombreDemasiadoCortoException{
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza,"Rojo");
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza,"Azul");
		} catch ( JugadorConElMismoNombreException e) {
			return;
		}
		
		fail(); 
		
	}		

	public void testVerificarExcepcionJugadorConNombreCorto() 
			throws MaximaCantidadDeJugadoresSuperadaException, JugadorConElMismoColorException, JugadorConElMismoNombreException{
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("asd",unaRaza,"Rojo");
		} catch ( JugadorConNombreDemasiadoCortoException e) {
			return;
		}

		fail(); 
	}		

	public void testVerificarExcepcionJugadorConElMismoColor() 
	throws MaximaCantidadDeJugadoresSuperadaException, JugadorConElMismoNombreException, JugadorConNombreDemasiadoCortoException{
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza,"Rojo");
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador2",unaRaza,"Rojo");
		} catch ( JugadorConElMismoColorException e) {
			return;
		}
		
		fail(); 
		
	}		

	public void testVerificarExcepcionMaximaCantidadDeJugadoresSuperada() 
	throws JugadorConElMismoNombreException, JugadorConElMismoColorException, JugadorConNombreDemasiadoCortoException{
		AlgoCraft unJuegoAlgoCraft = new AlgoCraft();
		Raza unaRaza = new Raza();
		
		try {
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador",unaRaza, "Rojo");
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador1",unaRaza, "Verde");
			unJuegoAlgoCraft.agregarJugador("nombreDelJugador2",unaRaza, "Azul");
		} catch ( MaximaCantidadDeJugadoresSuperadaException e) {
			return;
		}
		
		fail(); 
		
	}		
	
	
	
}
