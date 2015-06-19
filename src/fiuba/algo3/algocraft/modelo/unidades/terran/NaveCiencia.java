package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoDescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;
import fiuba.algo3.algocraft.modelo.unidades.ataques.AbstractDisparo;

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

	protected void vivir() {
		this.acumularEnergia();		
	}
	
	public boolean atacar(Celda unaCelda) {
		return false;
	}
	
	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 0;
	} 
	
	public int DanioAtaque(Construccion construccion) {
		return 0;
	}
	
	protected boolean atacaUnidadesAereas() {
		return false;
	}

	public int getRangoAtaque(Unidad unaUnidad) {
		return 0;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 0;
	}
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}
	
	public boolean atacar(Celda unaCelda, AbstractDisparo unDisparo) {
		return false;
	/*	if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}*/
	
		
//		unaCelda.atacarUnidadDeLaCeldaConUnidad(this);
	/*	unDisparo.disparar(unaCelda);
		boolean disparoRealizado = this.disparo.disparar(unaCelda);
		
		if(! disparoRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;*/

	}
}