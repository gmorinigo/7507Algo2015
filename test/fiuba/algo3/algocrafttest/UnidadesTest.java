package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporte;

public class UnidadesTest extends TestBase{
	
	public void testCrearUnidadMarine() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Marine);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public void testCrearUnidadGolliat() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Golliat);
		//Mapa.getInstance().AddUnidad(unaUnidad);
	//	unaUnidad.HacerAlgo();
	}

	public void testCrearUnidadEspectro() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador1/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Espectro);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}

	public void testCrearUnidadNaveCiencia() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.especial1/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveCiencia);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public void testCrearUnidadNaveTransporte() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveTransporte);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public static AbstractUnidadFactory getFactoryUnidades(){
    
		return new UnidadFactoryTerran();
	} 

}


	
	
