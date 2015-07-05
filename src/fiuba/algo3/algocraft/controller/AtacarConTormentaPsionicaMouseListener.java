package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario.TipoAtaqueAltoTemplario;

public class AtacarConTormentaPsionicaMouseListener implements MouseListener{

	private Unidad unidad;
	
	public AtacarConTormentaPsionicaMouseListener(Unidad obtenerUnidad) {
		this.unidad = obtenerUnidad;
		
	}

	public void mouseReleased(MouseEvent arg0) {
		MapaMouseListener.activarAtaqueAltoTemplario(unidad, TipoAtaqueAltoTemplario.TormentaPsionica);
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