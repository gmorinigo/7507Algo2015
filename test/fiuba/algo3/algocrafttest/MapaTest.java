package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.construciones.Barraca;
import fiuba.algo3.algocraft.modelo.excepciones.CeldaOcupadaException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.recursos.GasVespano;
import fiuba.algo3.algocraft.modelo.recursos.Mineral;
import fiuba.algo3.algocraft.modelo.recursos.Recurso;
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
		Recurso unMineral    = new Mineral(posicion89);
		unMapa.agregarRecurso(unGasVespano);
		unMapa.agregarRecurso(unMineral);
		assertTrue(unMapa.dameRecursos().contains(unGasVespano));
		assertTrue(unMapa.dameRecursos().contains(unMineral));
	}
	
	public void testAgregarUnaConstruccionAlMapa(){
		Posicion posicion55 = new Posicion(5,5);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion55, new Jugador(new Raza()));
		
		try {
			System.out.print("Va a agrega la barraca\n");
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
			System.out.print("Excepcion!!\n");
		}

		assertTrue(unMapa.dameConstrucciones().contains(unaBarraca));
	}

	public void testAgregarUnaConstruccionAlMapaYVerificarLasCeldasOcupadas(){
		Posicion posicion88 = new Posicion(8,8);
		Posicion posicion89 = new Posicion(8,9);
		Posicion posicion98 = new Posicion(9,8);
		Posicion posicion99 = new Posicion(9,9);
		Mapa unMapa = Mapa.getInstance();
		Barraca unaBarraca = new Barraca(posicion88, new Jugador(new Raza()));
		
		try {
			unMapa.agregarConstruccion(unaBarraca);
		} catch (CeldaOcupadaException e) {
		}
		
		assertTrue(unMapa.verificarCeldaOcupada(posicion88));
		assertTrue(unMapa.verificarCeldaOcupada(posicion89));
		assertTrue(unMapa.verificarCeldaOcupada(posicion98));
		assertTrue(unMapa.verificarCeldaOcupada(posicion99));
	}

}

