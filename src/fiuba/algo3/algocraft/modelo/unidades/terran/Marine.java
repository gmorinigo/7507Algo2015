package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.ProgresoCreacion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadUno;


public class Marine extends UnidadUno {

	private String nombre;
	//private DisparoStrategy disparoStrategy;
	
	public Marine(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
//		this.disparoStrategy=disparoStrategy;
//		this.disparoStrategy.setUnidad(this);
	}
	
	public void disparar()
	{
//		this.disparoStrategy.disparar();
	}
	
	public String toString(){
		return "Unidad "+nombre;
	}
	public String getName(){ return this.nombre; }

	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProgresoCreacion progresoCreacion() {
		return new ProgresoCreacion(3, this);
	} 
}
