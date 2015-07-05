package fiuba.algo3.algocraft.controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class ControladorVentanaMapa {
	@SuppressWarnings("unused")
	private VentanaMapa ventMapa;
	@SuppressWarnings("unused")
	private Jugador jugador;
	private AlgoCraft juego;

	public ControladorVentanaMapa(Jugador jugador, VentanaMapa ventMapa, AlgoCraft juego) {
		this.ventMapa = ventMapa;
		this.jugador = jugador;
		this.juego = juego;
	}

	private class EscuchaBotonPasarTurno implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			juego.avanzarTurno();
		}
	}

	public ActionListener getListenerBotonPasarTurno() {
		return new EscuchaBotonPasarTurno();
	}
	
	
	private class EscuchaBotonSalir implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}

	public ActionListener getListenerBotonSalir() {
		return new EscuchaBotonSalir();
	}
}
