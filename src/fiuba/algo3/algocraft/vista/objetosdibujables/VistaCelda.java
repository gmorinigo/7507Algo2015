package fiuba.algo3.algocraft.vista.objetosdibujables;

import java.util.Observable;
import java.util.Observer;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.vista.Dibujable;
import fiuba.algo3.algocraft.vista.VistaEscenario;
import fiuba.algo3.algocraft.vista.VistaObjetoDibujable;


public class VistaCelda extends VistaObjetoDibujable implements Observer{
	
	
	public VistaCelda(Celda unaCelda, VistaEscenario vistaEscenario) {
		super((Dibujable)unaCelda, vistaEscenario);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}