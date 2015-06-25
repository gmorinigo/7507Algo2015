package fiuba.algo3.algocraft.modelo.unidades.terran;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class Espectro extends UnidadTerran {

	
	public Espectro(Jugador unJugador){
		super(unJugador);
		this.tamanioTransporte = 0;
	}
	
	public Espectro(Jugador unJugador,Posicion posicionConstruccion){
		super(unJugador);
		this.tamanioTransporte = 0;
		posicionDeConstruccion = posicionConstruccion;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(120);
	}

	public boolean esUnidadAerea(){
		return true;
	}


	public int turnosNecesariosParaCreacion() {
		return 8;
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		if (unaUnidadAtacada.esUnidadAerea()){
			return 20;
		}
		else {
			return 8;			
		}
	} 
	
	public int DanioAtaque(Construccion construccion) {
		return 8;
	}
	
	
	protected boolean atacaUnidadesAereas() {
		return true;
	}

	public int getRangoAtaque(Unidad unaUnidad) {
		return 5;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 5;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}

	public String getNombreObjetoDibujable() {
		return "Espectro";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/espectro.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}
}
