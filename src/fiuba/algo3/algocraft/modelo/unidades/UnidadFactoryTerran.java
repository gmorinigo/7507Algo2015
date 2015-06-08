package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	public Unidad2 crearUnidad(String nombre/*,DisparoStrategy estrategiaDisparo*/){
		
		return new Golliat(nombre/*,estrategiaDisparo*/);
		}
		
	
}
