package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.vista.Dibujable;

public abstract class Celda implements Dibujable {
	private Posicion posicion;
	private boolean estaOcupada;
	private Unidad unidad;
	protected Construccion construccion;
	
	public Celda(int fila, int columna) {
		Posicion unaPosicion = new Posicion(fila,columna);
		this.posicion = unaPosicion;
		this.unidad = null;
	}

	
	public Posicion obtenerPosicion(){
		return this.posicion;  
	}
	
	public boolean celdaOcupada(){
		return (this.estaOcupada);  
	}
	
	public void ocuparCelda(){
		this.estaOcupada = true;  
	}

	public void desocuparCelda(){
		this.unidad = null;
		this.construccion = null;
		this.estaOcupada = false;  
	}
	
	public boolean tieneGas(){
		return false;
	}

	public boolean tieneMineral(){
		return false;
	}


	public boolean agregarUnidad(Unidad unaUnidad) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if (!this.puedeMoverse(unaUnidad) || !unaUnidad.estaOperativa()) {
			return false;
		}
		
		// Esta ocupada por una construccion
		if (this.unidad == null && this.celdaOcupada()){
			return false;
		}
		
		if (this.unidad != null) {
			// Tiene una unidad
			if (this.unidad.esUnaNaveTransporte()){
				return (this.agregarUnidadANaveDeTransporte(unaUnidad));
			}
			else{
				return false;
			}
		}
		// No tiene nada
		else{
			return (this.agregarUnidadNormal(unaUnidad));
		}

	}
	
	private boolean agregarUnidadANaveDeTransporte(Unidad unaUnidad) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		NaveTransporte unaNaveTransporte = (NaveTransporte) this.unidad;
		unaNaveTransporte.cargarUnidad(unaUnidad);
		unaUnidad.dameCelda().desocuparCelda();
		unaUnidad.setCelda(this);
		return true;
	}


	private boolean agregarUnidadNormal(Unidad unaUnidad) {
		this.unidad = unaUnidad;
		this.unidad.setCelda(this);
		this.ocuparCelda();
		
		return true;
	}


	public void eliminarUnidad() {
		this.unidad = null;
		this.desocuparCelda();
	}


	abstract public boolean puedeMoverse(Unidad unaUnidad);


	abstract public boolean esAtacable();
	
	abstract public boolean esPosbibleConstruir(Construccion construccion);
	abstract public boolean agregarConstruccion(Construccion construccion);

	public boolean atacarUnidadDeLaCeldaConUnidad(Unidad unaUnidadAtacante) {
		if(!this.esAtacable())
			return false;
		
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		if(this.unidad.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		return this.unidad.recibirataque(unaUnidadAtacante);
	}

	public boolean atacarConstruccionDeLaCeldaConUnidad(Unidad unaUnidadAtacante) {
		if(!this.esAtacable())
			return false;
		
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		if(this.construccion.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		return this.construccion.recibirataque(unaUnidadAtacante);
	}

	private boolean celdaFueraDelRangoDeAtaqueEnemigo(Unidad unaUnidadAtacante) {
		int rango = unaUnidadAtacante.getRangoAtaque(this.unidad);
		int posicionXUnidadAtacante = unaUnidadAtacante.dameCelda().obtenerPosicion().dameColumna();
		int posicionYUnidadAtacante = unaUnidadAtacante.dameCelda().obtenerPosicion().dameFila();
		
		// esta a la derecha del rango
		if (this.posicion.dameColumna() > (posicionXUnidadAtacante + rango)) return true;
		
		// esta a la izquierda del rango
		if (this.posicion.dameColumna() < (posicionXUnidadAtacante - rango)) return true;
		
		// esta por encima del rango
		if (this.posicion.dameFila() > (posicionYUnidadAtacante + rango)) return true;
		
		// esta por debajo del rango
		if (this.posicion.dameFila() < (posicionYUnidadAtacante - rango)) return true;
		
		return false;
	}

	public boolean tieneUnidad() {
		return (this.unidad != null);
	}
	
	public boolean tieneConstruccion() {
		return (this.construccion != null);
	}

	public boolean atacarUnidadDelaCeldaConAlucionacion(Unidad unaUnidadAtacante) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if(!this.esAtacable())
			return false;
		
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;

		if(!this.unidad.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		Mapa mapaDelJuego = Mapa.getInstance();
		
		Celda celdaAlucionacionIzquierada = mapaDelJuego.dameCelda(new Posicion(this.obtenerPosicion().dameFila(),this.obtenerPosicion().dameColumna()-1)); 
		Celda celdaAlucionacionDerecha = mapaDelJuego.dameCelda(new Posicion(this.obtenerPosicion().dameFila(),this.obtenerPosicion().dameColumna()+1));
		
		while (celdaAlucionacionIzquierada.estaOcupada){
			celdaAlucionacionIzquierada = mapaDelJuego.dameCelda(new Posicion(celdaAlucionacionIzquierada.obtenerPosicion().dameFila(), celdaAlucionacionIzquierada.obtenerPosicion().dameColumna()-1));
		}
		
		while (celdaAlucionacionDerecha.estaOcupada){
			celdaAlucionacionDerecha = mapaDelJuego.dameCelda(new Posicion(celdaAlucionacionDerecha.obtenerPosicion().dameFila(), celdaAlucionacionDerecha.obtenerPosicion().dameColumna()+1));
		}

		Unidad alucionacionIzquierda = this.unidad.crearAlucinacion();
		Unidad alucionacionDerecha = this.unidad.crearAlucinacion();
		celdaAlucionacionIzquierada.agregarUnidad(alucionacionIzquierda);
		celdaAlucionacionDerecha.agregarUnidad(alucionacionDerecha);
		
		return true;
	}

	public boolean atacarRadioDelaCeldaConTormentaPsionica(Unidad unaUnidadAtacante) {
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;

		Mapa mapaDelJuego = Mapa.getInstance();
		for (int i = (this.posicion.dameFila()-1); i <=(this.posicion.dameFila()+1);i++){
			for (int j = (this.posicion.dameColumna()-1); j <=(this.posicion.dameColumna()+1);j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i,j));
				if (unaCelda.esAtacable()){
					unaCelda.atacarCeldaConTormentaPsionica();
				}
			}
		}
		return true;
		
	}

	
	public boolean atacarRadioDeLaCeldaConMisilEMP(Unidad unaUnidadAtacante) {
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		Mapa mapaDelJuego = Mapa.getInstance();
		for (int i = (this.posicion.dameFila()-1); i <=(this.posicion.dameFila()+1);i++){
			for (int j = (this.posicion.dameColumna()-1); j <=(this.posicion.dameColumna()+1);j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i,j));
				if (unaCelda.esAtacable()){
					unaCelda.atacarCeldaConMisilEMP();
				}
			}
		}
		
		return true;
	}
	
	private void atacarCeldaConTormentaPsionica() {
		if (this.unidad != null){
			this.unidad.recibirAtaqueTormentaPsionica();
		}
		
		if (this.construccion != null){
			this.construccion.atacarConTormentaPsionica();
		}
		
	}

	private void atacarCeldaConMisilEMP() {
		if (this.unidad != null){
			this.unidad.recibirataqueMisilEMP();
		}
	}

	public Unidad obtenerUnidad() {
		return this.unidad;
	}

	public boolean atacarRadioDeLaCeldaConRadiacion(Unidad unaUnidadAtacante) {
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		Mapa mapaDelJuego = Mapa.getInstance();
		for (int i = (this.posicion.dameFila()-1); i <=(this.posicion.dameFila()+1);i++){
			for (int j = (this.posicion.dameColumna()-1); j <=(this.posicion.dameColumna()+1);j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i,j));
				if (unaCelda.esAtacable()){
					unaCelda.atacarCeldaConRadiacion();
				}
			}
		}
		
		return true;
	}
	
	private void atacarCeldaConRadiacion() {
		if (this.unidad != null){
			this.unidad.recibirAtaqueRadiacion();
		}
	}


	public void bajarDeLaNaveDeTransporte(Unidad unaUnidad) {
		Mapa mapaDelJuego = Mapa.getInstance();
		boolean unidadBajada = false;
		
		while(!unidadBajada){
			Celda nuevaCelda = mapaDelJuego.dameCelda(new Posicion(this.posicion.dameFila()+1,this.posicion.dameColumna()));
			if (nuevaCelda.estaOcupada || !nuevaCelda.esCeldaTerrestre()){
				nuevaCelda = mapaDelJuego.dameCelda(new Posicion(nuevaCelda.posicion.dameFila()+1,nuevaCelda.posicion.dameColumna()));				
			}
			else{
				unaUnidad.setCelda(nuevaCelda);
				unidadBajada = true;
			}
		}
	}


	protected boolean esCeldaTerrestre() {
		return false;
	}
	
	public Posicion getPosicionActual() {
		return this.obtenerPosicion();
	}
}
