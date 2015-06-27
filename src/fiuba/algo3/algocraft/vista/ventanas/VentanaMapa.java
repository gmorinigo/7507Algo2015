package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fiuba.algo3.algocraft.controller.ControladorVentanaMapa;
import fiuba.algo3.algocraft.controller.MapaMouseListener;
import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.MapaFactory;
import fiuba.algo3.algocraft.vista.VistaEscenario;

@SuppressWarnings("static-access")
public class VentanaMapa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
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
	JLabel cabeceraPanelDerecho;
	JButton volverButton;
	JButton guardarButton;
	VentanaPrincipal ventanaPrincipal;
	static AlgoCraft juego;
	private JLabel lblGas;
	private JLabel lblDameGas;
	private JLabel lblMineral;
	private JLabel lblDameMineral;
	private JLabel lblPoblacion;
	private JLabel lblPoblacionDisponible;
	private JLabel lblNewLabel;
	private JLabel lblPoblacionTotal;
	private JButton cambiarTurno;
	
	private void initComponents(Container panel){
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelCenter = new JPanel();
		
		saludoLabel = new JLabel("Turno Jugador: " + juego.dameElJugadorDelTurno().dameNombre());
		saludoLabel.setPreferredSize(new Dimension(160, 20));
		panelTop.add(saludoLabel, BorderLayout.LINE_START);
		
		cabeceraPanelDerecho = new JLabel("Botonera");
		cabeceraPanelDerecho.setPreferredSize(new Dimension(100, 40));
		
		volverButton = new JButton("Salir");
		volverButton.setPreferredSize(new Dimension(80,20));
		
		volverButton.addActionListener(controladorMapa.getListenerBotonSalir());
		
		lblGas = new JLabel("Gas:");
		panelTop.add(lblGas);
		
		lblDameGas = new JLabel();
		panelTop.add(lblDameGas);
		
		lblMineral = new JLabel("Mineral:");
		panelTop.add(lblMineral);
		
		lblDameMineral = new JLabel();
		panelTop.add(lblDameMineral);
	
		lblPoblacion = new JLabel("Poblacion:");
		panelTop.add(lblPoblacion);
		
		lblPoblacionDisponible = new JLabel();
		panelTop.add(lblPoblacionDisponible);
		
		lblNewLabel = new JLabel("/");
		panelTop.add(lblNewLabel);
		
		lblPoblacionTotal = new JLabel();
		panelTop.add(lblPoblacionTotal);
		
		this.actualizarRecursos();
		
		panelTop.add(volverButton, BorderLayout.LINE_END);
		
		panelCenter.add(this.vistaEscenario);
		
		panel.add(panelTop, BorderLayout.PAGE_START);
		
		cambiarTurno = new JButton("CambiarTurno");
		panelTop.add(cambiarTurno);
		panelRight.add(cabeceraPanelDerecho);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelRight, BorderLayout.LINE_END);
        panel.addMouseListener(new MapaMouseListener(this,this.juego));
	}
	
	@SuppressWarnings("unused")
	public VentanaMapa(AlgoCraft unJuego) {
		MapaFactory unMapaFactory = new MapaFactory();
		Mapa unMapa = unMapaFactory.crearMapa20x20Hardcodeado();
		frame = new JFrame("AlgoCraft");
		frame.setSize(800, 800);
		juego = unJuego;
		//frame.addKeyListener(controlTab.getKeyListenerMovimientos());
		this.jugador = juego.dameElJugadorDelTurno();
		this.controladorMapa = new ControladorVentanaMapa(jugador, this);
		this.ventPpal = ventanaPrincipal;
		this.vistaEscenario = new VistaEscenario(this.jugador, this.juego);
		this.initComponents(frame.getContentPane());
		unJuego.addObserver(this);
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


	public void update(Observable arg0, Object arg1) {
		this.actualizarRecursos();		
	}
	
	
	private void actualizarRecursos() {
		lblDameGas.setText(Integer.toString(juego.dameElJugadorDelTurno().dameCantidadGas()));		
		lblDameMineral.setText(Integer.toString(juego.dameElJugadorDelTurno().dameCantidadMineral()));
		lblPoblacionTotal.setText(Integer.toString(juego.dameElJugadorDelTurno().dameLimiteDePoblacion()));
		lblPoblacionDisponible.setText(Integer.toString(juego.dameElJugadorDelTurno().obtenerCantidadPoblacionDisponible()));
	}

}
