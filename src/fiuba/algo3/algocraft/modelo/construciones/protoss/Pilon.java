package fiuba.algo3.algocraft.modelo.construciones.protoss;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class Pilon extends Construccion{
	
	public Pilon(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		super(unaPosicion, jugador, unTipo);
	}

	protected int turnosNecesariosParaCreacion() {
		return 5;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	protected Salud saludInicial() {
		return new SaludProtoss(300,300);
	}
	
	public String getNombreObjetoDibujable() {
		return "Pilon";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/pilon.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
	
}
