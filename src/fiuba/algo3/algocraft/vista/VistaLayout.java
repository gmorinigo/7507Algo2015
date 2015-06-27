package fiuba.algo3.algocraft.vista;

import javax.swing.JPanel;

import fiuba.algo3.algocraft.modelo.AlgoCraft;

public abstract class VistaLayout extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	protected String id;
	protected AlgoCraft model;

	public  VistaLayout(String id) {
		this.id = id;
		this.model = null;
	}
	
	public String getId(){
		return this.id;
	}
	
	public abstract void reiniciarCampos();
	

	public void setModel(AlgoCraft model) {
		this.model = model;
	}
	
	public AlgoCraft getModel() {
		return this.model;
	}

}
