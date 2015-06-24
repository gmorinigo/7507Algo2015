package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevaPartida;

public class ControladorNuevaPartida extends ControllerGui{

	protected VistaNuevaPartida vista;

	public ControladorNuevaPartida(VentanaPrincipal ventana, VistaNuevaPartida vistaNuevoJugador1) {
		super(ventana);
		this.vista = vistaNuevoJugador1;
	}

	public ActionListener getListenerButtonAceptarUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private class EscuchaBotonVolver implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vent.showLayout("Bienvenido");
		}
		
	}

	public ActionListener getListenerButtonVolver() {
		return new EscuchaBotonVolver();
	}

}
