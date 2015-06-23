package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import fiuba.algo3.algocraft.controller.ControladorBienvenido;

public class VistaBienvenido extends VistaLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel tituloLabel;
	JButton nuevaPartida;
	
	
	
	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VistaBienvenido(ControladorBienvenido control){
		super("Bienvenido");
		setLayout(new GridLayout(0, 1));
		tituloLabel = new JLabel("Bienvenido", JLabel.CENTER);
		nuevaPartida = new JButton("Nueva Partida");
		add(tituloLabel);
		add(nuevaPartida);
		
		nuevaPartida.addActionListener(control.getListenerButtonNuevaPartida());
		
	}

	@Override
	public void reiniciarCampos() {
		
	}

	
}
