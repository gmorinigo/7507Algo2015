package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import junit.framework.TestCase;

public class UnidadesTest extends TestBase{
	
	
public void testCrearUnidadGolliat(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
	
	Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("Golliat"/*,new DisparoSuperStrategy()*/);
	assertEquals(unaUnidad.getName(), "Golliat");
	//Mapa.getInstance().AddUnidad(unaUnidad);
	//unaUnidad.HacerAlgo();
}

public void testCrearUnidadEspectro(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("Espectro"/*,new DisparoSuperStrategy()*/);
	assertEquals(unaUnidad.getName(), "Espectro");
	assertNotSame(unaUnidad.getName(), "lalala");
	
	//Mapa.getInstance().AddUnidad(unaUnidad);
	//unaUnidad.HacerAlgo();
}

public void testCrearUnidadEspectroNoEsCualquierCosaSinoLaUnidadQuePasoEnConstructor(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("Espectro"/*,new DisparoSuperStrategy()*/);
	assertNotSame(unaUnidad.getName(), "lalala");
}


public static AbstractUnidadFactory getFactoryUnidades(){
    
   return new UnidadFactoryTerran();
} 

}


	
	
