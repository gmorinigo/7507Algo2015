package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.NoSuchObjectException;

import javax.swing.JOptionPane;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

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
