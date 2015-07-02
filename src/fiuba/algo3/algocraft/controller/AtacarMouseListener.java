package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NoSuchObjectException;

import javax.swing.JOptionPane;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class AtacarMouseListener implements MouseListener {

	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	private Unidad unidad;
	private Celda celdaAtacar;
	
	public AtacarMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego,Posicion posicionCeldaPresionada, Unidad obtenerUnidad) {
		this.juego = juego;
		this.posicionCeldaPresionada = posicionCeldaPresionada;
		this.unidad = obtenerUnidad;
	}
	
	public void mouseReleased(MouseEvent arg0) {		
		this.unidad.atacar(celdaAtacar); 
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
