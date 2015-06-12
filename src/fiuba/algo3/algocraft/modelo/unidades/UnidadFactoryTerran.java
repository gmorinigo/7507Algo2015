package fiuba.algo3.algocraft.modelo.unidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.terran.Marine;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveTransporte;

public class UnidadFactoryTerran extends AbstractUnidadFactory {
	
	
	
	public Unidad crearUnidad(TipoUnidad tipo){
		switch(tipo){
		case especial1:	return new NaveCiencia();
		case especial2: throw new NoSuchObjectException("Objeto Especial 2");
		case terrestre1: return new Marine();
			break;
		case terrestre2:
			break;
		case volador1:
			break;
		case volador2:
			break;
		default:
			break;
		
}
		
		if (nombre == "Golliat"){ return new Golliat(nombre/*,estrategiaDisparo*/);}
		else if (nombre == "Marine"){return new Marine(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "Espectro"){return new Espectro(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "NaveCiencia"){return new NaveCiencia(nombre/*,estrategiaDisparo*/);}
		
		else if (nombre == "NaveTransporte"){return new NaveTransporte(nombre/*,estrategiaDisparo*/);}
		
		//else {return new Golliat(nombre/*,estrategiaDisparo*/);}
		
		else {return null;}  //---> deberia retornar Exception  
	}
		
	
}
