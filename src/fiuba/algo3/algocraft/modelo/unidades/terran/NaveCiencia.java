package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class NaveCiencia extends Unidad {
    private int energia;    

	
	public NaveCiencia(){
		this.energia =  50;
		this.tamanioTransporte = 0;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}

	@Override
	protected Salud saludInicial() {
		return null;
	}

	public boolean esUnidadAerea(){
		return true;
	}
	
	public void acumularEnergia(){ this.energia += 10; }
	public int getEnergia(){return this.energia;}


	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void atacar(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}


	protected int turnosNecesariosParaCreacion() {
		return 10;
	}
}