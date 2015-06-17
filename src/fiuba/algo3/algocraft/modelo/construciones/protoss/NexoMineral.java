package fiuba.algo3.algocraft.modelo.construciones.protoss;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class NexoMineral extends ExtractorDeMineral {

	public NexoMineral(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	protected int turnosNecesariosParaCreacion() {
		return 4;
	}
	
	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	protected Salud saludInicial() {
		return new SaludProtoss(250,250);
	}

	@Override
	public boolean recibirataque(Unidad unaUnidadAtacante) {
		// TODO Auto-generated method stub
		return false;
	}
}
