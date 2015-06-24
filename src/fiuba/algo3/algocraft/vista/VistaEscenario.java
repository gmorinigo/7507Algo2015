package fiuba.algo3.algocraft.vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.vista.objetosdibujables.VistaCelda;


public class VistaEscenario extends JPanel {
	private static final long serialVersionUID = 1L;
	Mapa mapaDelJuego;
	//MapaPanel mapaPanel;
	//NieblaPanel nieblaPanel;
	private List<VistaObjetoDibujable> vistasObjetosDibujables;
	int tamanio;
	int dim;
	
	public VistaEscenario(Jugador jug) {
		tamanio  = 20;
		dim = 32*tamanio;
		//dim = (tamanio+1) * 40 + 20*tamanio;

		mapaDelJuego = Mapa.getInstance();
		
        setPreferredSize(new Dimension(dim ,dim));
        this.vistasObjetosDibujables = new ArrayList<VistaObjetoDibujable>();
        this.cargarMapaInicial();
        //this.crearVistaVehiculo(jug.getVehiculo());
        //this.crearVistasSituaciones(jug.obtenerSituacionesCiudad());
        
        //this.mapaPanel = new MapaPanel();
		//this.nieblaPanel = new NieblaPanel(jug.getVehiculo(), jug.getCiudad().getMeta());
		
        setLayout(new BorderLayout()); 
	}
	
	private void cargarMapaInicial() {
		for (int i = 0; i<20;i++){
			for (int j = 0; j<20; j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i, j)); 
				this.vistasObjetosDibujables.add(new VistaCelda(unaCelda,this));
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//this.mapaPanel.paint(g);
		Iterator<VistaObjetoDibujable> it = this.vistasObjetosDibujables.iterator();
		while(it.hasNext()){
			it.next().paint(g);
		}
		
		//this.nieblaPanel.paint(g);
		revalidate();
		
	}

	/*
	private void crearVistaVehiculo(Celda celda){
		VistaCelda vista = new VistaCelda(celda, this);
		this.vistasPosicionables.add(Celda);
	}*/
	
	/*
	private class MapaPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage celdaImagen;
		
		public MapaPanel()
		{
			try {
				celdaImagen = ImageIO.read(new File("/fiuba/algo3/tp2/resources/images/grass.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		public void paint(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(celdaImagen, null, 0,0);
		}
	}
	*/
	/*private class NieblaPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		private BufferedImage nieblaImagen;*/
		//private Posicionable vehiculo;
		//private Posicionable meta;
		
/*		public NieblaPanel()//Posicionable vehiculo, Posicionable meta)
		{
			this.updateNieblaImagen();
		}
		
		
		
		public void paint(Graphics g){
			super.paintComponent(g);
			
			updateNieblaImagen();
			
			g.drawImage(nieblaImagen, 0, 0, this);
		}
*/

/*
		private void updateNieblaImagen() {
	
			nieblaImagen = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
			
			Area donut = new Area(new Rectangle2D.Double(0, 0, dim, dim));
			Posicion pos1 = VistaObjetoDibujable.convertToPix(vehiculo.getPosicionActual());
			centrarPosicion(pos1, 240);
			Posicion pos2 = VistaObjetoDibujable.convertToPix(meta.getPosicionActual());
			centrarPosicion(pos2, 120);
			
		    Area hole1 = new Area(new Ellipse2D.Double(pos1.dameFila(), pos1.dameColumna(), 240, 240));
		    Area hole2 = new Area(new Ellipse2D.Double(pos2.dameColumna(), pos2.dameColumna(), 120, 120));
		    
		    donut.subtract(hole1);
		    donut.subtract(hole2);
		    
		    Graphics2D g = nieblaImagen.createGraphics();
		    g.setColor(Color.black);
		    g.fill(donut);
		}



		private void centrarPosicion(Posicion pos, int diamtro) {
			int radio = diamtro / 2;
			
			pos.setFila(pos.dameFila() - radio + 10);
			pos.setColumna(pos.dameColumna() - radio + 10);
		}
	}*/

}