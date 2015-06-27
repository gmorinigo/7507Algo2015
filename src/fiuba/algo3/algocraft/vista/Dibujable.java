package fiuba.algo3.algocraft.vista;

import java.awt.Image;

import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public interface Dibujable {
	
	public Posicion getPosicion();
	public String getNombreObjetoDibujable();
	public Image getImagen();
	
}
