package fiuba.algo3.algocraft;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaBienvenido;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevoJugador1;

public class AlgoCraft {
	
	private VentanaPrincipal ventana;

	private void initVistas() {
		VistaBienvenido bienvenidoVista = new VistaBienvenido(this.ventana);
//		VistaNuevaPartida nuevaPartidaVista = new VistaNuevaPartida(this.ventana);
		VistaNuevoJugador1 nuevoJugador1Vista = new VistaNuevoJugador1(this.ventana);
		
		
		this.ventana.agregarLayout(bienvenidoVista);
		this.ventana.agregarLayout(nuevoJugador1Vista);
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
