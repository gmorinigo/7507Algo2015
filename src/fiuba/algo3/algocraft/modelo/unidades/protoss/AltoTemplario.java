package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoDescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class AltoTemplario extends UnidadProtoss {
    @SuppressWarnings("unused")
	private int energia;    
	public enum TipoAtaqueAltoTemplario{TormentaPsionica, Alucinacion}
	
	public AltoTemplario(Jugador unJugador) {
		super(unJugador);
		this.energia =  50;
		this.tamanioTransporte = 2;
	}
	
	public AltoTemplario(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 2;
		this.posicionDeConstruccion = posicionConstruccion;
	}
	
	
	protected Salud saludInicial() {
		return new SaludProtoss(40,40);
	}
	
	protected void vivir() {
		this.salud.regenerarEscudo();
		this.estado = new UnidadEstadoViviendo(this);
		this.acumularEnergia();		
	}
	
	public void acumularEnergia(){
		this.energia += 15; 
	}

	public int turnosNecesariosParaCreacion() {
		return 7;
	}

	public boolean atacar(Celda unaCelda) {
		return false;
	}

	
	public boolean atacar(Celda unaCelda, TipoAtaqueAltoTemplario unTipoDeAtaqueAltoTemplario) {
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean ataqueRealizado = false;
				
		switch (unTipoDeAtaqueAltoTemplario){
		case Alucinacion:
			ataqueRealizado = this.atacarConAlucinacion(unaCelda);
		case TormentaPsionica:
			ataqueRealizado = this.atacarConTormentaPsionica(unaCelda);
		default:
			break;
		}

		if(! ataqueRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
	
	
	
	private boolean atacarConTormentaPsionica(Celda unaCelda) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean atacarConAlucinacion(Celda unaCelda) {
		// TODO Auto-generated method stub
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

	public int getRangoAtaque(Unidad unidad) {
		return 0;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 0;
	}

	public int obtenerOcupacionSuministro() {
		return 2;
	}
}
