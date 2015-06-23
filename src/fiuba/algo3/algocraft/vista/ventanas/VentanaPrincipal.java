package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal {
	
	JFrame frame;
 	JPanel paneles;
 	private HashMap<String, VistaLayout> vistas;
	
	private void initComponents(){
		paneles = new JPanel(new CardLayout());
		frame.getContentPane().add(paneles);
		
	}
	

	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VentanaPrincipal(int ancho, int alto){
		frame = new JFrame("AlgoCraft");
		frame.setSize(ancho, alto);
		this.vistas = new HashMap<>();
		this.initComponents();
		
		frame.setResizable(false);
		frame.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void showLayout(String id){
		CardLayout cl = (CardLayout)(paneles.getLayout());
		cl.show(paneles, id);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	public void agregarLayout(VistaLayout vista){
		paneles.add(vista, vista.getId());
		this.vistas.put(vista.getId(), vista);
	}
	public VistaLayout getLayout(String id){
		return (VistaLayout)this.vistas.get(id);
	}

	public void ocultar() {
		frame.setVisible(false);
	}
	


}
