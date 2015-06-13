package fiuba.algo3.algocraft.modelo.unidades.terran;

import java.util.ArrayList;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.unidades.Salud;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;

public class NaveTransporte extends Unidad {
	private ArrayList<Unidad> unidadesCargadas;
	private int capacidadCargada;
	private int capacidadMaximaCarga;
	
	public NaveTransporte(){
		this.unidadesCargadas = new ArrayList<Unidad>();
		this.capacidadCargada = 0;
		this.tamanioTransporte = 0;
		this.capacidadMaximaCarga = 8;
		//this.disparoStrategy=disparoStrategy;
		//this.disparoStrategy.setUnidad(this);
	}
	
	@Override
	public void disparar() {}

	@Override
	protected Salud saludInicial() {
		return null;
	}
	
	public boolean esUnidadAerea(){
		return true;
	}

	public void cargarUnidad(Unidad unaUnidad) throws MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora{
		this.verificarSiEsUnaUnidadVoladora(unaUnidad);
		
		if ((capacidadMaximaCarga - capacidadCargada) >= unaUnidad.getTamanioTransporte()){
			unidadesCargadas.add(unaUnidad);
			this.capacidadCargada += unaUnidad.getTamanioTransporte(); 
		}
		else {
			throw new MaximaCapacidadDeTransporteSuperadaException();
		}
	}

	
	private void verificarSiEsUnaUnidadVoladora(Unidad unaUnidad) throws NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		if (unaUnidad.esUnidadAerea()){
			throw new NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora();
		}
		
		
	}

	public int getCapacidadOcupada(){
		
		return this.capacidadCargada;
	}

	@Override
	public void mover(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacar(int posicionX, int posicionY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int turnosNecesariosParaCreacion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
