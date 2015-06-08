package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad2;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import junit.framework.TestCase;

public class UnidadesTest extends TestCase{
	
	
public void testCrearUnidad(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
	
	Unidad2 unaUnidad = (Unidad2) factoryUnidades.crearUnidad("Marine"/*,new DisparoSuperStrategy()*/);
	
	//Mapa.getInstance().AddUnidad(unaUnidad);
	//unaUnidad.HacerAlgo();
}

public static AbstractUnidadFactory getFactoryUnidades(){
   return new UnidadFactoryTerran();
} 




}


	
	
