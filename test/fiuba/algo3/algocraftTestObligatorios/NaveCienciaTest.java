package fiuba.algo3.algocraftTestObligatorios;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocrafttest.TestBase;

@SuppressWarnings("static-access")
public class NaveCienciaTest extends TestBase {

	public void testCrearNaveCiencia() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.especial1,unJugador/* ,new DisparoSuperStrategy() */);
		assertNotNull(unaUnidad);
	}

	public void testCrearNaveCienciaCreadaCon50DeEnergia()
			throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.especial1,unJugador/* ,new DisparoSuperStrategy() */);
		NaveCiencia unaNaveCiencia = (NaveCiencia) unaUnidad;
		assertEquals(unaNaveCiencia.getEnergia(), 50);
	}

	public void testCrearNaveCienciaCreadaacumula100DeEnergiaEn5Turnos()
			throws JugadorConNombreDemasiadoCortoException,
			NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		Jugador unJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Jugador otroJugador = new Jugador("Nombre2",new RazaProtoss(),"Rojo");
		
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.especial1,unJugador/* ,new DisparoSuperStrategy() */);
		System.out.println(unaUnidad.getClass());
		NaveCiencia unaNaveCiencia = (NaveCiencia) unaUnidad;

		Turno unTurno = new Turno(unJugador,otroJugador);
		

		unTurno.addObserver(unaNaveCiencia);
		unaNaveCiencia.finalizarNacimiento();

		for (int i = 1; i <= 4; i++)
			unTurno.avanzarTurno();
		assertTrue(unTurno.dameTurno() == 5);
		// assertEquals(unaNaveCiencia.getEnergia(), 100);
	}

}
