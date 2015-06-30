package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class TurnoTest extends TestBase {
	
	public void testCrearUnTurnoEsElPrimero() throws JugadorConNombreDemasiadoCortoException{
		Turno unTurno = new Turno(new Jugador("unNombre",new RazaProtoss(),"Azul"),new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		assertTrue(unTurno.obtenerNumeroTurno() == 1);
	}
	
	public void testAumentarUnTurnoAumentaElTurno() throws JugadorConNombreDemasiadoCortoException{
		Turno unTurno = new Turno(new Jugador("unNombre",new RazaProtoss(),"Azul"),new Jugador("otroNombre",new RazaProtoss(),"Rojo"));
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unTurno.obtenerNumeroTurno() == 2);
	}
}
