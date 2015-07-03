package fiuba.algo3.algocraft.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import fiuba.algo3.algocraft.AlgoCraft;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public abstract class VistaObjetoDibujable implements Observer{
	
	protected Dibujable objeto;
	protected VistaEscenario vistaEscenario;

	
	public VistaObjetoDibujable(Dibujable objeto, VistaEscenario vistaEscenario){
		this.vistaEscenario = vistaEscenario;
		this.objeto = objeto;
	}
	
	public static Posicion convertToPix(Posicion pos) {	
		int x = pos.dameFila();
		int y = pos.dameColumna();
		return new Posicion(x*30, y*30);
	}

	public void paint(Graphics g) {
		Image image = null;
		ImageIcon imageIcon;
		Posicion posPixel = VistaObjetoDibujable.convertToPix(this.objeto.getPosicion());
		
		Celda unaCelda = (Celda) this.objeto;
		
		String path = "";
		if (unaCelda.tieneUnidad()){
			path = "/fiuba/algo3/algocraft/resources/images/" + unaCelda.obtenerUnidad().getNombreObjetoDibujable() + ".png"; 
		}
		else{
			if (unaCelda.tieneConstruccion()){
				path = "/fiuba/algo3/algocraft/resources/images/" + unaCelda.obtenerConstruccion().getNombreObjetoDibujable() + ".png"; 
			}
		}
		
		if (!unaCelda.tieneUnidad() && !unaCelda.tieneConstruccion()){
			path = "/fiuba/algo3/algocraft/resources/images/" + unaCelda.getNombreObjetoDibujable() + ".png";
		}
		
		try {
			image = ImageIO.read(new File((AlgoCraft.class.getResource(path)).toURI()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		if (unaCelda.tieneConstruccion() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeGas() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeMineral()){
			if (unaCelda.getPosicion().compararPosicion(unaCelda.obtenerConstruccion().damePosicionCeldaSupIzquierda())){
				Image imagenEscalada = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				BufferedImage imagenConFiltro = this.filtrarColor(imagenEscalada, unaCelda);
				imageIcon = new ImageIcon(imagenConFiltro);
				imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
			}
		}
		else{
			if (unaCelda.tieneConstruccion() || unaCelda.tieneUnidad()){
				Image imagenEscalada = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				BufferedImage imagenConFiltro = this.filtrarColor(imagenEscalada, unaCelda);
				imageIcon = new ImageIcon(imagenConFiltro);
				imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
			}
			else{
				Image imagenEscalada = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(imagenEscalada);
				imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
			}
		}
	}

	public BufferedImage filtrarColor(Image imagenEscalada, Celda unaCelda){
		
		String color = "";
		if (unaCelda.tieneUnidad()){
			color = unaCelda.obtenerUnidad().getJugador().dameColor();
		}
		else{
			color = unaCelda.obtenerConstruccion().getJugador().dameColor();
		}
		
		//Creación BufferedImage con la imagen
		BufferedImage bi = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imagenEscalada.getWidth(null), imagenEscalada.getHeight(null), Transparency.OPAQUE);
		bi.getGraphics().drawImage(imagenEscalada, 0, 0, null);
		
		//Crea una copia del mismo tamaño que la imagen
		BufferedImage biDestino = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(bi.getWidth(), bi.getHeight(), Transparency.OPAQUE);
		//Recorre las imagenes y obtiene el color de la imagen original y la almacena en el destino
		for (int x=0;x < bi.getWidth();x++){
			for (int y=0; y < bi.getHeight();y++){
				//Obtiene el color
				Color c1=new Color(bi.getRGB(x, y));
				// Color RGB -> Fuerzo el Azul
				if (color.equals("Rojo")) biDestino.setRGB(x, y, new Color(100,c1.getGreen(),c1.getBlue()).getRGB());
				if (color.equals("Verde")) biDestino.setRGB(x, y, new Color(c1.getRed(),100,c1.getBlue()).getRGB());
				if (color.equals("Azul")) biDestino.setRGB(x, y, new Color(c1.getRed(),c1.getGreen(),150).getRGB());
			}
		}
		return biDestino;
	}
}

