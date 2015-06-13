package fiuba.algo3.algocraft.modelo.construciones;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Asimilador;
import fiuba.algo3.algocraft.modelo.construciones.protoss.NexoMineral;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Pilon;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;


public class ConstruccionFactoryProtoss extends AbstractConstruccionFactory {
	public Construccion crearConstruccion(TipoConstruccion tipo, Posicion unaPosicion, Jugador unJugador) throws NoSuchObjectException{
		switch(tipo){
		case creadorUnidadesBasicas: return new Acceso(unaPosicion, unJugador);
		case creadorUnidadesNivel2: return new ArchivosTemplarios(unaPosicion, unJugador);
		case creadorUnidadesVoladoras: return new PuertoEstelarTerran(unaPosicion, unJugador); 
		case expansorPoblacion: return new Pilon(unaPosicion, unJugador);
		case extractorGas: return new Asimilador(unaPosicion, unJugador);
		case extractorMineral: return new NexoMineral(unaPosicion, unJugador);
		default: throw new NoSuchObjectException("Objeto Sin Tipo");
		
		}
	}

	@Override
	public boolean puedeCrear(TipoConstruccion tipo, int cantMineral, int cantGas) {

		return false;
	}
}
