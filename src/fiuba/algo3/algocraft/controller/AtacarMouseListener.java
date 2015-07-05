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
		MapaMouseListener.activarAtaque(unidad);
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
}
