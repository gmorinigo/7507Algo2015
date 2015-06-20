package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoDescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;
import fiuba.algo3.algocraft.modelo.unidades.ataques.AbstractDisparo;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoConRadiacion;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoEMP;

public class NaveCiencia<TipoAtaqueNaveCiencia> extends UnidadTerran {
    private int energia;    
	public enum TipoAtaqueNaveCiencia{MisilEMP, Radiacion}
	
	public NaveCiencia(Jugador unJugador){
		super(unJugador);
		this.energia =  50;
		this.tamanioTransporte = 0;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	public NaveCiencia(Jugador unJugador,Posicion posicionConstruccion){
		super(unJugador);
		this.energia =  50;
		this.tamanioTransporte = 0;
		posicionDeConstruccion = posicionConstruccion;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(200);
	}

	public boolean esUnidadAerea(){
		return true;
	}
	
	public void acumularEnergia(){ 
		if ((this.energia + 10) >= 200){
			this.energia = 200;
		}
		else{
			this.energia += 10;
		}}
	
	public int getEnergia(){return this.energia;}


	public int turnosNecesariosParaCreacion() {
		return 10;
	}

	protected void vivir() {
		this.estado = new UnidadEstadoViviendo(this);
		this.acumularEnergia();		
	}
	
	public boolean atacar(Celda unaCelda) {
		return false;
	}
	

	public boolean atacar(Celda unaCelda, TipoAtaqueNaveCiencia unTipoDeAtaqueNaveCiencia) {
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}		
		boolean ataqueRealizado = false;
				
		switch (unTipoDeAtaqueNaveCiencia){
		case MisilEMP:
			ataqueRealizado = this.atacarConMisilEMP(unaCelda);
		case Radiacion:
			ataqueRealizado = this.atacarConRadiacion(unaCelda);
		default:
			break;
		}

		if(! ataqueRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
	
	
	
	private boolean atacarConRadiacion(Celda unaCelda) {
		if((unaCelda.esAtacable())&&(this.getEnergia()>100)){
		DisparoConRadiacion unaRadiacion = new DisparoConRadiacion(this, 0);
		this.energia = this.energia - 75; 
		return unaRadiacion.disparar(unaCelda);		
				}
		return false;
	}

	private boolean atacarConMisilEMP(Celda unaCelda) {		
		if((unaCelda.esAtacable())&&(this.getEnergia()>100)){
		DisparoEMP unMisilazo = new DisparoEMP(this, 2);  
		this.energia = this.energia - 100;
		return unMisilazo.disparar(unaCelda);
				}
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