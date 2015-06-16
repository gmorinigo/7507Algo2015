package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocrafttest.TestBase;

public class MapaTest extends TestBase {

	public void testMapaTieneQueContenerTodasLasCeldas(){
		
		Mapa unMapa = Mapa.getInstance();
		Posicion posicion00 = new Posicion(0,0);
		Posicion posicion024 = new Posicion(0,24);
		Posicion posicion240 = new Posicion(24,0);
		Posicion posicion2424 = new Posicion(24,24);
		
		assertNotNull(unMapa.dameCelda(posicion00));
		assertNotNull(unMapa.dameCelda(posicion024));
		assertNotNull(unMapa.dameCelda(posicion240));
		assertNotNull(unMapa.dameCelda(posicion2424));
	}
	
	public void testMapaTieneQueCrearCeldasDiferentesParaLasPosiciones(){
		Mapa unMapa = Mapa.getInstance();
		Posicion posicion02 = new Posicion(0,2);
		Posicion posicion31 = new Posicion(3,1);
		assertNotSame(unMapa.dameCelda(posicion02),unMapa.dameCelda(posicion31));
	}

	public void testAgregarUnaConstruccionAlMapa() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion55 = new Posicion(5,5);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion55, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.creadorUnidadesBasicas);
		
		unMapa.agregarConstruccion(unaBarraca);
		assertTrue(unMapa.dameConstrucciones().contains(unaBarraca));
	}

	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException {
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.creadorUnidadesBasicas );
		
		assertTrue(unMapa.agregarConstruccion(unaBarraca));
	
		assertTrue(unMapa.verificarCeldaOcupada(posicion22));
		assertTrue(unMapa.verificarCeldaOcupada(posicion23));
		assertTrue(unMapa.verificarCeldaOcupada(posicion32));
		assertTrue(unMapa.verificarCeldaOcupada(posicion33));
	}
	
	public void testAgregarUnExtractorDeMineralYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion911 = new Posicion(9,11);
		Posicion posicion912 = new Posicion(9,12);
		Posicion posicion1011 = new Posicion(10,11);
		Posicion posicion1012 = new Posicion(10,12);
		Mapa unMapa = Mapa.getInstance();
		CentroMineral unCentroDeMineral = new CentroMineral(posicion911, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.extractorMineral);
		
		assertTrue(unMapa.agregarConstruccion(unCentroDeMineral));
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion911));
		assertFalse(unMapa.verificarCeldaOcupada(posicion912));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1011));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1012));
	}
	
	
	public void testAgregarUnExtractorDeMineralEnUnaCeldaQueNoTieneMineralYNoRecolectaNada() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion912 = new Posicion(9,12);

		Mapa unMapa = Mapa.getInstance();
		CentroMineral unCentroDeMineral = new CentroMineral(posicion912, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.extractorMineral);
		
		
		assertTrue(unMapa.agregarConstruccion(unCentroDeMineral));
		fail();
	}
	
	
	public void testAgregarUnExtractorDeGasYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion99 = new Posicion(9,9);
		Posicion posicion910 = new Posicion(9,10);
		Posicion posicion109 = new Posicion(10,9);
		Posicion posicion1010 = new Posicion(10,10);
		Mapa unMapa = Mapa.getInstance();
		Refineria unaRefineria = new Refineria(posicion99, new Jugador("unNombre",new RazaProtoss(),"Azul"), TipoConstruccion.extractorGas);
		
		assertTrue(unMapa.agregarConstruccion(unaRefineria));
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion99));
		assertFalse(unMapa.verificarCeldaOcupada(posicion910));
		assertFalse(unMapa.verificarCeldaOcupada(posicion109));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1010));
	}

	
	public void testAgregarUnExtractorDeGasEnUnaCeldaQueNoTieneGasYNoRecolectaNada() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion910 = new Posicion(9,10);

		Mapa unMapa = Mapa.getInstance();
		Refineria unaRefineria = new Refineria(posicion910, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.extractorGas);
		
		unMapa.agregarConstruccion(unaRefineria);
		fail();
		
	}
	
	
	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasQueOcupa() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.creadorUnidadesBasicas);
		
		assertTrue(unMapa.agregarConstruccion(unaBarraca));
		
		ArrayList<Celda> celdas = unaBarraca.dameCeldas();
		
		assertTrue(celdas.get(0).obtenerPosicion().compararPosicion(posicion22));
		assertTrue(celdas.get(1).obtenerPosicion().compararPosicion(posicion32));
		assertTrue(celdas.get(2).obtenerPosicion().compararPosicion(posicion23));
		assertTrue(celdas.get(3).obtenerPosicion().compararPosicion(posicion33));
	}
	
	public void testAgregarUnaTropaAlMapa() throws JugadorConNombreDemasiadoCortoException, NoReuneLosRequisitosException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos{
		Posicion posicion1515 = new Posicion(15,15);
		Posicion posicion1715 = new Posicion(17,15);
		Mapa unMapa = Mapa.getInstance();
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Barraca unaBarraca = new Barraca(posicion1515, unJugador,TipoConstruccion.creadorUnidadesBasicas );
		
		assertTrue(unMapa.agregarConstruccion(unaBarraca)); 
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		
		Unidad marine = unaBarraca.crearUnidad(unJugador);
		unTurno.addObserver(marine);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();	
		assertTrue(unMapa.agregarUnidad(posicion1715, marine));
		assertTrue(unMapa.verificarCeldaOcupada(posicion1715));
	}
	
	
	public void testNoSePuedoAgregarUnaEstructuraGrandeAlBordeDerechoDelMapa() throws JugadorConNombreDemasiadoCortoException {
		Posicion posicion_1_199 = new Posicion(1,199);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion_1_199, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.creadorUnidadesBasicas);
		
		assertFalse(unMapa.agregarConstruccion(unaBarraca));
	}
	

	public void testNoSePuedeAgregarUnaEstructuraGrandeAlBordeInferiorDelMapa() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion_1_199 = new Posicion(199,1);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion_1_199, new Jugador("unNombre",new RazaProtoss(),"Azul"),TipoConstruccion.creadorUnidadesBasicas);
		
		assertFalse(unMapa.agregarConstruccion(unaBarraca));
		
	}
	
	public void testAgregarUnaEstructuraGrandeAlBordeInferiorDerechoDelMapaTieneQueDevolverExcepcion() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion_1_199 = new Posicion(199,199);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion_1_199, new Jugador("unNombre",new RazaProtoss(),"Azul"), TipoConstruccion.creadorUnidadesBasicas);
		
		assertFalse(unMapa.agregarConstruccion(unaBarraca));
	}
	
	public void testAgregarUnaNaveDeTransporteAlMapaCargarleUnaUnidadPasarPorElEspacioYDejarlaDelOtroLado() throws JugadorConNombreDemasiadoCortoException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, NoSuchObjectException{
		// TODO: Agregar la funcionalidad de este test
		Posicion posicion1515 = new Posicion(15,15);
		Posicion posicion1715 = new Posicion(17,15);
		Mapa unMapa = Mapa.getInstance();
		Jugador unJugador = new Jugador("unNombre",new RazaTerran(),"Azul");
		Barraca unaBarraca = new Barraca(posicion1515, unJugador,TipoConstruccion.creadorUnidadesBasicas );
		
		assertTrue(unMapa.agregarConstruccion(unaBarraca));
		
		Jugador otroJugador = new Jugador("Nombre",new RazaTerran(),"Rojo");
		Turno unTurno = new Turno(unJugador,otroJugador);
		
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		
		Unidad marine = unaBarraca.crearUnidad(unJugador);
		unTurno.addObserver(marine);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
				
		assertTrue(unMapa.agregarUnidad(posicion1715, marine));
		assertTrue(unMapa.verificarCeldaOcupada(posicion1715));
	}
}


