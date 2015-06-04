package fiuba.algo3.algocrafttest;

import org.junit.Test;

import fiuba.algo3.algocraft.Celda;
//import fiuba.algo3.algocraft.GasVespano;
import fiuba.algo3.algocraft.Mapa;
import fiuba.algo3.algocraft.Posicion;
//import fiuba.algo3.algocraft.Recurso;

public class RecoleccionTest{
	@Test
	public void testRecolectarGasVespanoDeUnaCelda(){
		Mapa unMapa = Mapa.getInstance();		
		Posicion laPosicionDelRecurso = new Posicion(3,3);
		Celda unaCelda = unMapa.dameCelda(laPosicionDelRecurso);
		unaCelda.cargarCeldaConGas();
		assert(unaCelda.celdaTieneGas());
	}	
	
	@Test
	public void testValidarRefineriaParaRecolectarGasVaspeno(){
		Mapa unMapa = Mapa.getInstance();		
		Posicion laPosicionDelRecurso = new Posicion(4,4);
		Celda unaCelda = unMapa.dameCelda(laPosicionDelRecurso);
		unaCelda.cargarCeldaConMineral();
		assert(unaCelda.celdaTieneMineral());
	}	
	
	
	
	
	
	
	
}
