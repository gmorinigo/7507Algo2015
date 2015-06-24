package fiuba.algo3.algocraft.vista.ventanas;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fiuba.algo3.algocraft.controller.ControladorNuevoJugagor1;

public class VistaNuevoJugador1 extends VistaLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel tituloLabel;
	JLabel razaLabel;
	JTextField nombreJug1TField;
	ButtonGroup grupoRazas;
	List<JRadioButton> razaOptions;
	JButton aceptarButton;
	JButton volverButton;

	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VistaNuevoJugador1(VentanaPrincipal ventana){
		super("NuevoJugador1");
		setLayout(new GridLayout(0, 1));
		this.razaOptions = new ArrayList<>();
		grupoRazas = new ButtonGroup();
		
		
		tituloLabel = new JLabel("Por favor elija un nombre: ", JLabel.CENTER);
		razaLabel = new JLabel("Por favor elija una raza: ", JLabel.CENTER);
		nombreJug1TField = new JTextField(20);
		aceptarButton = new JButton("Aceptar");
		volverButton = new JButton("Volver");
		
		add(tituloLabel);
		add(nombreJug1TField);
		add(razaLabel);
		
		this.crearRazasOptions();
		
		//add options raza
		Iterator<JRadioButton> it = this.razaOptions.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
		
		add(aceptarButton);
		add(volverButton);
		ControladorNuevoJugagor1 control = new ControladorNuevoJugagor1(ventana, this);
		
		aceptarButton.addActionListener(control.getListenerButtonAceptarUsers());
		volverButton.addActionListener(control.getListenerButtonAceptarUsers());
		
		
	}

	private void crearRazasOptions() {
		
		String razaName = "Terran";
		JRadioButton raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		
		this.grupoRazas.add(raza);
		this.razaOptions.add(raza);
		
		razaName = "Protoss";
		raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		this.grupoRazas.add(raza);
		this.razaOptions.add(raza);
	}

	public String getNameUser() {
		return nombreJug1TField.getText();		
	}
	
	public String getRazaSeleccionada() {
		return grupoRazas.getSelection().getActionCommand();
	}
	
	@Override
	public void reiniciarCampos() {
	}


}
