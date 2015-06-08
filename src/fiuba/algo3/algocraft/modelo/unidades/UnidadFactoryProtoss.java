package fiuba.algo3.algocraft.modelo.unidades;

public class UnidadFactoryProtoss extends AbstractUnidadFactory {
	
	public Unidad2 crearUnidad(String nombre/*,DisparoStrategy estrategiaDisparo*/){
		return new Zealot(nombre/*,estrategiaDisparo*/);
		
	}
}
