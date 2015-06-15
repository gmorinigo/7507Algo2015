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
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;



public class ConstruccionFactoryProtoss extends AbstractConstruccionFactory {
	public Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);
		this.verificarConstruccionesParaPoderCrear(tipo, unJugador);
		this.consumirRecursosJugador(tipo, unJugador);
		Construccion unaConstruccion;

		switch(tipo){
		case creadorUnidadesBasicas: 
			unaConstruccion = new Acceso(unaPosicion, unJugador, tipo);
			break;
		case creadorUnidadesNivel2: 
			unaConstruccion = new ArchivosTemplarios(unaPosicion, unJugador, tipo);
			break;
		case creadorUnidadesVoladoras: 
			unaConstruccion = new PuertoEstelarProtoss(unaPosicion, unJugador, tipo);
			break;
		case expansorPoblacion: 
			unaConstruccion = new Pilon(unaPosicion, unJugador, tipo);
			break;
		case extractorGas: 
			unaConstruccion = new Asimilador(unaPosicion, unJugador, tipo);
			break;
		case extractorMineral: 
			unaConstruccion = new NexoMineral(unaPosicion, unJugador, tipo);
			break;
		default: throw new NoSuchObjectException("Objeto Sin Tipo");
		}
		unJugador.agregarConstruccion(unaConstruccion);
		return unaConstruccion;
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
