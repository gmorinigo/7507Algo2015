package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.Fabrica;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocrafttest.TestBase;

@SuppressWarnings("static-access")
public class NaveCienciaTest extends TestBase {

	public void testCrearNaveCiencia() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException {
		RazaTerran unaRaza = new RazaTerran(); 
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();

		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		unTurno.addObserver(unaUnidad);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertNotNull(unaUnidad);
	}

	public void testCrearNaveCienciaCreadaCon50DeEnergia()
			throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException {
		RazaTerran unaRaza = new RazaTerran(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");

		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaNaveCiencia = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		unTurno.addObserver(unaNaveCiencia);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).getEnergia(), 50);
	}

	public void testCrearNaveCienciaCreadaacumula100DeEnergiaEn5Turnos()
			throws JugadorConNombreDemasiadoCortoException,
			NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException {
		RazaTerran unaRaza = new RazaTerran(); 
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");

		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaNaveCiencia = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		unTurno.addObserver(unaNaveCiencia);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).getEnergia(), 50);

		for (int i = 0; i <= 4; i++)
			unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).getEnergia(), 100);
	}

}
