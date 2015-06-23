package fiuba.algo3.algocraft.vista.ventanas;

import javax.swing.JPanel;

import fiuba.algo3.algocraft.modelo.Jugador;


public abstract class VistaLayout extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	protected String id;
	protected Jugador model;

	public  VistaLayout(String id) {
		this.id = id;
		this.model = null;
	}
	
	public String getId(){
		return this.id;
	}
	
	public abstract void reiniciarCampos();
	

	public void setModel(Jugador model) {
		this.model = model;
	}
	
	public Jugador getModel() {
		return this.model;
	}

}
