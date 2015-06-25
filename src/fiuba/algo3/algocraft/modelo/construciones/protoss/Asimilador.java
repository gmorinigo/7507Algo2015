package fiuba.algo3.algocraft.modelo.construciones.protoss;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeGas;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class Asimilador extends ExtractorDeGas{
	public Asimilador(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}
	

	protected int turnosNecesariosParaCreacion() {
		return 6;
	}


	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}


	protected Salud saludInicial() {
		return new SaludProtoss(450,450);
	}
	
	public String getNombreObjetoDibujable() {
		return "Asimilador";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/asimilador.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}
