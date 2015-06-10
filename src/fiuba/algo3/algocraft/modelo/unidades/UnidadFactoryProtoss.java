package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad crearUnidad(String nombre/*,DisparoStrategy estrategiaDisparo*/){

		if (nombre == "Zealot"){ return new Zealot(nombre/*,estrategiaDisparo*/);}
				
		else if (nombre == "Dragon"){return new Dragon(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "Scout"){return new Scout(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "AltoTemplario"){return new AltoTemplario(nombre/*,estrategiaDisparo*/);}
		
		else {return null;}  //---> deberia retornar Exception  
		
		
	}
}
