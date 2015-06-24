package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3.algocraft.controller.ControladorVentanaMapa;
import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.MapaFactory;
import fiuba.algo3.algocraft.vista.VistaEscenario;

public class VentanaMapa extends JFrame{

	JFrame frame;
	private Jugador jugador;
	private ControladorVentanaMapa controladorMapa;
	private VistaEscenario vistaEscenario;
	private VentanaPrincipal ventPpal;
	JPanel panelTop;
	JPanel panelLeft;
	JPanel panelRight;
	JPanel panelCenter;
	JLabel saludoLabel;
	JButton volverButton;
	JButton guardarButton;
	VentanaPrincipal ventanaPrincipal;
	static AlgoCraft juego;
	
	private void initComponents(Container panel){
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelCenter = new JPanel();
		
		saludoLabel = new JLabel("Hola " +juego.dameJugador1());
		saludoLabel.setPreferredSize(new Dimension(100, 20));
		
		//String dif = this.jugador.getCiudad().getDificultad();
		volverButton = new JButton("Salir");
		volverButton.setPreferredSize(new Dimension(80,20));
		guardarButton = new JButton("Guardar");
		guardarButton.setPreferredSize(new Dimension(80, 20));
		
		volverButton.addActionListener(controladorMapa.getListenerBotonSalir());
		guardarButton.addActionListener(controladorMapa.getListenerBotonGuardar());

		
		panelTop.add(saludoLabel, BorderLayout.LINE_START);
		panelTop.add(volverButton, BorderLayout.LINE_END);
		
		//panelLeft.add(new VistaMovimientosTablero(jugador), BorderLayout.PAGE_START);
		//panelLeft.add(new VistaBotonesMovimiento(jugador, controlTab), BorderLayout.PAGE_END);
		
		panelCenter.add(this.vistaEscenario);
		
		panelRight.add(guardarButton, Component.RIGHT_ALIGNMENT);
		
		panel.add(panelTop, BorderLayout.PAGE_START);
		//panel.add(panelLeft, BorderLayout.LINE_START);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelRight, BorderLayout.LINE_END);
	}
	
	public VentanaMapa(AlgoCraft unJuego) {
		MapaFactory unMapaFactory = new MapaFactory();
		Mapa unMapa = unMapaFactory.crearMapa20x20Hardcodeado();
		frame = new JFrame("AlgoCraft");
		frame.setSize(800, 800);
		juego = unJuego;
		//frame.addKeyListener(controlTab.getKeyListenerMovimientos());
		this.jugador = juego.dameJugador1();
		this.controladorMapa = new ControladorVentanaMapa(jugador, this);
		this.ventPpal = ventanaPrincipal;
		this.initVistaMapa();
		this.initComponents(frame.getContentPane());
		
		//frame.setResizable(false);
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
		frame.pack();
		frame.setLocationRelativeTo(null);
		this.mostrar();
	}

	private void initVistaMapa(){
		this.vistaEscenario = new VistaEscenario(this.jugador);
	}
	
	public void ocultar() {
		frame.setVisible(false);
	}
	public void mostrar() {
		this.vistaEscenario.repaint();
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
	}
	
	public VistaEscenario getVistaMapa() {
		return this.vistaEscenario;
	}
	
	public VentanaPrincipal getVentanaGUI() {
		return this.ventPpal;
	}

}
