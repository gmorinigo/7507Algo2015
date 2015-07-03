package fiuba.algo3.algocraft.controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class ControladorVentanaMapa {
	private VentanaMapa ventMapa;
	@SuppressWarnings("unused")
	private Jugador jugador;
	private AlgoCraft juego;

	public ControladorVentanaMapa(Jugador jugador, VentanaMapa ventMapa, AlgoCraft juego) {
		this.ventMapa = ventMapa;
		this.jugador = jugador;
		this.juego = juego;
	}

	private class EscuchaBotonArriba implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	public ActionListener getListenerButtonArriba() {
		// TODO Auto-generated method stub
		return new EscuchaBotonArriba();
	}

	private class EscuchaBotonAbajo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
	public ActionListener getListenerButtonAbajo() {
		// TODO Auto-generated method stub
		return new EscuchaBotonAbajo();
	}

	private class EscuchaBotonDer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	public ActionListener getListenerButtonDer() {
		// TODO Auto-generated method stub
		return new EscuchaBotonDer();
	}

	private class EscuchaBotonIzq implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	public ActionListener getListenerButtonIzq() {
		// TODO Auto-generated method stub
		return new EscuchaBotonIzq();
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
