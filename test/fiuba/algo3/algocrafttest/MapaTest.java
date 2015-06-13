package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoReuneLosRequisitosException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

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

	public void testAgregarUnaConstruccionAlMapa() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion55 = new Posicion(5,5);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion55, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
			
		}

		assertTrue(unMapa.dameConstrucciones().contains(unaBarraca));
	}

	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion22));
		assertTrue(unMapa.verificarCeldaOcupada(posicion23));
		assertTrue(unMapa.verificarCeldaOcupada(posicion32));
		assertTrue(unMapa.verificarCeldaOcupada(posicion33));
	}
	
	public void testAgregarUnExtractorDeMineralYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion911 = new Posicion(9,11);
		Posicion posicion912 = new Posicion(9,12);
		Posicion posicion1011 = new Posicion(10,11);
		Posicion posicion1012 = new Posicion(10,12);
		Mapa unMapa = Mapa.getInstance();
		CentroDeMineral unCentroDeMineral = new CentroDeMineral(posicion911, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unCentroDeMineral);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion911));
		assertFalse(unMapa.verificarCeldaOcupada(posicion912));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1011));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1012));
	}
	
	
	public void testAgregarUnExtractorDeMineralEnUnaCeldaQueNoTieneMineral() throws JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion912 = new Posicion(9,12);

		Mapa unMapa = Mapa.getInstance();
		CentroDeMineral unCentroDeMineral = new CentroDeMineral(posicion912, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unCentroDeMineral);
		} catch (ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException e) {
			return;
		}
		
		fail();
	}
	
	
	public void testAgregarUnExtractorDeGasYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion99 = new Posicion(9,9);
		Posicion posicion910 = new Posicion(9,10);
		Posicion posicion109 = new Posicion(10,9);
		Posicion posicion1010 = new Posicion(10,10);
		Mapa unMapa = Mapa.getInstance();
		Refineria unaRefineria = new Refineria(posicion99, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaRefineria);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion99));
		assertFalse(unMapa.verificarCeldaOcupada(posicion910));
		assertFalse(unMapa.verificarCeldaOcupada(posicion109));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1010));
	}

	
	public void testAgregarUnExtractorDeGasEnUnaCeldaQueNoTieneGas() throws JugadorConNombreDemasiadoCortoException, CeldaOcupadaException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException{
		Posicion posicion910 = new Posicion(9,10);

		Mapa unMapa = Mapa.getInstance();
		Refineria unaRefineria = new Refineria(posicion910, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaRefineria);
		} catch (ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException e) {
			return;
		}
		
		fail();
	}
	
	
	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasQueOcupa() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException{
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new RazaProtoss(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}
		
		ArrayList<Celda> celdas = unaBarraca.dameCeldas();
		
		assertTrue(celdas.get(0).obtenerPosicion().compararPosicion(posicion22));
		assertTrue(celdas.get(1).obtenerPosicion().compararPosicion(posicion32));
		assertTrue(celdas.get(2).obtenerPosicion().compararPosicion(posicion23));
		assertTrue(celdas.get(3).obtenerPosicion().compararPosicion(posicion33));
	}
	
	public void testAgregarUnaTropaAlMapa() throws JugadorConNombreDemasiadoCortoException, ConstruccionExtractorDeMineralEnCeldaQueNoTieneMineralException, ConstruccionExtractorDeGasEnCeldaQueNoTieneGasException, CeldaOcupadaException, NoReuneLosRequisitosException, NoSuchObjectException{
		Posicion posicion1515 = new Posicion(15,15);
		Posicion posicion1715 = new Posicion(17,15);
		Mapa unMapa = Mapa.getInstance();
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Barraca unaBarraca = new Barraca(posicion1515, unJugador );
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}
		
		Turno unTurno = new Turno(unJugador);
		
		unaBarraca.crearEstructura(unTurno);
		
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
		
		Unidad marine = unaBarraca.crearUnidad();
		unTurno.addObserver(marine);
		
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
				
		unMapa.agregarUnidad(posicion1715, marine);
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion1715));
	}
}


