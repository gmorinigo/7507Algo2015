package fiuba.algo3.algocraft;

import fiuba.algo3.algocraft.vista.VistaBienvenido;
import fiuba.algo3.algocraft.vista.VistaNuevaPartida;
import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;

public class AlgoCraft {
	
	private VentanaPrincipal ventana;

	private void initVistas() {
		VistaBienvenido bienvenidoVista = new VistaBienvenido(this.ventana);
		this.ventana.agregarLayout(bienvenidoVista);
		VistaNuevaPartida nuevaPartidaVista = new VistaNuevaPartida(this.ventana);
		this.ventana.agregarLayout(nuevaPartidaVista);
	}

	//--------------------------------------------------
	// Public ------------------------------------------
	
	/******************* MAIN  *************************/
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AlgoCraft app = new AlgoCraft();
	}

	/**************** Constructor **********************/	
	public AlgoCraft(){
		ventana = new VentanaPrincipal(500, 500);
		initVistas();
		ventana.showLayout("Bienvenido a AlgoCraft");
	}


}
