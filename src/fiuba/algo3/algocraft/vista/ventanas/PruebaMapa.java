package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import fiuba.algo3.algocraft.controller.ControladorVentanaMapa;
import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.MapaFactory;
import fiuba.algo3.algocraft.vista.VistaEscenario;

import java.awt.event.MouseAdapter;

public class PruebaMapa extends JFrame{

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
	VentanaPrincipal ventanaPrincipal;
	static AlgoCraft juego;
	private JLabel lblGas;
	private JLabel lblDameGas;
	private JLabel lblMineral;
	private JLabel lblDameMineral;
	private JLabel label;
	private JLabel lblPoblacion;
	private JLabel lblPoblacionDisponible;
	private JLabel lblNewLabel;
	private JLabel lblPoblacionTotal;
	private JButton cambiarTurno;
	private JPopupMenu popupMenu;
	private JMenuItem mntmExpansorPoblacion;
	private JMenuItem mntmUnidadesBasicas;
	private JMenuItem mntmExtractorGas;
	private JMenuItem mntmExtractorMineral;
	
	private void initComponents(Container panel){
		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelLeft.setLayout(new BorderLayout());
		panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelCenter = new JPanel();
		
		saludoLabel = new JLabel("Hola " +juego.dameJugador1().dameNombre());
		saludoLabel.setPreferredSize(new Dimension(100, 20));
		
		//String dif = this.jugador.getCiudad().getDificultad();
		volverButton = new JButton("Salir");
		volverButton.setPreferredSize(new Dimension(80,20));
		
		volverButton.addActionListener(controladorMapa.getListenerBotonSalir());
		
		lblGas = new JLabel("Gas:");
		panelTop.add(lblGas);
		
		lblDameGas = new JLabel();
		panelTop.add(lblDameGas);
		lblDameGas.setText(Integer.toString(juego.dameJugador1().dameCantidadGas()));
		
		lblMineral = new JLabel("Mineral:");
		panelTop.add(lblMineral);
		
		lblDameMineral = new JLabel();
		panelTop.add(lblDameMineral);
		lblDameMineral.setText(Integer.toString(juego.dameJugador1().dameCantidadMineral()));
		
		lblPoblacion = new JLabel("Poblacion:");
		panelTop.add(lblPoblacion);
		
		lblPoblacionDisponible = new JLabel();
		panelTop.add(lblPoblacionDisponible);
		lblPoblacionDisponible.setText(Integer.toString(juego.dameJugador1().obtenerCantidadPoblacionDisponible()));
		
		lblNewLabel = new JLabel("/");
		panelTop.add(lblNewLabel);
		
		lblPoblacionTotal = new JLabel();
		panelTop.add(lblPoblacionTotal);
		lblPoblacionTotal.setText(Integer.toString(juego.dameJugador1().dameLimiteDePoblacion()));
		
		panelTop.add(saludoLabel, BorderLayout.LINE_START);
		panelTop.add(volverButton, BorderLayout.LINE_END);
		
		//panelLeft.add(new VistaMovimientosTablero(jugador), BorderLayout.PAGE_START);
		//panelLeft.add(new VistaBotonesMovimiento(jugador, controlTab), BorderLayout.PAGE_END);
		
		panelCenter.add(this.vistaEscenario);
		
		panel.add(panelTop, BorderLayout.PAGE_START);
		
		cambiarTurno = new JButton("CambiarTurno");
		panelTop.add(cambiarTurno);
		//panel.add(panelLeft, BorderLayout.LINE_START);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelRight, BorderLayout.LINE_END);
	
	}
	
	public PruebaMapa(AlgoCraft unJuego) {
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
		
		popupMenu = new JPopupMenu();
		addPopup(vistaEscenario, popupMenu);
		
		mntmExtractorMineral = new JMenuItem("Crear extractorMineral");
		mntmExtractorMineral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("hola mundo");
			}
		});
		popupMenu.add(mntmExtractorMineral);
		
		mntmExtractorGas = new JMenuItem("Crear extractorGas");
		popupMenu.add(mntmExtractorGas);
		
		mntmExpansorPoblacion = new JMenuItem("Crear expansorPoblacion");
		popupMenu.add(mntmExpansorPoblacion);
		
		mntmUnidadesBasicas = new JMenuItem("Crear creadorUnidadesBasicas");
		mntmUnidadesBasicas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,"presionaste el elemento del menu");
			}
		});
		popupMenu.add(mntmUnidadesBasicas);
		
		label = new JLabel("");
		vistaEscenario.add(label, BorderLayout.NORTH);
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
