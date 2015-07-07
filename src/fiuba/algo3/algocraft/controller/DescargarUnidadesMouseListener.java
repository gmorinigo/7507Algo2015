package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class DescargarUnidadesMouseListener  implements MouseListener{

	private NaveTransporte naveTransporte;
	private AlgoCraft juego;
	
	public DescargarUnidadesMouseListener(Unidad obtenerUnidad, AlgoCraft juego) {
		this.naveTransporte = (NaveTransporte) obtenerUnidad;
		this.juego = juego;
	}

	public void mouseReleased(MouseEvent arg0) {	
		naveTransporte.descargarUnidades();
		this.juego.avisarObservers();	
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