package fiuba.algo3.algocraft;

import fiuba.algo3.algocraft.resources.sounds.Sonido;
import fiuba.algo3.algocraft.vista.VistaNuevaPartida;
import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;

public class AlgoCraft {
	
	private VentanaPrincipal ventana;
	private void initVistas() {
		VistaNuevaPartida nuevaPartidaVista = new VistaNuevaPartida(this.ventana);
		this.ventana.agregarLayout(nuevaPartidaVista);
	}

	//--------------------------------------------------
	// Public ------------------------------------------
	
	/******************* MAIN  *************************/
	public static void main(String[] args) {
		Sonido unSonido = new Sonido();
		unSonido.init();
		unSonido.start();
		@SuppressWarnings("unused")
		AlgoCraft app = new AlgoCraft();
	}

	/**************** Constructor **********************/	
	public AlgoCraft(){
		ventana = new VentanaPrincipal(500, 500);
		initVistas();
		ventana.showLayout("Bienvenido");
	}


}
