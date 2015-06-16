package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;


public class Marine extends UnidadTerran {

	//private DisparoStrategy disparoStrategy;


	public Marine() {
		this.tamanioTransporte = 1;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(40);
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		if(!this.estado.esPosibleRealizarAccion()) {
			//lanzar Exception
		}
	
		//Realizar Movimiento
		
		//cambio el estado
		this.estado = new UnidadEstadoescansando(this);
	}

	public int turnosNecesariosParaCreacion() {
		return 3;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
	}
	
	public int DanioAtaque() {
		return 6;
	} 
}
