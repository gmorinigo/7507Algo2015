package fiuba.algo3.algocraft.modelo.mapa;

import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public abstract class Celda {
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
		this.estaOcupada = false;  
	}
	
	public boolean tieneGas(){
		return false;
	}

	public boolean tieneMineral(){
		return false;
	}


	public boolean agregarUnidad(Unidad unaUnidad) {
		if (!this.puedeMoverse(unaUnidad) || this.celdaOcupada() || !unaUnidad.estaOperativa()) {
			return false;
		}
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

	public boolean atacarUnidadDeLaCeldaConUnidadConMisilEMP(Unidad unaUnidadAtacante) {
		if(!this.esAtacable())
			return false;
		
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		if(this.unidad.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		return this.unidad.recibirataqueMisilEMP(unaUnidadAtacante);
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

	public boolean atacarUnidadDelaCeldaConAlucionacion(Unidad unaUnidadAtacante) {
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
		for (int i = (this.posicion.dameFila()-2); i <=(this.posicion.dameFila()+2);i++){
			for (int j = (this.posicion.dameColumna()-2); j <=(this.posicion.dameColumna()+2);j++){
				Celda unaCelda = mapaDelJuego.dameCelda(new Posicion(i,j));
				if (unaCelda.esAtacable()){
					unaCelda.atacarCeldaConTormentaPsionica();
				}
			}
		}
		return true;
		
	}


	private void atacarCeldaConTormentaPsionica() {
		if (this.unidad != null){
			this.unidad.atacarConTormentaPsionica();
		}
		
		if (this.construccion != null){
			this.construccion.atacarConTormentaPsionica();
		}
		
	}


	public Unidad obtenerUnidad() {
		return this.unidad;
	}


	public boolean atacarUnidadDeLaCeldaConRadiacion(Unidad unaUnidadAtacante) {
		if(!this.esAtacable())
			return false;
		
		if (this.celdaFueraDelRangoDeAtaqueEnemigo(unaUnidadAtacante)) return false;
		
		if(this.unidad.sonUnidadesDelMismoJugador(unaUnidadAtacante)){
			return false;
		}
		
		return this.unidad.recibirAtaqueRadiacion(unaUnidadAtacante);
	}
}
