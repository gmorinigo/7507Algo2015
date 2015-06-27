package fiuba.algo3.algocraft.modelo.construciones.protoss;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class NexoMineral extends ExtractorDeMineral {

	public NexoMineral(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	protected int turnosNecesariosParaCreacion() {
		return 4;
	}
	
	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	protected Salud saludInicial() {
		return new SaludProtoss(250,250);
	}
	public String getNombreObjetoDibujable() {
		return "NexoMineral";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/nexomineral.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
	
}
