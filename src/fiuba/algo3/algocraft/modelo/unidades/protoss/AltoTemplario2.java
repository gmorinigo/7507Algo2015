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
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoAlucinacion;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoTormentaPsionica;

public class AltoTemplario2 extends UnidadProtoss {
	private int energia;    
	public enum TipoAtaqueAltoTemplario{TormentaPsionica, Alucinacion}
	
	public AltoTemplario2(Jugador unJugador) {
		super(unJugador);
		this.energia =  50;
		this.tamanioTransporte = 2;
	}
	
	public AltoTemplario2(Jugador unJugador,Posicion posicionConstruccion) {
		super(unJugador);
		this.tamanioTransporte = 2;
		this.energia =  50;
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
		if ((this.energia + 15) >= 200){
			this.energia = 200;
		}
		else{
			this.energia += 15;
		}
	}

	public int turnosNecesariosParaCreacion() {
		return 7;
	}

	public boolean atacar(Celda unaCelda) {
		return false;
	}

	
	public boolean atacar(Celda unaCelda, TipoAtaqueAltoTemplario unTipoAtaqueAltoTemplario) {
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean ataqueRealizado = false;
				
		switch (unTipoAtaqueAltoTemplario){
		case Alucinacion:
			ataqueRealizado = this.atacarConAlucinacion(unaCelda);
			break;
		case TormentaPsionica:
			ataqueRealizado = this.atacarConTormentaPsionica(unaCelda);
			break;
		default:
			break;
		}

		if(! ataqueRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
	
	
	
	private boolean atacarConTormentaPsionica(Celda unaCelda) {
		DisparoTormentaPsionica unaTormentaPsionica = new DisparoTormentaPsionica(this,2);
		return (unaTormentaPsionica.disparar(unaCelda));
	}

	private boolean atacarConAlucinacion(Celda unaCelda) {
		DisparoAlucinacion unDisparoAlucinacion = new DisparoAlucinacion(this,2);
		return (unDisparoAlucinacion.disparar(unaCelda));
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

	public Unidad crearAlucinacion() {
		AltoTemplario2 unaUnidad = (AltoTemplario2) new AltoTemplario2(this.jugador);
		unaUnidad.marcarEstaUnidadComoAlucinacion();
		unaUnidad.estado = new UnidadEstadoViviendo(this);
		return (Unidad)unaUnidad;
	}

	public int obtenerCantidadEnergia() {
		return this.energia;
	}
}
