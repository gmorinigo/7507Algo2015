package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
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
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;

@SuppressWarnings("static-access")
public class RangoAtaqueEspectroTest extends TestBase{
	public void testRangoAtaquePorTierraUnidadEspectroDebeSer5() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
        Mapa mapa = Mapa.getInstance();
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss otraRaza = new RazaProtoss();
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		Posicion unaPosicion = new Posicion(12,3);
		Posicion unaPosicion2 = new Posicion(1,1);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444 = new Posicion(44,4);
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();	
		Barraca unaConstruccion = (Barraca) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",otraRaza,"Azul");
		
		AbstractConstruccionFactory otroFactoryConstrucciones = otraRaza.getFactoryConstrucciones();
		Acceso otraConstruccion = (Acceso) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion2, unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaFabrica.estaOperativa());
		
		PuertoEstelarTerran unPuertoEstelar = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelar);
		for (int i = 0; i<10;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelar.estaOperativa());
		
		Posicion posicion1010 = new Posicion(10,10);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, posicion1010, unJugador);
		Construccion otroExpansor = (Construccion) otroFactoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(15,15), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());

		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelar.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(4,5), otraUnidad);
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(52,otraUnidad.obtenerCantidadEscudo());	
	}

	public void testRangoAtaquePorAireUnidadEspectroDebeSer5() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
        Mapa mapa = Mapa.getInstance();
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss otraRaza = new RazaProtoss();
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		
		Posicion unaPosicion = new Posicion(12,3);
		Posicion unaPosicion2 = new Posicion(1,1);
		Posicion posicion1414 = new Posicion(14,14);
		Posicion posicion444 = new Posicion(44,4);
		Posicion otraPosicion = new Posicion(15,7);
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();	
		Barraca unaConstruccion = (Barraca) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",otraRaza,"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		AbstractConstruccionFactory otroFactoryConstrucciones = otraRaza.getFactoryConstrucciones();
		Acceso unAcceso = (Acceso) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(unAcceso);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion2, unJugador);
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion1414, otroJugador);
		
		unTurno.addObserver(unaFabrica);
		unTurno.addObserver(unPuertoEstelar);
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaFabrica.estaOperativa());
		
		PuertoEstelarTerran unPuertoEstelarTerran = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelarTerran);
		for (int i = 0; i<10;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelarTerran.estaOperativa());
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) otroFactoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelarTerran.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) unPuertoEstelar.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(4,9), otraUnidad);
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(80,otraUnidad.obtenerCantidadEscudo());	
	}
	
	public void testRangoNoAtaquaPorAireUnidadEspectroSiElEnemigoEstaAMasDe5Rangos() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
        Mapa mapa = Mapa.getInstance();
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss otraRaza = new RazaProtoss();
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		
		Posicion unaPosicion = new Posicion(12,3);
		Posicion unaPosicion2 = new Posicion(1,1);
		Posicion posicion1414= new Posicion(14,14);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444 = new Posicion(44,4);
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();	
		Barraca unaConstruccion = (Barraca) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",otraRaza,"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(10000);
		
		AbstractConstruccionFactory otroFactoryConstrucciones = otraRaza.getFactoryConstrucciones();
		Acceso unAcceso = (Acceso) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(unAcceso);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion2, unJugador);
		PuertoEstelarProtoss unPuertoEstelar = (PuertoEstelarProtoss) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicion1414, otroJugador);
		
		unTurno.addObserver(unaFabrica);
		unTurno.addObserver(unPuertoEstelar);
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaFabrica.estaOperativa());
		
		PuertoEstelarTerran unPuertoEstelarTerran = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelarTerran);
		for (int i = 0; i<10;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelarTerran.estaOperativa());
		
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) otroFactoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelarTerran.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) unPuertoEstelar.crearUnidad(otroJugador,unTipoUnidad.volador1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(4,10), otraUnidad);
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(150, otraUnidad.obtenerCantidadVida());
		assertEquals(100,otraUnidad.obtenerCantidadEscudo());	
	}
	
	public void testRangoAtaqueUnidadEspectroNoPuedeAtacarSiEstaAMasDe5() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
        Mapa mapa = Mapa.getInstance();
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss otraRaza = new RazaProtoss();
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Rojo");
		
		Posicion unaPosicion = new Posicion(12,3);
		Posicion unaPosicion2 = new Posicion(1,1);
		Posicion otraPosicion = new Posicion(15,7);
		Posicion posicion444 = new Posicion(44,4);
		
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();	
		Barraca unaConstruccion = (Barraca) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
		
		Jugador otroJugador = new Jugador("Nombre",otraRaza,"Azul");
		unJugador.dameAlmacenMineral().almacenarRecurso(10000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(10000);
		AbstractConstruccionFactory otroFactoryConstrucciones = otraRaza.getFactoryConstrucciones();
		Acceso otraConstruccion = (Acceso) otroFactoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
		
		Turno unTurno = new Turno(unJugador,otroJugador);

		
		unTurno.addObserver(unaConstruccion);
		unTurno.addObserver(otraConstruccion);
		
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesNivel2, unaPosicion2, unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i = 0; i<12;i++) unTurno.avanzarTurno();
		assertTrue(unaFabrica.estaOperativa());
		
		PuertoEstelarTerran unPuertoEstelarTerran = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesEspecialesYVoladoras, posicion444, unJugador);
		
		unTurno.addObserver(unPuertoEstelarTerran);
		for (int i = 0; i<10;i++) unTurno.avanzarTurno();
		assertTrue(unPuertoEstelarTerran.estaOperativa());
		
		
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion otroExpansor = (Construccion) otroFactoryConstrucciones.crearConstruccion(unTipo.expansorPoblacion, new Posicion(120,120), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(otroExpansor);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		assertTrue(unExpansor.estaOperativa());
		assertTrue(otroExpansor.estaOperativa());
		
		TipoUnidad unTipoUnidad = null;
		Unidad unaUnidad = (Unidad) unPuertoEstelarTerran.crearUnidad(unJugador,unTipoUnidad.volador1);
		UnidadProtoss otraUnidad = (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(4,11), otraUnidad);
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());
		
		unaUnidad.atacar(otraUnidad.dameCelda());
		
		assertEquals(100, otraUnidad.obtenerCantidadVida());
		assertEquals(60,otraUnidad.obtenerCantidadEscudo());	
	}

}
