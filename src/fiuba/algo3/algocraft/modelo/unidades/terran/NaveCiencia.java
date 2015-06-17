package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class NaveCiencia extends UnidadTerran {
    private int energia;    

	
	public NaveCiencia(Jugador unJugador){
		super(unJugador);
		this.energia =  50;
		this.tamanioTransporte = 0;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}

	@Override
	protected Salud saludInicial() {
		return new SaludTerran(200);
	}

	public boolean esUnidadAerea(){
		return true;
	}
	
	public void acumularEnergia(){ this.energia += 10; }
	public int getEnergia(){return this.energia;}


	public int turnosNecesariosParaCreacion() {
		return 10;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean atacar(Celda unaCelda) {
		return false;
	}
	
	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 0;
	} 
	
	protected boolean atacaUnidadesAereas() {
		return false;
	}

	public int getRangoAtaque(Unidad unaUnidad) {
		return 0;
	}
}