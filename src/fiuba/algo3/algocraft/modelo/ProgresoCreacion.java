package fiuba.algo3.algocraft.modelo;

public class ProgresoCreacion {
	
	protected Construible entidad;
	private int cantidadTurnosParaCreacion;
	private int cantidadTurnosAvanzados;
	
	public ProgresoCreacion(int cantidadTurnosParaCreacion, Construible entidad) {
		this.entidad = entidad;
		this.cantidadTurnosParaCreacion = cantidadTurnosParaCreacion;
		this.cantidadTurnosAvanzados = 0;
	}
	
	public void avanzarProgreso() {
		this.cantidadTurnosAvanzados ++ ;
		if(this.cantidadTurnosAvanzados == this.cantidadTurnosParaCreacion)
			this.entidad.finalizarCreacion();
	}
	
	
	public int dameProgreso() {
		return this.cantidadTurnosAvanzados;
	}
	
	public int damePorcentajeProgreso() {
		return this.cantidadTurnosAvanzados *100 / this.cantidadTurnosParaCreacion;
	}
}
