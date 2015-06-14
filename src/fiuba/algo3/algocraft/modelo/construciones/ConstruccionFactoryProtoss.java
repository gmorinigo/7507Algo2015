package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Asimilador;
import fiuba.algo3.algocraft.modelo.construciones.protoss.NexoMineral;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Pilon;
import fiuba.algo3.algocraft.modelo.construciones.protoss.PuertoEstelarProtoss;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;



public class ConstruccionFactoryProtoss extends AbstractConstruccionFactory {
	public Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);
		
		this.verificarConstruccionesParaPoderCrear(tipo, unJugador);
		
		switch(tipo){
		case creadorUnidadesBasicas: return new Acceso(unaPosicion, unJugador, tipo);
		case creadorUnidadesNivel2: return new ArchivosTemplarios(unaPosicion, unJugador, tipo);
		case creadorUnidadesVoladoras: return new PuertoEstelarProtoss(unaPosicion, unJugador, tipo); 
		case expansorPoblacion: return new Pilon(unaPosicion, unJugador, tipo);
		case extractorGas: return new Asimilador(unaPosicion, unJugador, tipo);
		case extractorMineral: return new NexoMineral(unaPosicion, unJugador, tipo);
		default: throw new NoSuchObjectException("Objeto Sin Tipo");
		}
	}

	protected int dameCostoMineral(TipoConstruccion tipo) {
		switch(tipo){
		case creadorUnidadesBasicas: return 150;
		case creadorUnidadesNivel2: return 150;
		case creadorUnidadesVoladoras: return 150; 
		case expansorPoblacion: return 100;
		case extractorGas: return 100;
		case extractorMineral: return 50;
		default: return 0;
		}
	}

	protected int dameCostoGas(TipoConstruccion tipo) {
		switch(tipo){
		case creadorUnidadesBasicas: return 0;
		case creadorUnidadesNivel2: return 200;
		case creadorUnidadesVoladoras: return 150; 
		case expansorPoblacion: return 0;
		case extractorGas: return 0;
		case extractorMineral: return 0;
		default: return 0;
		}
	}

}
