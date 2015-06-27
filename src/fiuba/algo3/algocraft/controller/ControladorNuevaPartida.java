package fiuba.algo3.algocraft.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoColorException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConElMismoNombreException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.vista.ventanas.PruebaMapa;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;
import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;
import fiuba.algo3.algocraft.vista.ventanas.VistaNuevaPartida;

public class ControladorNuevaPartida extends ControllerGui {

	protected VistaNuevaPartida vista;

	public ControladorNuevaPartida(VentanaPrincipal ventana, VistaNuevaPartida vistaNuevoJugador1) {
		super(ventana);
		this.vista = vistaNuevoJugador1;
	}
	
	private class EscuchaBotonAceptar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			AlgoCraft juego = new AlgoCraft();
			Raza razaJug1;
			Raza razaJug2;
			switch (vista.dameRazaSeleccionadaJug1()) {
			case "":
				JOptionPane.showMessageDialog(null, "Seleccione una raza para el jugador 1");
				return;
			case "Terran":
				razaJug1 = new RazaTerran();
				break;
			case "Protoss":
				razaJug1 = new RazaProtoss();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Seleccione una raza para el jugador 1");
				return;
			}
			try {
				juego.agregarJugadorNumero1(vista.dameNombreJug1(), razaJug1, "Azul");
			} catch (JugadorConNombreDemasiadoCortoException ex) {
				JOptionPane.showMessageDialog(null, "Nombre del jugador 1 demasiado corto");
				return;
			}
			
			switch (vista.dameRazaSeleccionadaJug2()) {
			case "":
				JOptionPane.showMessageDialog(null, "Seleccione una raza para el jugador 2");
				return;
			case "Terran":
				razaJug2 = new RazaTerran();
				break;
			case "Protoss":
				razaJug2 = new RazaProtoss();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Seleccione una raza para el jugador 2");
				return;
			}
			
			try {
				juego.agregarJugadorNumero2(vista.dameNombreJug2(), razaJug2, "Rojo");
			} catch (JugadorConElMismoNombreException e1) {
				JOptionPane.showMessageDialog(null, "Jugador 2 tiene el mismo nombre que Jugador 1");
			} catch (JugadorConElMismoColorException e1) {
				JOptionPane.showMessageDialog(null, "Jugador 2 tiene el mismo color que Jugador 1");
			} catch (JugadorConNombreDemasiadoCortoException e1) {
				JOptionPane.showMessageDialog(null, "Nombre del jugador 2 demasiado corto");
			}
			VentanaMapa unMapa = new VentanaMapa(juego);
			unMapa.setVisible(true);
			vista.setVisible(false);
		}
		
	}

	public ActionListener getListenerButtonAceptarUsers() {
		return new EscuchaBotonAceptar();
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