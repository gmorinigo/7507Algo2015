package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;

public class JugadorTest extends TestBase {

	public void testJugadorVerificaElNombreConElQueFueCreado() throws JugadorConNombreDemasiadoCortoException{
		Jugador unJugador = new Jugador("NombreJugador", new RazaProtoss(), "unColor");
		assertEquals("NombreJugador", unJugador.dameNombre());
	}
	
	public void testJugadorVerificaElColorConElQueFueCreado() throws JugadorConNombreDemasiadoCortoException{
		Jugador unJugador = new Jugador("NombreJugador", new RazaProtoss(), "unColor");
		assertEquals("unColor", unJugador.dameColor());
	}
	
}
