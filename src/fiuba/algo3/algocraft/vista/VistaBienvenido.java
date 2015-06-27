package fiuba.algo3.algocraft.vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import fiuba.algo3.algocraft.controller.ControladorBienvenido;
import fiuba.algo3.algocraft.vista.ventanas.VentanaPrincipal;

public class VistaBienvenido extends VistaLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel tituloLabel;
	JButton nuevaPartida;
	
	
	
	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VistaBienvenido(VentanaPrincipal ventana){
		super("Bienvenido");
		setLayout(new GridLayout(0, 1));
		tituloLabel = new JLabel("Bienvenido", JLabel.CENTER);
		nuevaPartida = new JButton("Nueva Partida");
		add(tituloLabel);
		add(nuevaPartida);
		
		ControladorBienvenido control = new ControladorBienvenido(ventana, this);
		
		nuevaPartida.addActionListener(control.getListenerButtonNuevaPartida());
		
	}

	@Override
	public void reiniciarCampos() {
		
	}

	
}
