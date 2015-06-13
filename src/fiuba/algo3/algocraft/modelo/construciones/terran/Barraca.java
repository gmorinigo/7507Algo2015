package fiuba.algo3.algocraft.modelo.construciones.terran;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.ConstruccionEstadoTrabajando;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Barraca extends Construccion {
	
	public Barraca(Posicion unaPosicion, Jugador jugador){
		super(unaPosicion, jugador);
	}

	
	public Unidad crearUnidad() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException {
		// Obtener dinamicamente la factory
		AbstractUnidadFactory factoryUnidades = this.jugador.dameRaza().getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(TipoUnidad.terrestre1);
		this.estado = new ConstruccionEstadoTrabajando(unaUnidad.turnosNecesariosParaCreacion(), this);
		
		return unaUnidad;
	}

	@Override
	public int costoGas() {
		return 0;
	}

	@Override
	public int costoMineral() {
		return 150;
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		return 12;
	}


	@Override
	protected void vivir() {
		
	}
		
	
}
