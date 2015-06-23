package fiuba.algo3.algocraft;

import fiuba.algo3.algocraft.controller.ControladorBienvenido;
import fiuba.algo3.algocraft.controller.ControladorNuevaPartida;
import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaBienvenido;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevaPartida;

public class AlgoCraft {
	
	private VentanaPrincipal ventana;

	private void initVistas() {
		ControladorBienvenido controlBienvenido = new ControladorBienvenido(this.ventana);
		ControladorNuevaPartida controlNuevaPartida = new ControladorNuevaPartida(this.ventana);
		
		VistaBienvenido bienvenidoVista = new VistaBienvenido(controlBienvenido);
		VistaNuevaPartida nuevaPartidaVista = new VistaNuevaPartida(controlNuevaPartida);
		
		this.ventana.agregarLayout(bienvenidoVista);
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
		ventana.showLayout("Bienvenido");
	}


}
