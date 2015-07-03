package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class AtacarMouseListener implements MouseListener/*,MouseMotionListener */{

	private Unidad unidad;
	
	public AtacarMouseListener(Unidad obtenerUnidad) {
		this.unidad = obtenerUnidad;
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		MapaMouseListener.activarAtaque();
		MapaMouseListener.unidad = unidad;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
/*
	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("entre");
		Mapa mapaDelJuego = Mapa.getInstance();
		Posicion posicionCeldaPresionada =  MapaMouseListener.convertirPixAPosicionCelda(arg0.getX(),arg0.getY());
		Celda celdaAtacable = mapaDelJuego.dameCelda(posicionCeldaPresionada);
		unidad.atacar(celdaAtacable);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
*/
}
