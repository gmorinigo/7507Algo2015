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
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public abstract class VistaObjetoDibujable implements Observer{
	
	protected Dibujable objeto;
	protected VistaEscenario vistaEscenario;

	
	public VistaObjetoDibujable(Dibujable objeto, VistaEscenario vistaEscenario){
		this.vistaEscenario = vistaEscenario;
		this.objeto = objeto;
	}
	
	public static Posicion convertToPix(Posicion pos) {	
		int x = pos.dameColumna();
		int y = pos.dameFila();
		return new Posicion( 30 +60*(x-1), 30 + 60*(y-1));
		//return new Posicion( x*40, y*40);
	}

	public void paint(Graphics g) {
		Image image = null;
		ImageIcon imageIcon;
		Posicion posPixel = VistaObjetoDibujable.convertToPix(this.objeto.getPosicionActual());
		
		//Cargamos la imagen del objeto que vamos a pintar en la ciudad.
		switch(this.objeto.getNombreObjetoPosicionable())
		{
		case "CeldaTerrestre":
		{
			try {			
				image = ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaTerrestre.png")).toURI()));
				
			} catch (IOException | NullPointerException | URISyntaxException ex) {
			}	
		}break;
		case "CeldaAerea":
		{
			try {			
				image = ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaAerea.png")).toURI()));				
			} catch (IOException | NullPointerException | URISyntaxException ex) {
			}	
		}break;
		case "CeldaConGas":
		{
			try {			
				image = ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaConGas.png")).toURI()));				
			} catch (IOException | NullPointerException | URISyntaxException ex) {
			}	
		}break;
		case "CeldaConMineral":
		{
			try {			
				image = ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaConMineral.png")).toURI()));				
			} catch (IOException | NullPointerException | URISyntaxException ex) {
			}	
		}break;
		}
		imageIcon = new ImageIcon(image);
		imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
	}
}

