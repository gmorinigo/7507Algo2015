package fiuba.algo3.algocraft.modelo.unidades.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;

public class Dragon extends UnidadProtoss {

	public Dragon(Jugador unJugador) {
		super(unJugador);

		this.tamanioTransporte = 4;
	}
	
	protected Salud saludInicial() {
		return new SaludProtoss(100,80);
	}

	public int turnosNecesariosParaCreacion() {
		return 6;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		return 20;
	}
	
	public int DanioAtaque(Construccion construccion) {
		return 20;
	}
	
	protected boolean atacaUnidadesAereas() {
		return true;
	}

	public int getRangoAtaque(Unidad unidad) {
		return 4;
	}

	public int getRangoAtaque(Construccion construccion) {
		return 4;
	} 
	
	public int obtenerOcupacionSuministro() {
		return 2;
	}

}
