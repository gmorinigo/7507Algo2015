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
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario2;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario2.TipoAtaqueAltoTemplario;


@SuppressWarnings("static-access")
public class AltoTemplarioTest extends TestBase {
	public void testCrearUnidadAltoTemplario() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
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
		
		assertTrue(celdaAltoTemplario.obtenerUnidad() instanceof AltoTemplario2);
	}

	public void testCrearUnAltoTemplarioYVerificarLaCantidadDeEnergiaAcumuladaAlPasarLosTurnos() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
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
		
		AltoTemplario2 unAltoTemplario = (AltoTemplario2) celdaAltoTemplario.obtenerUnidad();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();
		
		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
	}

	public void testLanzarAlucionacionAUnaUnidad() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
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

		Acceso unAcceso = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
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

		TipoUnidad unTipoUnidad = TipoUnidad.terrestre1;
		Unidad unaUnidad = (Unidad) unAcceso.crearUnidad(unJugador, unTipoUnidad);
		unTurno.addObserver(unaUnidad);
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		Celda celdaAltoTemplario = unMapa.dameCelda(posicionAltoTemplario);
		
		AltoTemplario2 unAltoTemplario = (AltoTemplario2) celdaAltoTemplario.obtenerUnidad();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();
		
		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
		assertTrue(unAltoTemplario.atacar(unaUnidad.dameCelda(), TipoAtaqueAltoTemplario.Alucinacion));
	}

}
