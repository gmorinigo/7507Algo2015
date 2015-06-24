package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaBienvenido;

public class ControladorBienvenido extends ControllerGui{

	protected VistaBienvenido vista;
	
	public ControladorBienvenido(VentanaPrincipal ventana, VistaBienvenido vistaBienvenido) {
		super(ventana);
		this.vista = vistaBienvenido;
	}
	
	/****************************************************/
	
	/****** Listener para el boton nueva partida ********/
	
	private class EscuchaBotonNuevaPartida implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vent.showLayout("NuevoJugador1");
		}
		
	}

	public ActionListener getListenerButtonNuevaPartida() {
		// TODO Auto-generated method stub
		return new EscuchaBotonNuevaPartida();
	}
	
	

}
