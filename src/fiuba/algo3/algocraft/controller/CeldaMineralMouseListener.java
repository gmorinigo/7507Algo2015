package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NoSuchObjectException;

import javax.swing.JOptionPane;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;
	
public class CeldaMineralMouseListener extends MouseAdapter{
		
	//private VentanaMapa ventanaMapa;
	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	
    public CeldaMineralMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego, Posicion posicionCeldaPresionada) {
    	//this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
    	this.posicionCeldaPresionada = posicionCeldaPresionada;
	}
    
    
	public void mouseReleased(MouseEvent arg0) {		
		// Acá tiene que construir el jugador del turno actual
		try {
			this.juego.agregarConstruccion(this.juego.dameElJugadorDelTurno(),TipoConstruccion.extractorMineral,posicionCeldaPresionada);
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		} catch (CantidadDeMineralInsuficienteException e) {
			JOptionPane.showMessageDialog(null,"Mineral Insuficiente"); 
		} catch (CantidadDeGasInsuficienteException e) {
			JOptionPane.showMessageDialog(null,"Gas Insuficiente"); 
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException e) {
			JOptionPane.showMessageDialog(null,"Falta construir un Puerto Estelar"); 
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException e) {
			JOptionPane.showMessageDialog(null,"Falta construir un Acceso"); 
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException e) {
			JOptionPane.showMessageDialog(null,"Falta construir una Barraca"); 
		} catch (NoHaySuficientesRecursos e) {
			JOptionPane.showMessageDialog(null,"No hay suficientes recursos"); 
		} catch (NoSePudoConstruirException e) {
			JOptionPane.showMessageDialog(null,"No se Puede Construir en esa posicion"); 
		};
	}

}

