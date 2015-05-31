package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.Mapa;
import fiuba.algo3.algocraft.GasVespano;
import fiuba.algo3.algocraft.Mineral;
import fiuba.algo3.algocraft.Posicion;
import fiuba.algo3.algocraft.Recursos;
import junit.framework.TestCase;

public class MapaTest extends TestCase {

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
	
	public void testAgregarRecursosAlMapaDeberiaAgregarRecursosALaLista(){
		Posicion posicion55 = new Posicion(5,5);
		Posicion posicion89 = new Posicion(8,9);
		Mapa unMapa = Mapa.getInstance();
		GasVespano unGasVespano = new GasVespano(posicion55);
		Recursos unMineral    = new Mineral(posicion89);
		unMapa.agregarRecurso(unGasVespano);
		unMapa.agregarRecurso(unMineral);
		assertTrue(unMapa.dameRecursos().contains(unGasVespano));
		assertTrue(unMapa.dameRecursos().contains(unMineral));
	}
}
