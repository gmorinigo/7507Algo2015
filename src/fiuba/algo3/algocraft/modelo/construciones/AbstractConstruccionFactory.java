package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

abstract public class AbstractConstruccionFactory {
	public enum TipoConstruccion{extractorMineral,extractorGas,expansorPoblacion,creadorUnidadesBasicas,creadorUnidadesNivel2,creadorUnidadesVoladoras}
	public abstract Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;

	protected void verificarRecursosParaPoderCrear(TipoConstruccion tipo, Jugador unJugador) 
	throws CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException {
		if (unJugador.dameCantidadMineral() < this.dameCostoMineral(tipo)){
			throw new CantidadDeMineralInsuficienteException();
		}
		if (unJugador.dameCantidadGas() < this.dameCostoGas(tipo)){
			throw new CantidadDeGasInsuficienteException();		
		}
		 
	}
	
	
	protected abstract int dameCostoMineral(TipoConstruccion tipo);
	protected abstract int dameCostoGas(TipoConstruccion tipo);
	
	protected void verificarConstruccionesParaPoderCrear(TipoConstruccion tipo, Jugador unJugador) throws ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException {
		TipoConstruccion construccionesRequeridas = dameConstruccionRequeridasParaConstruir(tipo); 
		if ( construccionesRequeridas != null ){
			unJugador.verificarConstruccionCreada(tipo);
		}
	}
	
	@SuppressWarnings("static-access")
	protected TipoConstruccion dameConstruccionRequeridasParaConstruir(TipoConstruccion tipo) {
		TipoConstruccion unTipoDeConstruccionRequerida = null;
		
		switch(tipo){
		case creadorUnidadesBasicas: return null; 
		case creadorUnidadesNivel2: return unTipoDeConstruccionRequerida.creadorUnidadesVoladoras;
		case creadorUnidadesVoladoras: return unTipoDeConstruccionRequerida.creadorUnidadesBasicas;
		case expansorPoblacion: return null;
		case extractorGas: return null; 
		case extractorMineral: return null;
		default: return null;
		}
	}
}
