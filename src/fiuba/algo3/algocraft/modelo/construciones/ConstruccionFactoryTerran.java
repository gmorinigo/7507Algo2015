package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.DepositoDeSuministro;
import fiuba.algo3.algocraft.modelo.construciones.terran.Fabrica;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;

public class ConstruccionFactoryTerran extends AbstractConstruccionFactory {
	public Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException{
		this.verificarRecursosParaPoderCrear(tipo, unJugador);
		this.verificarConstruccionesParaPoderCrear(tipo, unJugador);
		
		Construccion unaConstruccion;
		
		switch(tipo){
		case creadorUnidadesBasicas: 
			unaConstruccion = new Barraca(unaPosicion, unJugador, tipo);
			break;
		case creadorUnidadesNivel2: 
			unaConstruccion = new Fabrica(unaPosicion, unJugador, tipo);
			break;
		case creadorUnidadesEspecialesYVoladoras: 
			unaConstruccion = new PuertoEstelarTerran(unaPosicion, unJugador, tipo);
			break;
		case expansorPoblacion: 
			unaConstruccion = new DepositoDeSuministro(unaPosicion, unJugador, tipo);
			break;
		case extractorGas: 
			unaConstruccion = new Refineria(unaPosicion, unJugador, tipo);
			break;
		case extractorMineral: 
			unaConstruccion = new CentroMineral(unaPosicion, unJugador, tipo);
			break;
		default: throw new NoSuchObjectException("Objeto Sin Tipo");
		}
		
		if(! Mapa.getInstance().agregarConstruccion(unaConstruccion))
			throw new NoSePudoConstruirException();
		
		this.consumirRecursosJugador(tipo, unJugador);
		unJugador.agregarConstruccion(unaConstruccion);
		Turno unTurno = Turno.getInstance();
		unTurno.addObserver(unaConstruccion);
		return unaConstruccion;
	}

	protected int dameCostoMineral(TipoConstruccion tipo) {
		switch(tipo){
		case creadorUnidadesBasicas: return 150;
		case creadorUnidadesNivel2: return 200;
		case creadorUnidadesEspecialesYVoladoras: return 150; 
		case expansorPoblacion: return 100;
		case extractorGas: return 100;
		case extractorMineral: return 50;
		default: return 0;
		}
	}

	protected int dameCostoGas(TipoConstruccion tipo) {
		switch(tipo){
		case creadorUnidadesBasicas: return 0;
		case creadorUnidadesNivel2: return 100;
		case creadorUnidadesEspecialesYVoladoras: return 100; 
		case expansorPoblacion: return 0;
		case extractorGas: return 0;
		case extractorMineral: return 0;
		default: return 0;
		}
	}

}