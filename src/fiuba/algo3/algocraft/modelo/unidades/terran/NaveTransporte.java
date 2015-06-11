package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadCinco;

public class NaveTransporte extends UnidadCinco {

	private String nombre;

	public NaveTransporte(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	@Override
	public void disparar() {}

	@Override
	public String getName() {return this.nombre;}

	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean esUnidadAerea(){
		return true;
	}
	
	@Override
	public void finDeTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}

}
