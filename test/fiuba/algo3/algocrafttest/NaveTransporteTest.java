package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporte;

public class NaveTransporteTest extends TestBase {	
	
	public void testCrearUnidadNaveTransporte() throws NoSuchObjectException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveTransporte);
	}

	
	public void testAgregarUnidadALaNaveDeTransporte() throws MaximaCapacidadDeTransporteSuperadaException, NoSuchObjectException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		assertTrue(unaNaveTrasporte instanceof NaveTransporte);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1/*,new DisparoSuperStrategy()*/);
		assertTrue(unMarine instanceof Marine);
		
		unaNaveTrasporte.cargarUnidad(unMarine);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 1);
	}

	
	public void testAgregar3UnidadesALaNaveDeTransporte() throws MaximaCapacidadDeTransporteSuperadaException, NoSuchObjectException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1/*,new DisparoSuperStrategy()*/);

		Unidad unGoliat = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		assertTrue(unGoliat instanceof Golliat);
				
		Unidad otroGoliat = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		
		unaNaveTrasporte.cargarUnidad(unMarine);
		unaNaveTrasporte.cargarUnidad(unGoliat);
		unaNaveTrasporte.cargarUnidad(otroGoliat);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 5);
	}

	public void testLlenarNaveTransporteYAgregarOtraUnidadSeEsperaExcepcion() throws NoSuchObjectException, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		
		Unidad unMarine = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1/*,new DisparoSuperStrategy()*/);

		Unidad golliat1 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		Unidad golliat2 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		Unidad golliat3 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		Unidad golliat4 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2/*,new DisparoSuperStrategy()*/);
		

		unaNaveTrasporte.cargarUnidad(golliat1);
		unaNaveTrasporte.cargarUnidad(golliat2);
		unaNaveTrasporte.cargarUnidad(golliat3);
		unaNaveTrasporte.cargarUnidad(golliat4);
		
		assertEquals(unaNaveTrasporte.getCapacidadOcupada(), 8);

		try{
			unaNaveTrasporte.cargarUnidad(unMarine);
		}
		catch (MaximaCapacidadDeTransporteSuperadaException e){
			return;
		}
		
		fail();
	}
	
	
	public void testAgregarUnaUnidadVoladoraSeEsperaExcepcion() throws NoSuchObjectException, MaximaCapacidadDeTransporteSuperadaException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		NaveTransporte unaNaveTrasporte = (NaveTransporte) factoryUnidades.crearUnidad(unTipo.volador2/*,new DisparoSuperStrategy()*/);
		
		Unidad unEspectro = (Unidad) factoryUnidades.crearUnidad(unTipo.volador1/*,new DisparoSuperStrategy()*/);

		try{
			unaNaveTrasporte.cargarUnidad(unEspectro);
		}
		catch (NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e){
			return;
		}
		
		fail();
	}
	
	public static AbstractUnidadFactory getFactoryUnidades(){
	    
		   return new UnidadFactoryTerran();
	} 
}