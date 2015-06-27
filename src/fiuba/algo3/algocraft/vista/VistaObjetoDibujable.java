package fiuba.algo3.algocraft.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Observer;
import javax.swing.ImageIcon;

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
		//return new Posicion( 30 +60*(x-1), 30 + 60*(y-1));
		return new Posicion( x*32, y*32);
	}

	public void paint(Graphics g) {
		Image image = null;
		ImageIcon imageIcon;
		Posicion posPixel = VistaObjetoDibujable.convertToPix(this.objeto.getPosicion());
		
		Celda unaCelda = (Celda) this.objeto;
		
		if (unaCelda.tieneUnidad()){
			image = unaCelda.obtenerUnidad().getImagen();
		}
		else{
			if (unaCelda.tieneConstruccion()){
				image = unaCelda.obtenerConstruccion().getImagen();	
			}
		}
		
		if (!unaCelda.tieneUnidad() && !unaCelda.tieneConstruccion()){
			image = this.objeto.getImagen();
		}
		
		imageIcon = new ImageIcon(image);
		
		if (unaCelda.tieneConstruccion() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeGas() && !unaCelda.obtenerConstruccion().construccionRecolectoraDeMineral()){
			//TODO Ver como dibujamos la construccion en 4 celdas
			imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
		}
		else{
			imageIcon.paintIcon(vistaEscenario, g, posPixel.dameColumna(), posPixel.dameFila());
		}
	}

}

