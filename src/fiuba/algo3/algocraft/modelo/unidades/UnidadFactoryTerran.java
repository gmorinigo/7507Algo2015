package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(String nombre/*,DisparoStrategy estrategiaDisparo*/){
		if (nombre == "Golliat"){ return new Golliat(nombre/*,estrategiaDisparo*/);}
		else if (nombre == "Marine"){return new Marine(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "Espectro"){return new Espectro(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "NaveCiencia"){return new NaveCiencia(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "NaveTransporte"){return new NaveTransporte(nombre/*,estrategiaDisparo*/);}
		
		//else {return new Golliat(nombre/*,estrategiaDisparo*/);}
		
		else {return null;}  //---> deberia retornar Exception  
	}
		
	
}
