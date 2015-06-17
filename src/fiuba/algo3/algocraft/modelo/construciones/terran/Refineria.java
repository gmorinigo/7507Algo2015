package fiuba.algo3.algocraft.modelo.construciones.terran;

import fiuba.algo3.algocraft.modelo.Almacen;
import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.ExtractorDeGas;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Refineria extends ExtractorDeGas{
	
	public Refineria(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo){
		super(unaPosicion, jugador, unTipo);
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 6;
	}

	
	@Override
	public void recolectar(Almacen almacen) {
		almacen.almacenarRecurso(10);
	}

	@Override
	protected Salud saludInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recibirataque(Unidad unaUnidadAtacante) {
		// TODO Auto-generated method stub
		return false;
	}	
}