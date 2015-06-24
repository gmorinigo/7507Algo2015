package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevoJugador1;

public class ControladorNuevoJugagor1 extends ControllerGui{

	protected VistaNuevoJugador1 vista;

	public ControladorNuevoJugagor1(VentanaPrincipal ventana, VistaNuevoJugador1 vistaNuevoJugador1) {
		super(ventana);
		this.vista = vistaNuevoJugador1;
	}

	public ActionListener getListenerButtonAceptarUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
