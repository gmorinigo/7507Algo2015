package fiuba.algo3.algocraft.modelo.construciones.terran;

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
import fiuba.algo3.algocraft.modelo.unidades.terran.SaludTerran;

public class DepositoDeSuministro extends Construccion {

	public DepositoDeSuministro(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		super(unaPosicion, jugador, unTipo);
	}


	protected int turnosNecesariosParaCreacion() {
		return 6;
	}

	protected void vivir() {
	}


	protected Salud saludInicial() {
		return new SaludTerran(500);

	}

	public String getNombreObjetoDibujable() {
		return "DepositoSuministros";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/depositosuministros.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}
