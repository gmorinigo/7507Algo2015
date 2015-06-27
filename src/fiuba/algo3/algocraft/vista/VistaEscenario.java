package fiuba.algo3.algocraft.vista;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.vista.objetosdibujables.VistaCelda;


public class VistaEscenario extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	Mapa mapaDelJuego;
	AlgoCraft unJuegoObservable;
	private List<VistaObjetoDibujable> vistasObjetosDibujables;
	int tamanio;
	int dim;
	
	public VistaEscenario(Jugador jug, AlgoCraft unJuegoObservable) {
		this.unJuegoObservable = unJuegoObservable;
		tamanio  = 20;
		dim = 30*tamanio;

		mapaDelJuego = Mapa.getInstance();
		
        setPreferredSize(new Dimension(dim ,dim));
        this.vistasObjetosDibujables = new ArrayList<VistaObjetoDibujable>();
        this.cargarMapa();
        this.unJuegoObservable.addObserver(this);
        setLayout(new BorderLayout()); 
	}
	
	private void cargarMapa() {
		this.vistasObjetosDibujables.clear();
		for (int i = 0; i<20;i++){
			for (int j = 0; j<20; j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i, j)); 
				this.vistasObjetosDibujables.add(new VistaCelda(unaCelda,this));
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<VistaObjetoDibujable> it = this.vistasObjetosDibujables.iterator();
		while(it.hasNext()){
			it.next().paint(g);
		}
		
		revalidate();
	}

	public void update(Observable arg0, Object arg1) {
        this.cargarMapa();
        repaint();
	}

	
}