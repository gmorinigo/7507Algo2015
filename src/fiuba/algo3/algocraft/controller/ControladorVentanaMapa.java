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
			/*try
			{
				jugador.vehiculoBaja();
				ventMapa.mostrar();
			}catch(AvanzaAPosicionInvalidaException e1){
				JOptionPane.showMessageDialog(null,"A donde vas? Te estas yendo de la ciudad.");
			}catch(MovimientosMaximosException e2){
				JOptionPane.showMessageDialog(null,"Perdiste!");
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("UserAdmin");
			}catch(JuegoTerminadoException e3){
				JOptionPane.showMessageDialog(null,"Ganaste!");
				
				int puntaje = (jugador.getCiudad().getMovimientosMaximos()-jugador.getMovimientosRealizados());
				String dificultad = jugador.getCiudad().getDificultad();
				ArchivadorRanking.getInstance().agregarJugadorARanking(jugador.getNombre(), puntaje, dificultad);
				
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("Puntajes");
			}*/
			
		}
		
	}
	
	public ActionListener getListenerButtonArriba() {
		// TODO Auto-generated method stub
		return new EscuchaBotonArriba();
	}

	private class EscuchaBotonAbajo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/*try{
				jugador.vehiculoSube();
				ventMapa.mostrar();
			}catch(AvanzaAPosicionInvalidaException e1){
				JOptionPane.showMessageDialog(null,"A donde vas? Te estas yendo de la ciudad.");
			}catch(MovimientosMaximosException e2){
				JOptionPane.showMessageDialog(null,"Perdiste!");
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("UserAdmin");
			}catch(JuegoTerminadoException e3){
				JOptionPane.showMessageDialog(null,"Ganaste!");
				
					
				int puntaje = (jugador.getCiudad().getMovimientosMaximos()-jugador.getMovimientosRealizados());
				String dificultad = jugador.getCiudad().getDificultad();
				ArchivadorRanking.getInstance().agregarJugadorARanking(jugador.getNombre(), puntaje, dificultad);
				
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("Puntajes");
			}*/
		}
		
	}
	public ActionListener getListenerButtonAbajo() {
		// TODO Auto-generated method stub
		return new EscuchaBotonAbajo();
	}

	private class EscuchaBotonDer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/*try{
				jugador.vehiculoAvanza();
				ventMapa.mostrar();
			}catch(AvanzaAPosicionInvalidaException e1){
				JOptionPane.showMessageDialog(null,"A donde vas? Te estas yendo de la ciudad.");
			}catch(MovimientosMaximosException e2){
				JOptionPane.showMessageDialog(null,"Perdiste!");
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("UserAdmin");
			}catch(JuegoTerminadoException e3){
				JOptionPane.showMessageDialog(null,"Ganaste!");
				int puntaje = (jugador.getCiudad().getMovimientosMaximos()-jugador.getMovimientosRealizados());
				String dificultad = jugador.getCiudad().getDificultad();
				ArchivadorRanking.getInstance().agregarJugadorARanking(jugador.getNombre(), puntaje, dificultad);
				
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("Puntajes");
			}*/
		}
		
	}
	public ActionListener getListenerButtonDer() {
		// TODO Auto-generated method stub
		return new EscuchaBotonDer();
	}

	private class EscuchaBotonIzq implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/*try
			{
				jugador.vehiculoRetrocede();
				ventMapa.mostrar();
			}catch(AvanzaAPosicionInvalidaException e1){
				JOptionPane.showMessageDialog(null,"A donde vas? Te estas yendo de la ciudad.");
			}catch(MovimientosMaximosException e2){
				JOptionPane.showMessageDialog(null,"Perdiste!");
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("UserAdmin");
			}catch(JuegoTerminadoException e3){
				JOptionPane.showMessageDialog(null,"Ganaste!");
				
				int puntaje = (jugador.getCiudad().getMovimientosMaximos()-jugador.getMovimientosRealizados());
				String dificultad = jugador.getCiudad().getDificultad();
				ArchivadorRanking.getInstance().agregarJugadorARanking(jugador.getNombre(), puntaje, dificultad);
				
				ventMapa.ocultar();
				ventMapa.getVentanaGUI().showLayout("Puntajes");
			}*/
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
