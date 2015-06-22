package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeMineral;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.terran.SaludTerran;

public class CentroMineral extends ExtractorDeMineral{
	
	public CentroMineral(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	protected int turnosNecesariosParaCreacion() {
		return 4;
	}

	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	protected Salud saludInicial() {
		return new SaludTerran(500);
	}
}
