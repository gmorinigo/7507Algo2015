package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarProtoss;
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
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia.TipoAtaqueNaveCiencia;
import fiuba.algo3.algocrafttest.TestBase;

@SuppressWarnings("static-access")
public class NaveCienciaTest extends TestBase {

	public void testCrearNaveCiencia() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException {
		RazaTerran unaRaza = new RazaTerran(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		//AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();

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

	@SuppressWarnings("unused")
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
		
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaNaveCiencia = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).obtenerCantidadEnergia(), 50);
	}

	@SuppressWarnings("unused")
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
		
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaNaveCiencia = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).obtenerCantidadEnergia(), 50);

		for (int i = 0; i <= 4; i++)
			unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).obtenerCantidadEnergia(), 100);
	}
	
	public void testCrearNaveCienciaCreadaacumula200DeEnergia()
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
		assertEquals(((NaveCiencia) unaNaveCiencia).obtenerCantidadEnergia(), 150);

		for (int i = 0; i <= 4; i++)
			unTurno.avanzarTurno();
		assertEquals(((NaveCiencia) unaNaveCiencia).obtenerCantidadEnergia(), 200);
	}


	@SuppressWarnings("unused")
	public void testAtaqueZealotConMisilEMPTest() throws JugadorConNombreDemasiadoCortoException,
													 NoSuchObjectException, CantidadDeMineralInsuficienteException, 
													CantidadDeGasInsuficienteException,
													 ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, 
													 ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, 
													 ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, 
													 NoHaySuficientesRecursos, 
													 NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{
		
		
	/** Se crea Jugador Terran **/	
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss razaProtoss = new RazaProtoss();
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();


    /** Se crea Jugador Terran **/
		
		Jugador otroJugador = new Jugador("Nombre",razaProtoss,"Azul");
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenGas().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstruccionesProtoss = razaProtoss.getFactoryConstrucciones();
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		unTurno.vaciarObservers();
		
	/** Se crean Construcciones **/	
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstruccionesProtoss.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
	/** Se crea Barraca Fabrica y Puerto Estelar Terran **/	
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();

	/** Se Crea NAVE CIENCIA  **/
		
		NaveCiencia unaNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertTrue(unaNaveCiencia.estaOperativa());
		assertTrue(unaNaveCiencia instanceof NaveCiencia);
	
	/** Se Crea unidadProtoss  **/
		factoryConstruccionesProtoss.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(17,14), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarProtoss unPuertoProtoss = (PuertoEstelarProtoss) factoryConstruccionesProtoss.crearConstruccion(TipoConstruccion.creadorUnidadesVoladoras, new Posicion(27,25), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();	
		Unidad unidadProtoss = (Unidad) unPuertoProtoss.crearUnidad(otroJugador,TipoUnidad.volador1);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		assertEquals(100, unidadProtoss.obtenerCantidadEscudo());
		assertEquals(150, unidadProtoss.obtenerCantidadVida());
		unaNaveCiencia.atacar(unidadProtoss.dameCelda(), TipoAtaqueNaveCiencia.MisilEMP);
		assertEquals(0, unidadProtoss.obtenerCantidadEscudo());
		assertEquals(150, unidadProtoss.obtenerCantidadVida());
	}
	
	
	@SuppressWarnings("unused")
	public void testAtaqueZealotConRadiacion() throws JugadorConNombreDemasiadoCortoException,
			 NoSuchObjectException, CantidadDeMineralInsuficienteException, 
			 CantidadDeGasInsuficienteException,
			 ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, 
			 ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, 
			 ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, 
			 NoHaySuficientesRecursos, 
			 NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada{
		
		
		/** Se crea Jugador Terran **/	
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss razaProtoss = new RazaProtoss();
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		
		
		/** Se crea Jugador Terran **/
		
		Jugador otroJugador = new Jugador("Nombre",razaProtoss,"Azul");
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenGas().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstruccionesProtoss = razaProtoss.getFactoryConstrucciones();
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		unTurno.vaciarObservers();
		
		/** Se crean Construcciones **/	
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstruccionesProtoss.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		/** Se crea Barraca Fabrica y Puerto Estelar Terran **/	
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		/** Se Crea NAVE CIENCIA  **/
		
		NaveCiencia unaNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertTrue(unaNaveCiencia.estaOperativa());
		assertTrue(unaNaveCiencia instanceof NaveCiencia);
		
		/** Se Crea unidadProtoss  **/
		factoryConstruccionesProtoss.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(17,14), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarProtoss unPuertoProtoss = (PuertoEstelarProtoss) factoryConstruccionesProtoss.crearConstruccion(TipoConstruccion.creadorUnidadesVoladoras, new Posicion(27,25), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();	
		Unidad unidadProtoss = (Unidad) unPuertoProtoss.crearUnidad(otroJugador,TipoUnidad.volador1);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		assertEquals(100, unidadProtoss.obtenerCantidadEscudo());
		assertEquals(150, unidadProtoss.obtenerCantidadVida());
		unaNaveCiencia.atacar(unidadProtoss.dameCelda(), TipoAtaqueNaveCiencia.Radiacion);
		assertEquals(80, unidadProtoss.obtenerCantidadEscudo());
		assertEquals(150, unidadProtoss.obtenerCantidadVida());
		for (int i = 0 ; i < 16;i++){
			unTurno.avanzarTurno();
			assertTrue(unidadProtoss.estaViva());
		}
		unTurno.avanzarTurno();
		assertFalse(unidadProtoss.estaViva());
	}
}
