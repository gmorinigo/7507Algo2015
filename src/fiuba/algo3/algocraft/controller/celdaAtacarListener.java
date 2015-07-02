package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class celdaAtacarListener implements MouseListener {
	private Unidad unidad;
	private Celda celdaAtacar;
	
	public celdaAtacarListener(Unidad unidad) {
		
		this.unidad = unidad;
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

	@Override
	public void mouseReleased(MouseEvent arg0) {

		Mapa mapaDelJuego = Mapa.getInstance();
		Posicion posicionCeldaPresionada =  MapaMouseListener.convertirPixAPosicionCelda(arg0.getX(),arg0.getY());
		Celda celdaAtacar = mapaDelJuego.dameCelda(posicionCeldaPresionada);
		unidad.atacar(celdaAtacar);
	}

}
