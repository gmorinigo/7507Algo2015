package fiuba.algo3.algocraft.modelo.construciones.protoss;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.DebeUtilizarElCreadorDeUnidadesConTipoComoParametroException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class PuertoEstelarProtoss extends Construccion{
	protected ArrayList<Unidad> unidadesEnProceso;
	protected ArrayList<Unidad> unidadesFinalizadas;
	
	public PuertoEstelarProtoss(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		super(unaPosicion, jugador, unTipo);
		this.unidadesEnProceso = new ArrayList<Unidad>();
		this.unidadesFinalizadas = new ArrayList<Unidad>();
	}
	
	public Unidad crearUnidad(Jugador unJugador) throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, DebeUtilizarElCreadorDeUnidadesConTipoComoParametroException {
		throw new DebeUtilizarElCreadorDeUnidadesConTipoComoParametroException();
	}
	
	public Unidad crearUnidad(Jugador unJugador, TipoUnidad unTipo) throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada {
		
		AbstractUnidadFactory factoryUnidades = this.jugador.dameRaza().getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo, unJugador,this.damePosicionCeldaSupIzquierda());
		
		return unaUnidad;
	}

	protected int turnosNecesariosParaCreacion() {
		return 10;
	}

	protected void vivir() {
	}

	protected Salud saludInicial() {
		return new SaludProtoss(600,600);
	}

	public String getNombreObjetoDibujable() {
		return "PuertoEstelarProtoss";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/puertoestelarprotoss.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}

}
