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

import fiuba.algo3.algocraft.controller.ControladorNuevaPartida;

public class VistaNuevaPartida extends VistaLayout{
	
	private static final long serialVersionUID = 1L;
	JLabel tituloLabel;
	JLabel tituloLabel2;
	JLabel razaLabelJug1;
	JLabel razaLabelJug2;
	
	JLabel colorLabelJug1;
	JLabel colorLabelJug2;
	
	JTextField nombreJug1TField;
	JTextField nombreJug2TField;
	
	ButtonGroup grupoRazasJug1;
	List<JRadioButton> razaOptionsJug1;
	
	ButtonGroup grupoRazasJug2;
	List<JRadioButton> razaOptionsJug2;
	
	JButton aceptarButton;
	JButton volverButton;

	//--------------------------------------------------
	// Public ------------------------------------------
	
	public VistaNuevaPartida(VentanaPrincipal ventana){
		super("NuevaPartida");
		setLayout(new GridLayout(0, 1));
		this.razaOptionsJug1 = new ArrayList<>();
		this.razaOptionsJug2 = new ArrayList<>();
		grupoRazasJug1 = new ButtonGroup();
		grupoRazasJug2 = new ButtonGroup();
		
		
		tituloLabel = new JLabel("Por favor elija un nombre Jugador1: ", JLabel.CENTER);
		tituloLabel2 = new JLabel("Por favor elija un nombre Jugador2: ", JLabel.CENTER);
		razaLabelJug1 = new JLabel("Por favor elija una raza: ", JLabel.CENTER);
		razaLabelJug2 = new JLabel("Por favor elija una raza: ", JLabel.CENTER);
		
		nombreJug1TField = new JTextField(20);
		nombreJug2TField = new JTextField(20);
		
		aceptarButton = new JButton("Aceptar");
		volverButton = new JButton("Volver");
		
		add(tituloLabel);
		add(nombreJug1TField);
		
		add(razaLabelJug1);
		crearRazaOptionsJug1();
		//add options raza
		Iterator<JRadioButton> it = this.razaOptionsJug1.iterator();
		while(it.hasNext()) {
			add(it.next());
		}
		
		add(tituloLabel2);
		add(nombreJug2TField);
		add(razaLabelJug2);
		crearRazaOptionsJug2();
		//add options raza
		Iterator<JRadioButton> it2 = this.razaOptionsJug2.iterator();
		while(it2.hasNext()) {
			add(it2.next());
		}
		
		add(aceptarButton);
		add(volverButton);
		ControladorNuevaPartida control = new ControladorNuevaPartida(ventana, this);
		
		aceptarButton.addActionListener(control.getListenerButtonAceptarUsers());
		volverButton.addActionListener(control.getListenerButtonVolver());
		
		
	}

	private void crearRazaOptionsJug2() {
		String razaName = "Terran";
		JRadioButton raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		
		this.grupoRazasJug2.add(raza);
		this.razaOptionsJug2.add(raza);
		
		razaName = "Protoss";
		raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		this.grupoRazasJug2.add(raza);
		this.razaOptionsJug2.add(raza);
	}

	protected void crearRazaOptionsJug1() {
		String razaName = "Terran";
		JRadioButton raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		
		this.grupoRazasJug1.add(raza);
		this.razaOptionsJug1.add(raza);
		
		razaName = "Protoss";
		raza = new JRadioButton(razaName);
		raza.setActionCommand(razaName);
		this.grupoRazasJug1.add(raza);
		this.razaOptionsJug1.add(raza);
	}
	
	public String dameNombreJug1() {
		return nombreJug1TField.getText();		
	}
	
	public String dameNombreJug2() {
		return nombreJug2TField.getText();		
	}
	
	public String dameRazaSeleccionadaJug1() {
		if(grupoRazasJug1.getSelection() == null) {
			return "";
		}
		return grupoRazasJug1.getSelection().getActionCommand();
	}

	public String dameRazaSeleccionadaJug2() {
		if(grupoRazasJug2.getSelection() == null) {
			return "";
		}
		return grupoRazasJug2.getSelection().getActionCommand();
	}
	
	@Override
	public void reiniciarCampos() {
	}


}
