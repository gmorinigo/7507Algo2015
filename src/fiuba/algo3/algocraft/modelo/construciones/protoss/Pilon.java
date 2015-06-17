package fiuba.algo3.algocraft.modelo.construciones.protoss;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class Pilon extends Construccion{
	
	public Pilon(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		super(unaPosicion, jugador, unTipo);
	}

	protected int turnosNecesariosParaCreacion() {
		return 5;
	}

	@Override
	protected void vivir() {
		// TODO Auto-generated method stub
		
	}

	protected Salud saludInicial() {
		return new SaludProtoss(300,300);
	}


	@Override
	public boolean recibirataque(Unidad unaUnidadAtacante) {
		// TODO Auto-generated method stub
		return false;
	}
}
