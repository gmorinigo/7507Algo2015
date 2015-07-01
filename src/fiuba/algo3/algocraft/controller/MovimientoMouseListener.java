package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NoSuchObjectException;

import javax.swing.JOptionPane;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class MovimientoMouseListener implements MouseListener {

	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	private Unidad unidad;
	private TipoDireccion direccion;
	
    public MovimientoMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego, Posicion posicionCeldaPresionada,TipoDireccion unMovimiento, Unidad unidad) {
    	//this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
    	this.posicionCeldaPresionada = posicionCeldaPresionada;
    	this.unidad = unidad;
    	this.direccion = unMovimiento;
	}
    
    
	public void mouseReleased(MouseEvent arg0) {		
		// Acá tiene que construir el jugador del turno actual
	
		try {
			unidad.mover(direccion);
			this.juego.avisarObservers();
		}  catch (MaximaCapacidadDeTransporteSuperadaException e) {
			JOptionPane.showMessageDialog(null,"Nave de Transporte llena"); 
		} catch (NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e) {
			JOptionPane.showMessageDialog(null,"No se puede Agregar a la Nave una unidad voladora"); 
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
