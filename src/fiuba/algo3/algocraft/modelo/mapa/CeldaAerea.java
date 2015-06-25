package fiuba.algo3.algocraft.modelo.mapa;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAerea extends Celda {
	public CeldaAerea(int fila, int columna){
		super(fila, columna);
	}

	public boolean puedeMoverse(Unidad unaUnidad) {
		return unaUnidad.esUnidadAerea();
	}

	public boolean esAtacable() {
		return this.celdaOcupada();
	}

	public boolean agregarConstruccion(Construccion construccion) {
		return false;
	}

	public boolean esPosbibleConstruir(Construccion construccion) {
		return false;
	}

	public String getNombreObjetoDibujable() {
		return "CeldaAerea";
	}

	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/celdaAerea.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}
