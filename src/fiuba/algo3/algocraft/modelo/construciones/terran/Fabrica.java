package fiuba.algo3.algocraft.modelo.construciones.terran;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.ConstruccionEstadoTrabajando;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.SaludTerran;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class Fabrica extends Construccion {

	protected ArrayList<Unidad> unidadesEnProceso;
	protected ArrayList<Unidad> unidadesFinalizadas;
	
	public Fabrica(Posicion unaPosicion, Jugador jugador, TipoConstruccion unTipo) {
		super(unaPosicion, jugador, unTipo);
		this.unidadesEnProceso = new ArrayList<Unidad>();
		this.unidadesFinalizadas = new ArrayList<Unidad>();
	}
	
	
	public Unidad crearUnidad(Jugador unJugador, TipoUnidad unTipo) throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada {
		// Obtener dinamicamente la factory
		AbstractUnidadFactory factoryUnidades = this.jugador.dameRaza().getFactoryUnidades();
		Unidad unaUnidad = (Unidad) factoryUnidades.crearUnidad(unTipo, unJugador,this.damePosicionCeldaSupIzquierda());
		this.estado = new ConstruccionEstadoTrabajando(unaUnidad.turnosNecesariosParaCreacion(), this);
		
		return unaUnidad;
	}

	public boolean estaTrabajando() {
		return ! this.unidadesEnProceso.isEmpty(); 
	}

	public void finalizarUnidad(Unidad unidad) {
		this.unidadesEnProceso.remove(unidad);
		this.unidadesFinalizadas.add(unidad);
	}

	public Unidad dameUnidad() {
		Unidad unidad = this.unidadesFinalizadas.get(this.unidadesFinalizadas.size()-1);
		this.unidadesFinalizadas.remove(unidad);
		return unidad;
	}

	public boolean hayBarraca(){
		for(Construccion unaConstruccion: jugador.dameConstrucciones()){
			if (unaConstruccion instanceof Barraca) return true;
		}
		return false;
	}

	protected int turnosNecesariosParaCreacion() {
		return 12;
	}

	protected void vivir() {
	}

	protected Salud saludInicial() {
		return new SaludTerran(1250);
	}
	
	public String getNombreObjetoDibujable() {
		return "Fabrica";
	}
	
	public Image getImagen() {
		Image imagen = null;
		try {
			imagen =  ImageIO.read(new File((getClass().getResource("/fiuba/algo3/algocraft/resources/images/fabrica.png")).toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return imagen;
	}

	
}
