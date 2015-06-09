package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad2;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import junit.framework.TestCase;

public class UnidadesTest extends TestCase{
	
	
public void testCrearUnidadGolliat(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
	
	Unidad2 unaUnidad = (Unidad2) factoryUnidades.crearUnidad("Golliat"/*,new DisparoSuperStrategy()*/);
	assertEquals(unaUnidad.getName(), "Golliat");
	//Mapa.getInstance().AddUnidad(unaUnidad);
	//unaUnidad.HacerAlgo();
}

public void testCrearUnidadEspectro(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad2 unaUnidad = (Unidad2) factoryUnidades.crearUnidad("Espectro"/*,new DisparoSuperStrategy()*/);
	assertEquals(unaUnidad.getName(), "Espectro");
	assertNotSame(unaUnidad.getName(), "lalala");
	
	//Mapa.getInstance().AddUnidad(unaUnidad);
	//unaUnidad.HacerAlgo();
}

public void testCrearUnidadEspectroNoEsCualquierCosaSinoLaUnidadQuePasoEnConstructor(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad2 unaUnidad = (Unidad2) factoryUnidades.crearUnidad("Espectro"/*,new DisparoSuperStrategy()*/);
	assertNotSame(unaUnidad.getName(), "lalala");

}


public static AbstractUnidadFactory getFactoryUnidades(){
   return new UnidadFactoryTerran();
} 




}


	
	
