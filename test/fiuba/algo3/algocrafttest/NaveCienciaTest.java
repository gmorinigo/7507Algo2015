package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;

public class NaveCienciaTest extends TestBase {

	public void testCrearNaveCiencia() throws NoSuchObjectException {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades
				.crearUnidad(unTipo.especial1/* ,new DisparoSuperStrategy() */);
		assertNotNull(unaUnidad);
	}

	public void testCrearNaveCienciaCreadaCon50DeEnergia()
			throws NoSuchObjectException {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades
				.crearUnidad(unTipo.especial1/* ,new DisparoSuperStrategy() */);
		NaveCiencia unaNaveCiencia = (NaveCiencia) unaUnidad;
		assertEquals(unaNaveCiencia.getEnergia(), 50);
	}

	public void testCrearNaveCienciaCreadaacumula100DeEnergiaEn5Turnos()
			throws JugadorConNombreDemasiadoCortoException,
			NoSuchObjectException {
		AbstractUnidadFactory factoryUnidades = new UnidadFactoryTerran();
		;
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades
				.crearUnidad(unTipo.especial1/* ,new DisparoSuperStrategy() */);
		System.out.println(unaUnidad.getClass());
		NaveCiencia unaNaveCiencia = (NaveCiencia) unaUnidad;
		Turno unTurno = new Turno(new Jugador("unNombre", new RazaProtoss(),
				"Azul"));

		unTurno.addObserver(unaNaveCiencia);
		unaNaveCiencia.finalizarCreacion();

		for (int i = 1; i <= 4; i++)
			unTurno.avanzarTurno();
		assertTrue(unTurno.dameTurno() == 5);
		// assertEquals(unaNaveCiencia.getEnergia(), 100);
	}

}
