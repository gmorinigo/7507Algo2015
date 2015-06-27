package fiuba.algo3.algocraft.vista;

import java.awt.Graphics;
import java.awt.Image;
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
		
		//Image otraimg = img.getScaledInstance(115,230,java.awt.Image.SCALE_SMOOTH); //creamos una imagen nueva dándole las dimensiones que queramos a la antigua

		Image imagenEscalada = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		//imageIcon = new ImageIcon(image);
		imageIcon = new ImageIcon(imagenEscalada);
		
		if (unaCelda.tieneConstruccion() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeGas() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeMineral()){
			//TODO Ver como dibujamos la construccion en 4 celdas
			imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
		}
		else{
			imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
		}
	}

}

