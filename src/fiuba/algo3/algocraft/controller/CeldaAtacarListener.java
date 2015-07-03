package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class CeldaAtacarListener implements MouseListener {
	private Unidad unidad;
	private Celda celdaAtacar;
	
	public CeldaAtacarListener(Unidad unidad,Celda celda) {
		
		this.unidad = unidad;
		celdaAtacar = celda;
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		unidad.atacar(celdaAtacar);
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
