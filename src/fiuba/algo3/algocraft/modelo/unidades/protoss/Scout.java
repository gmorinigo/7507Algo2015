package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTres;

public class Scout extends UnidadTres {

	private String nombre;
	public Scout(String nombre) {	}
	public void disparar() {}
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
