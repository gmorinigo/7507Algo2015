package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoDescansando;
import fiuba.algo3.algocraft.modelo.unidades.UnidadEstadoViviendo;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoAlucinacion;
import fiuba.algo3.algocraft.modelo.unidades.ataques.DisparoTormentaPsionica;

public class AltoTemplario extends UnidadProtoss {
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

	
	public boolean atacar(Celda unaCelda, TipoAtaqueAltoTemplario unTipoAtaqueAltoTemplario) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if (!this.estado.esPosibleRealizarAccion()) {
			return false;
		}
		
		boolean ataqueRealizado = false;
				
		switch (unTipoAtaqueAltoTemplario){
		case Alucinacion:
			ataqueRealizado = this.atacarConAlucinacion(unaCelda);
			if (ataqueRealizado) this.restarEnergia(100);
			break;
		case TormentaPsionica:
			ataqueRealizado = this.atacarConTormentaPsionica(unaCelda);
			if (ataqueRealizado) this.restarEnergia(75);
			break;
		default:
			break;
		}

		if(! ataqueRealizado)
			return false;
		
		this.estado = new UnidadEstadoDescansando(this);
		return true;
	}
	
	
	
	private void restarEnergia(int cantidadEnergiaARestar) {
		if ((this.energia - cantidadEnergiaARestar) < 0){
			this.energia = 0;
		}
		else{
			this.energia -= cantidadEnergiaARestar;
		}
		
	}

	private boolean atacarConTormentaPsionica(Celda unaCelda) {
		if (!this.cantidadMagiaDisponible(TipoAtaqueAltoTemplario.TormentaPsionica)){
			return false;
		}
		boolean retornoAtaqueTormentaPsionica = false;
		DisparoTormentaPsionica unaTormentaPsionica = new DisparoTormentaPsionica(this,1,unaCelda);
		retornoAtaqueTormentaPsionica = unaTormentaPsionica.disparar(unaCelda);
		if (retornoAtaqueTormentaPsionica){
			Turno unTurno = Turno.getInstance();
			unTurno.addObserver(unaTormentaPsionica);
		}
		return retornoAtaqueTormentaPsionica;
	}
	
	private boolean atacarConAlucinacion(Celda unaCelda) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if (!this.cantidadMagiaDisponible(TipoAtaqueAltoTemplario.Alucinacion)){
			return false;
		}
		DisparoAlucinacion unDisparoAlucinacion = new DisparoAlucinacion(this,2);
		return (unDisparoAlucinacion.disparar(unaCelda));
	}


	private boolean cantidadMagiaDisponible(TipoAtaqueAltoTemplario tipoAtaque) {
		switch(tipoAtaque){
		case Alucinacion:
			if (this.energia >= 100) return true;
			break;
		case TormentaPsionica:
			if (this.energia >= 75) return true;
			break;
		default:
			break;
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

	public int getRangoAtaque(Unidad unidad) {
		return 5;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 5;
	}

	public int obtenerOcupacionSuministro() {
		return 2;
	}

	public Unidad crearAlucinacion() {
		AltoTemplario unaUnidad = (AltoTemplario) new AltoTemplario(this.jugador);
		unaUnidad.marcarEstaUnidadComoAlucinacion();
		unaUnidad.estado = new UnidadEstadoViviendo(this);
		return (Unidad)unaUnidad;
	}

	public int obtenerCantidadEnergia() {
		return this.energia;
	}
}
