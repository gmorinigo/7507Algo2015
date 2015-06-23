package fiuba.algo3.algocraft.vista.ventanas;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


import fiuba.algo3.algocraft.controller.ControladorNuevaPartida;

public class VistaNuevaPartida extends VistaLayout{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel tituloLabel;
	JTextField nombreJug1TField;
	JTextField nombreJug2TField;
	JButton aceptarButton;
	JButton volverButton;

	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VistaNuevaPartida( ControladorNuevaPartida control){
		super("NuevaPartida");
		setLayout(new GridLayout(0, 1));
		tituloLabel = new JLabel("Por favor elija un nombre: ", JLabel.CENTER);
		nombreJug1TField = new JTextField(20);
		nombreJug2TField = new JTextField(20);
		aceptarButton = new JButton("Aceptar");
		volverButton = new JButton("Volver");
		
		add(tituloLabel);
		add(nombreJug1TField);
		add(nombreJug2TField);
		add(aceptarButton);
		add(volverButton);
		
		aceptarButton.addActionListener(control.getListenerButtonAceptarUsers(this));
		volverButton.addActionListener(control.getListenerButtonVolver(this));
		
		
	}

	public String getNameUser1() {
		return nombreJug1TField.getText();
		
	}

	public String getNameUser2() {
		return nombreJug2TField.getText();
	}
	
	@Override
	public void reiniciarCampos() {
		nombreJug1TField.setText("");
		nombreJug2TField.setText("");
	}


}
