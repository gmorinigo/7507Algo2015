package fiuba.algo3.algocrafttest;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
/*import fiuba.algo3.algocraft.modelo.recursos.GasVespano;
import fiuba.algo3.algocraft.modelo.recursos.Mineral;
import fiuba.algo3.algocraft.modelo.recursos.Recurso;*/

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
	/*
	public void testAgregarRecursosAlMapaDeberiaAgregarRecursosALaLista(){
		Posicion posicion55 = new Posicion(5,5);
		Posicion posicion89 = new Posicion(8,9);
		Mapa unMapa = Mapa.getInstance();
		GasVespano unGasVespano = new GasVespano(posicion55);
		Recurso unMineral    = new Mineral(posicion89);
		unMapa.agregarRecurso(unGasVespano);
		unMapa.agregarRecurso(unMineral);
		assertTrue(unMapa.dameRecursos().contains(unGasVespano));
		assertTrue(unMapa.dameRecursos().contains(unMineral));
	}
	*/
	public void testAgregarUnaConstruccionAlMapa() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion55 = new Posicion(5,5);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion55, new Jugador("unNombre",new Raza(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}

		assertTrue(unMapa.dameConstrucciones().contains(unaBarraca));
	}

	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new Raza(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion22));
		assertTrue(unMapa.verificarCeldaOcupada(posicion23));
		assertTrue(unMapa.verificarCeldaOcupada(posicion32));
		assertTrue(unMapa.verificarCeldaOcupada(posicion33));
	}
	
	public void testAgregarUnExtractorDeMineralYVerificarLasCeldasOcupadas() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion99 = new Posicion(9,9);
		Posicion posicion910 = new Posicion(9,10);
		Posicion posicion109 = new Posicion(10,9);
		Posicion posicion1010 = new Posicion(10,10);
		Mapa unMapa = Mapa.getInstance();
		Refineria unaRefineria = new Refineria(posicion99, new Jugador("unNombre",new Raza(),"Azul"));
		
		try {
			unMapa.agregarConstruccion(unaRefineria);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion99));
		assertFalse(unMapa.verificarCeldaOcupada(posicion910));
		assertFalse(unMapa.verificarCeldaOcupada(posicion109));
		assertFalse(unMapa.verificarCeldaOcupada(posicion1010));
	}
	
	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasQueOcupa() throws JugadorConNombreDemasiadoCortoException{
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion23 = new Posicion(2,3);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion33 = new Posicion(3,3);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion22, new Jugador("unNombre",new Raza(),"Azul"));
		
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
}

