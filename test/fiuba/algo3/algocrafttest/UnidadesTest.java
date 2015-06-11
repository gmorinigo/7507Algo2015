package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;

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

public void testCrearNaveCiencia(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("NaveCiencia"/*,new DisparoSuperStrategy()*/);
	assertEquals(unaUnidad.getName(), "NaveCiencia");
}


public void testCrearNaveCienciaCreadaCon50DeEnergia(){	
	AbstractUnidadFactory factoryUnidades = getFactoryUnidades();	
	Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad("NaveCiencia"/*,new DisparoSuperStrategy()*/);
	NaveCiencia unaNaveCiencia = (NaveCiencia) unaUnidad;	
	assertEquals(unaNaveCiencia.getEnergia(), 50);
}


public static AbstractUnidadFactory getFactoryUnidades(){
    
   return new UnidadFactoryTerran();
} 

}


	
	
