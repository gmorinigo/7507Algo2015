package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import fiuba.algo3.algocraft.modelo.Raza;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
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
		panel.addMouseListener(new MouseListener(){
			
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			JPopupMenu popupMenu = new JPopupMenu("Menu contextual");
			JMenuItem mitemMineral = popupMenu.add(String.format("Crear extractorMineral en %s %s",arg0.getX(),arg0.getY()));
			JMenuItem mitemGas = popupMenu.add(String.format("Crear extractorGas en %s %s",arg0.getX(),arg0.getY()));
			JMenuItem mitemPoblacion = popupMenu.add(String.format("Crear expansorPoblacion en %s %s",arg0.getX(),arg0.getY()));
			JMenuItem mitemUnidadesBasicas = popupMenu.add(String.format("Crear creadorUnidadesBasicas en %s %s",arg0.getX(),arg0.getY()));
			popupMenu.setEnabled(true);
			popupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			mitemMineral.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			mitemGas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			mitemPoblacion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			mitemUnidadesBasicas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("hiciste Click");
					JOptionPane.showMessageDialog(null,"presionaste la barraca");
					Graphics g = null;
					Image imageBarraca = null;
					try {
						imageBarraca = ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/barraca.png")).toURI()));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}				
					ImageIcon imageIcon = new ImageIcon(imageBarraca);					
					imageIcon.paintIcon(vistaEscenario,g, arg0.getX(), arg0.getY());
				}
			});
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
		
		}
	});
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
	

}
