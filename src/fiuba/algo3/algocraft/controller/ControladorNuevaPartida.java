package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevaPartida;

public class ControladorNuevaPartida extends ControllerGui {
	
	protected VistaNuevaPartida vista;

	public ControladorNuevaPartida(VentanaPrincipal ventana, VistaNuevaPartida vistaNuevaPartida) {
		super(ventana);
		this.vista = vistaNuevaPartida;
	}
	

	/****************************************************/
	
	/****** Listener para el boton acepater users *******/
	private class EscuchaBotonAceptarUsers implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public ActionListener getListenerButtonAceptarUsers() {
		return new EscuchaBotonAceptarUsers();
	}
	
	
	
	/****************************************************/
	
	/****** Listener para el boton volver ***************/
	private class EscuchaBotonVolver implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vista.reiniciarCampos();
			vent.showLayout("Bienvenido");
		}
		
	}
	
	public ActionListener getListenerButtonVolver() {
		return new EscuchaBotonVolver();
	}

}
