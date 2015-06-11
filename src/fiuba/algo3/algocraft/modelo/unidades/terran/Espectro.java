package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTres;

public class Espectro extends UnidadTres {

	private String nombre;

	
	public Espectro(String nombre/*, DisparoStrategy disparoStrategy*/){
		this.nombre=nombre;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	
	@Override
	public void disparar() {  
		//		this.disparoStrategy.disparar();
		}
	
	
	@Override
	public String getName(){ return this.nombre; }


	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void finDeTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}
	
	

}
