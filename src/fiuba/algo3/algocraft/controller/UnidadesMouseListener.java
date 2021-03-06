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
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class UnidadesMouseListener implements MouseListener {

	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	private TipoUnidad tipoUnidad;
	
    public UnidadesMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego, Posicion posicionCeldaPresionada, TipoUnidad unTipoDeUnidad) {
    	//this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
    	this.posicionCeldaPresionada = posicionCeldaPresionada;
    	tipoUnidad = unTipoDeUnidad;
	}
    
    
	public void mouseReleased(MouseEvent arg0) {		
		// Ac� tiene que construir el jugador del turno actual
		try {
			try {
				this.juego.agregarUnidad(this.juego.dameElJugadorDelTurno(),tipoUnidad,posicionCeldaPresionada);
			} catch (CapacidadDePoblacionMaximaSuperada e) {
				JOptionPane.showMessageDialog(null,"No hay poblacion"); 
			}
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		} catch (CantidadDeMineralInsuficienteException e) {
			JOptionPane.showMessageDialog(null,"Mineral Insuficiente"); 
		} catch (CantidadDeGasInsuficienteException e) {
			JOptionPane.showMessageDialog(null,"Gas Insuficiente"); 
		} catch (NoHaySuficientesRecursos e) {
			JOptionPane.showMessageDialog(null,"No hay suficientes recursos"); 
		} 
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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