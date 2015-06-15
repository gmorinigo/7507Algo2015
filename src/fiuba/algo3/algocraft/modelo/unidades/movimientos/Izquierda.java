package fiuba.algo3.algocraft.modelo.unidades.movimientos;

import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.CeldaTerrestre;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Izquierda extends Movimiento{
	public void mover(Unidad unaUnidad) {
        Mapa mapa = Mapa.getInstance();
        Celda celda = unaUnidad.dameCelda();
        Posicion posActual = celda.obtenerPosicion();
        Posicion posNueva = new Posicion(posActual.dameFila(),posActual.dameColumna()-1);
        celda.eliminarUnidad();
        celda = new CeldaTerrestre(posActual.dameFila() ,posActual.dameColumna()-1);
        celda.agregarUnidad(unaUnidad);
        unaUnidad.mover(mapa.dameCelda(posNueva));
	}

}
