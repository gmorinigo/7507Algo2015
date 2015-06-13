package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.CentroMineral;
import fiuba.algo3.algocraft.modelo.construciones.terran.DepositoDeSuministro;
import fiuba.algo3.algocraft.modelo.construciones.terran.Fabrica;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
import fiuba.algo3.algocraft.modelo.construciones.terran.Refineria;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;

public class ConstruccionFactoryTerran extends AbstractConstruccionFactory {
	public Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException{
		switch(tipo){
		case creadorUnidadesBasicas: return new Barraca(unaPosicion, unJugador);
		case creadorUnidadesNivel2: return new Fabrica(unaPosicion, unJugador);
		case creadorUnidadesVoladoras: return new PuertoEstelarTerran(unaPosicion, unJugador); 
		case expansorPoblacion: return new DepositoDeSuministro(unaPosicion, unJugador);
		case extractorGas: return new Refineria(unaPosicion, unJugador);
		case extractorMineral: return new CentroMineral(unaPosicion, unJugador);
		default: throw new NoSuchObjectException("Objeto Sin Tipo");
		
		}
	}

	@Override
	public boolean puedeCrear(TipoConstruccion tipo, int cantMineral, int cantGas) {

		return false;
	}
}