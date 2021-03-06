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
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.resources.sounds.Sonido;
import fiuba.algo3.algocraft.vista.VistaEscenario;

@SuppressWarnings("static-access")
public class VentanaMapa extends JFrame implements Observer/*, Runnable*/{

	private static final long serialVersionUID = 1L;
	JFrame frame;
	private Jugador jugador;
	private ControladorVentanaMapa controladorMapa;
	private VistaEscenario vistaEscenario;
	//private VentanaPrincipal ventPpal;
	JPanel panelTop;
	JPanel panelLeft;
	JPanel panelRight;
	JPanel panelCenter;
	private JLabel lblTurnoJugador;
	private JLabel lblJugadorTurnoActual;
	//JLabel cabeceraPanelDerecho;
	JButton salirButton;
	JButton guardarButton;
	VentanaPrincipal ventanaPrincipal;
	static AlgoCraft juego;
	private JLabel lblGas;
	private JLabel lblDameGas;
	private JLabel lblMineral;
	private JLabel lblDameMineral;
	private JLabel lblPoblacion;
	private JLabel lblPoblacionOcupada;
	private JLabel lblNewLabel;
	private JLabel lblPoblacionTotal;
	private JButton cambiarTurnoButton;
	private JLabel lblNroTurno;
//	private Thread unThreadSonido;
	
	private void initComponents(Container panel){
//		this.generarSonido();
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelCenter = new JPanel();
		
		lblTurnoJugador = new JLabel("Jugador:");
		//lblTurnoJugador.setPreferredSize(new Dimension(80, 20));
		panelTop.add(lblTurnoJugador, BorderLayout.LINE_START);
		
		lblJugadorTurnoActual = new JLabel();
		panelTop.add(lblJugadorTurnoActual);

		//cabeceraPanelDerecho = new JLabel("Botonera");
		//cabeceraPanelDerecho.setPreferredSize(new Dimension(100, 40));
		
		salirButton = new JButton("Salir");
		salirButton.setPreferredSize(new Dimension(80,20));
		
		salirButton.addActionListener(controladorMapa.getListenerBotonSalir());
		
		lblGas = new JLabel("  Gas:");
		panelTop.add(lblGas);
		
		lblDameGas = new JLabel();
		panelTop.add(lblDameGas);
		
		lblMineral = new JLabel("  Mineral:");
		panelTop.add(lblMineral);
		
		lblDameMineral = new JLabel();
		panelTop.add(lblDameMineral);
	
		lblPoblacion = new JLabel("  Poblacion:");
		panelTop.add(lblPoblacion);
		
		lblPoblacionOcupada = new JLabel();
		panelTop.add(lblPoblacionOcupada);
		
		lblNewLabel = new JLabel("/");
		panelTop.add(lblNewLabel);
		
		lblPoblacionTotal = new JLabel();
		panelTop.add(lblPoblacionTotal);
		
		JLabel lvlNroTurnoActual = new JLabel("  Nro Turno Actual:");
		panelTop.add(lvlNroTurnoActual);
		
		lblNroTurno = new JLabel();
		panelTop.add(lblNroTurno);
		
		this.actualizarRecursos();
		
		//panelTop.add(salirButton, BorderLayout.LINE_END);
		
		panelCenter.add(this.vistaEscenario);
		
		panel.add(panelTop, BorderLayout.PAGE_START);
		
		cambiarTurnoButton = new JButton("CambiarTurno");
		//panelTop.add(cambiarTurnoButton);
		panelRight.add(cambiarTurnoButton);
		panelRight.add(salirButton);

		cambiarTurnoButton.addActionListener(controladorMapa.getListenerBotonPasarTurno());
		salirButton.addActionListener(controladorMapa.getListenerBotonSalir());
		
		//panelRight.add(cabeceraPanelDerecho);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelRight, BorderLayout.LINE_END);
        panel.addMouseListener(new MapaMouseListener(this,this.juego));
	}
/*
	private void generarSonido() {
		unThreadSonido = new Thread(this);
		unThreadSonido.start();
	}
*/
	@SuppressWarnings("unused")
	public VentanaMapa(AlgoCraft unJuego) {
		Sonido unSonido = new Sonido();
		unSonido.init();
		unSonido.start();	
		MapaFactory unMapaFactory = new MapaFactory();
		Mapa unMapa = unMapaFactory.crearMapa20x20Hardcodeado();
		frame = new JFrame("AlgoCraft");
		frame.setSize(800, 800);
		juego = unJuego;
		this.jugador = juego.dameElJugadorDelTurno();
		this.controladorMapa = new ControladorVentanaMapa(jugador, this, unJuego);
		this.vistaEscenario = new VistaEscenario(this.jugador, this.juego);
		this.initComponents(frame.getContentPane());
		unJuego.addObserver(this);
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
	
	public void update(Observable arg0, Object arg1) {
		this.actualizarRecursos();		
	}
	
	
	private void actualizarRecursos() {
		lblJugadorTurnoActual.setText(juego.dameElJugadorDelTurno().dameNombre());
		lblDameGas.setText(Integer.toString(juego.dameElJugadorDelTurno().dameCantidadGas()));		
		lblDameMineral.setText(Integer.toString(juego.dameElJugadorDelTurno().dameCantidadMineral()));
		lblPoblacionOcupada.setText(Integer.toString(juego.dameElJugadorDelTurno().obtenerCantidadPoblacionOcupada()));
		lblPoblacionTotal.setText(Integer.toString(juego.dameElJugadorDelTurno().dameLimiteDePoblacion()));		
		
		Turno unTurno = Turno.getInstance();
		lblNroTurno.setText(Integer.toString(unTurno.obtenerNumeroTurno()));
	}
/*
	public void run() {
		Sonido unSonido = new Sonido();
		unSonido.init();
		unSonido.start();		
	}
*/
}
