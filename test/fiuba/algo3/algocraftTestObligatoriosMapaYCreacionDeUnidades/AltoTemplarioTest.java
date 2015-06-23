package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario.TipoAtaqueAltoTemplario;


@SuppressWarnings("static-access")
public class AltoTemplarioTest extends TestBase {
	public void testCrearUnidadAltoTemplario() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		Posicion posicionAltoTemplario = new Posicion(20,8);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicion3 = new Posicion(12,3);		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicion3 , unJugador);

		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		unTurno.addObserver(unaConstruccion);
		
		unTurno.addObserver(unAcceso);
		unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);

		unTurno.addObserver(unArchivoTemplario);
			
		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());
		
		unTurno.addObserver(unArchivoTemplario.crearUnidad(unJugador));
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		Celda celdaAltoTemplario = unMapa.dameCelda(posicionAltoTemplario);
		
		assertTrue(celdaAltoTemplario.obtenerUnidad() instanceof AltoTemplario);
	}

	@SuppressWarnings("unused")
	public void testCrearUnAltoTemplarioYVerificarLaCantidadDeEnergiaAcumuladaAlPasarLosTurnos() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(6,4);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion terceraPosicion = new Posicion(20,9);
		Posicion posicionAltoTemplario = new Posicion(20,8);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		unTurno.vaciarObservers();
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicion3 = new Posicion(12,3);		
		Construccion unaConstruccion = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicion3 , unJugador);

		Construccion unAcceso = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		//unTurno.addObserver(unaConstruccion);
		
		//unTurno.addObserver(unAcceso);
		//unJugador.agregarConstruccion(unAcceso);
		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, otraPosicion, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, terceraPosicion, unJugador);

		unTurno.addObserver(unArchivoTemplario);
			
		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		unArchivoTemplario.crearUnidad(unJugador);
		//unTurno.addObserver();

		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		Celda celdaAltoTemplario = unMapa.dameCelda(posicionAltoTemplario);
		
		AltoTemplario unAltoTemplario = (AltoTemplario) celdaAltoTemplario.obtenerUnidad();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();
		
		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
	}

	public void testLanzarAlucionacionAUnaUnidad() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion posicionAcceso = new Posicion(20,7);
		Posicion posicionPuertoEstelar = new Posicion(15,7);
		Posicion posicionArchivosTemplarios = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicionExpPob = new Posicion(12,3);		
		factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicionExpPob , unJugador);

		Acceso unAcceso = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAcceso, unJugador);

		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicionPuertoEstelar, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, posicionArchivosTemplarios, unJugador);

		//unTurno.addObserver(unArchivoTemplario);
			
		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		AltoTemplario unAltoTemplario = (AltoTemplario) unArchivoTemplario.crearUnidad(unJugador);

		TipoUnidad unTipoUnidad = TipoUnidad.terrestre1;
		Unidad unaUnidad = (Unidad) unAcceso.crearUnidad(unJugador, unTipoUnidad);
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();

		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
        assertEquals(unaUnidad.dameCelda(),unMapa.dameCelda(new Posicion(20,6)));

		unaUnidad.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		unaUnidad.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		
        assertEquals(unaUnidad.dameCelda(),unMapa.dameCelda(new Posicion(22,6)));

		assertTrue(unAltoTemplario.atacar(unaUnidad.dameCelda(), TipoAtaqueAltoTemplario.Alucinacion));
		
		Zealot alucinacionIzquierda = (Zealot) unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad();
		Zealot alucinacionDerecha = (Zealot) unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad();
		
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad() instanceof Zealot);
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad() instanceof Zealot);
		
		assertTrue(alucinacionDerecha.esUnaAlucinacion());
		assertTrue(alucinacionIzquierda.esUnaAlucinacion());
		assertEquals(0,alucinacionIzquierda.obtenerCantidadVida());
		assertEquals(60,alucinacionIzquierda.obtenerCantidadEscudo());
	}
	
	public void testTormentaPsionica() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Posicion posicionAccesoJug1 = new Posicion(17,7);

		Posicion posicionAcceso = new Posicion(20,7);
		Posicion posicionPuertoEstelar = new Posicion(15,7);
		Posicion posicionArchivosTemplarios = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		unTurno.vaciarObservers();
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicionExpPob = new Posicion(12,3);		
		factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicionExpPob , unJugador);

		factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAccesoJug1, unJugador);

		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicionPuertoEstelar, unJugador);
		unTurno.addObserver(unPuertoEstelar);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspeciales, posicionArchivosTemplarios, unJugador);

		Posicion posicionExpPobJug2 = new Posicion(20,20);		
		factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicionExpPobJug2 , otroJugador);

		Acceso accesoJug2 = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAcceso, unJugador);
			
		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		AltoTemplario unAltoTemplario = (AltoTemplario) unArchivoTemplario.crearUnidad(unJugador);

		TipoUnidad unTipoUnidad = TipoUnidad.terrestre1;
		Unidad unaUnidad = (Unidad) accesoJug2.crearUnidad(unJugador, unTipoUnidad);
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();

		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
        assertEquals(unaUnidad.dameCelda(),unMapa.dameCelda(new Posicion(20,6)));

		unaUnidad.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		unaUnidad.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		
        assertEquals(unaUnidad.dameCelda(),unMapa.dameCelda(new Posicion(22,6)));

		assertTrue(unAltoTemplario.atacar(unaUnidad.dameCelda(), TipoAtaqueAltoTemplario.TormentaPsionica));
		assertEquals(60, unaUnidad.obtenerCantidadVida());
		assertEquals(0, unaUnidad.obtenerCantidadEscudo());
		unTurno.avanzarTurno();
		assertEquals(60, unaUnidad.obtenerCantidadVida());
		assertEquals(5, unaUnidad.obtenerCantidadEscudo());
		unTurno.avanzarTurno();
		assertFalse(unaUnidad.estaViva());
	}

}

