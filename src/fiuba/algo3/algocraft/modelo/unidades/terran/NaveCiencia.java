package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadCuatro;

public class NaveCiencia extends UnidadCuatro {
    private int energia;    

	
	public NaveCiencia(){
		this.energia =  50;   
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	
	@Override
	public void disparar() {

	}

	@Override
	protected Salud saludInicial() {
		return null;
	}

	public boolean esUnidadAerea(){
		return true;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(10, this);
	}
	
	public void acumularEnergia(){ this.energia += 10; }
	public int getEnergia(){return this.energia;}
}