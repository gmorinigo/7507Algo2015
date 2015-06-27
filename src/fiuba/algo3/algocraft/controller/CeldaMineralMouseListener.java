package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NoSuchObjectException;

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
		
	private VentanaMapa ventanaMapa;
	private AlgoCraft juego;
	private Posicion posicionCeldaPresionada;
	
    public CeldaMineralMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego, Posicion posicionCeldaPresionada) {
    	this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
    	this.posicionCeldaPresionada = posicionCeldaPresionada;
	}
    
    
	public void mouseReleased(MouseEvent arg0) {		
		// Acá tiene que construir el jugador del turno actual
		try {
			this.juego.agregarConstruccion(this.juego.dameJugador1(),TipoConstruccion.extractorMineral,posicionCeldaPresionada);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CantidadDeMineralInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CantidadDeGasInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoHaySuficientesRecursos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSePudoConstruirException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}

