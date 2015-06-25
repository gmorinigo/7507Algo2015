package fiuba.algo3.algocraft.modelo.mapa;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaConMineral extends Celda {
	public CeldaConMineral(int fila, int columna){
		super(fila, columna);
	}
	
	public boolean tieneMineral(){
		return true;
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return false;
	}
	
	public boolean esAtacable() {
		return false;
	}

	@Override
	public boolean agregarConstruccion(Construccion construccion) {
		if( ! construccion.construccionRecolectoraDeMineral() || this.celdaOcupada())
			return false;
		
		this.ocuparCelda();
		this.construccion = construccion;
		return true;
	}

	@Override
	public boolean esPosbibleConstruir(Construccion construccion) {
		return (construccion.construccionRecolectoraDeMineral() && ! this.celdaOcupada());
	}
	
	public String getNombreObjetoDibujable() {
		return "CeldaConMineral";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaConMineral.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}
