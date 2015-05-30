package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.CampoDeBatalla;
import fiuba.algo3.algocraft.GasVespano;
import fiuba.algo3.algocraft.Mineral;
import fiuba.algo3.algocraft.Posicion;
import fiuba.algo3.algocraft.Recursos;
import junit.framework.TestCase;

public class CampoDeBatallaTest extends TestCase {

	public void testCampoDeBatallaTieneQueContenerTodasLasCeldas(){
		
		CampoDeBatalla unCampoDeBatalla = CampoDeBatalla.getInstance();
		Posicion posicion00 = new Posicion(0,0);
		Posicion posicion024 = new Posicion(0,24);
		Posicion posicion240 = new Posicion(24,0);
		Posicion posicion2424 = new Posicion(24,24);
		
		assertNotNull(unCampoDeBatalla.dameCelda(posicion00));
		assertNotNull(unCampoDeBatalla.dameCelda(posicion024));
		assertNotNull(unCampoDeBatalla.dameCelda(posicion240));
		assertNotNull(unCampoDeBatalla.dameCelda(posicion2424));
	}
	
	public void testCampoDeBatallaTieneQueCrearCeldasDiferentesParaLasPosiciones(){
		CampoDeBatalla unCampoDeBatalla = CampoDeBatalla.getInstance();
		Posicion posicion02 = new Posicion(0,2);
		Posicion posicion31 = new Posicion(3,1);
		assertNotSame(unCampoDeBatalla.dameCelda(posicion02),unCampoDeBatalla.dameCelda(posicion31));
	}
	
	public void testAgregarRecursosAlMapaDeberiaAgregarRecursosALaLista(){
		Posicion posicion55 = new Posicion(5,5);
		Posicion posicion89 = new Posicion(8,9);
		CampoDeBatalla unCampoDeBatalla = CampoDeBatalla.getInstance();
		GasVespano unGasVespano = new GasVespano(posicion55);
		Recursos unMineral    = new Mineral(posicion89);
		unCampoDeBatalla.agregarRecurso(unGasVespano);
		unCampoDeBatalla.agregarRecurso(unMineral);
		assertTrue(unCampoDeBatalla.dameRecursos().contains(unGasVespano));
		assertTrue(unCampoDeBatalla.dameRecursos().contains(unMineral));
	}
}
