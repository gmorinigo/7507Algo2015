package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class MovimientoMouseListener implements MouseListener {

	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	
    public MovimientoMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego, Posicion posicionCeldaPresionada,TipoDireccion unMovimiento) {
    	//this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
    	this.posicionCeldaPresionada = posicionCeldaPresionada;
	}
    
    
	public void mouseReleased(MouseEvent arg0) {		
		// Ac� tiene que construir el jugador del turno actual
	
		try {
			this.juego.agregarUnidad(this.juego.dameElJugadorDelTurno(),TipoUnidad.terrestre1,posicionCeldaPresionada);
		} catch (CapacidadDePoblacionMaximaSuperada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CantidadDeMineralInsuficienteException e) {
			// TODO Auto-generated catch blockk
			e.printStackTrace();
		} catch (CantidadDeGasInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (NoHaySuficientesRecursos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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