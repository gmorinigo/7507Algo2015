package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;

public class ControladorBienvenido extends ControllerGui{

	public ControladorBienvenido(VentanaPrincipal ventana) {
		super(ventana);
	}
	
	/****************************************************/
	
	/****** Listener para el boton nueva partida ********/
	
	private class EscuchaBotonNuevaPartida implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vent.showLayout("NuevaPartida");
		}
		
	}

	public ActionListener getListenerButtonNuevaPartida() {
		// TODO Auto-generated method stub
		return new EscuchaBotonNuevaPartida();
	}
	
	

}
