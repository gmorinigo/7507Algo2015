package fiuba.algo3.algocraft.controller;

import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;

abstract public class ControllerGui {

	protected VentanaPrincipal vent;

	public ControllerGui(VentanaPrincipal ventana) {
		this.vent = ventana;
	}
}
