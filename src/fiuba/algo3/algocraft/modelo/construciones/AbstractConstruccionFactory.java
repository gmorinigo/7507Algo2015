package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

abstract public class AbstractConstruccionFactory {
	public enum TipoConstruccion{extractorMineral,extractorGas,expansorPoblacion,creadorUnidadesBasicas,creadorUnidadesNivel2,creadorUnidadesVoladoras}
	public abstract Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException;
	public abstract boolean puedeCrear(TipoConstruccion tipo, int cantMineral, int cantGas);

}
