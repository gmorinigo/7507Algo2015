package fiuba.algo3.algocraft.modelo.unidades.terran;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;

public class Espectro extends UnidadTerran {

	
	public Espectro(Jugador unJugador){
		super(unJugador);
		this.tamanioTransporte = 0;
	}
	
	protected Salud saludInicial() {
		return new SaludTerran(120);
	}

	public boolean esUnidadAerea(){
		return true;
	}


	public int turnosNecesariosParaCreacion() {
		return 8;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	public int DanioAtaque(Unidad unaUnidadAtacada) {
		if (unaUnidadAtacada.esUnidadAerea()){
			return 20;
		}
		else {
			return 8;			
		}
	} 
	
	protected boolean atacaUnidadesAereas() {
		return true;
	} 
}
