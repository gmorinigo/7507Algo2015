package fiuba.algo3.algocrafttest;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryTerran;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;

@SuppressWarnings("static-access")
public class UnidadesTest extends TestBase{
	
	public void testCrearUnidadMarine() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Marine);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public void testCrearUnidadGolliat() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre2,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Golliat);
		//Mapa.getInstance().AddUnidad(unaUnidad);
	//	unaUnidad.HacerAlgo();
	}

	public void testCrearUnidadEspectro() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador1,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof Espectro);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}

	public void testCrearUnidadNaveCiencia() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.especial1,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveCiencia);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public void testCrearUnidadNaveTransporte() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos{	
		AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");

		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo.volador2,unJugador/*,new DisparoSuperStrategy()*/);
		assertTrue(unaUnidad instanceof NaveTransporte);
		//Mapa.getInstance().AddUnidad(unaUnidad);
		//unaUnidad.HacerAlgo();
	}
	
	public static AbstractUnidadFactory getFactoryUnidades(){
    
		return new UnidadFactoryTerran();
	} 

}


	
	
