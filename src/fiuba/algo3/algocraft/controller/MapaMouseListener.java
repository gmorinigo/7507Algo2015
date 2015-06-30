package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class MapaMouseListener extends MouseAdapter { 
	
	private VentanaMapa ventanaMapa;
	private AlgoCraft juego;

	
    public MapaMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego) {
    	this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
	}
    
    
	@SuppressWarnings("static-access")
	public void mouseReleased(MouseEvent arg0) {
		Mapa mapaDelJuego = Mapa.getInstance();
		Posicion posicionCeldaPresionada =  this.convertirPixAPosicionCelda(arg0.getX(),arg0.getY());
		Celda celdaPresionada = mapaDelJuego.dameCelda(posicionCeldaPresionada);
		
		JPopupMenu popupMenu = new JPopupMenu("Menu contextual");
		
		if (celdaPresionada.tieneMineral()){
			if (celdaPresionada.celdaOcupada()){
				JMenuItem mitemMineral = popupMenu.add(String.format("CeldaOcupada"));
			}
			else{
				JMenuItem mitemMineral = popupMenu.add(String.format("Crear extractorMineral"));
				mitemMineral.addMouseListener(new CeldaMineralMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
			}
		}
		
		if (celdaPresionada.tieneGas()){
			if (celdaPresionada.celdaOcupada()){
				JMenuItem mitemGas = popupMenu.add(String.format("CeldaOcupada"));
			}
			else{
				JMenuItem mitemGas = popupMenu.add(String.format("Crear extractorGas"));
				mitemGas.addMouseListener(new CeldaGasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
			}
		}	
		
		
		if (celdaPresionada.esCeldaAerea() && celdaPresionada.tieneUnidad()){
			JMenuItem mitemUnidadAerea = popupMenu.add(String.format("Tiene una unidad aerea"));
		}
		
		if (!celdaPresionada.tieneGas() && !celdaPresionada.tieneMineral() && !celdaPresionada.esCeldaAerea()){ 
			JMenuItem mitemPoblacion = popupMenu.add(String.format("Crear expansorPoblacion"));
			mitemPoblacion.addMouseListener(new ExpansorPoblacionMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
			JMenuItem mitemUnidadesBasicas = popupMenu.add(String.format("Crear creadorUnidadesBasicas"));
			mitemUnidadesBasicas.addMouseListener(new CreadorUnidadesBasicasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		}
		
		if(celdaPresionada.tieneConstruccion()){
			JMenuItem mitemUnidadesT1 = popupMenu.add(String.format("Crear unidad terrestre1"));
			mitemUnidadesT1.addMouseListener(new UnidadesTerrestres1MouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
			JMenuItem mitemUnidadesT2 = popupMenu.add(String.format("Crear unidad terrestre2"));
			mitemUnidadesT2.addMouseListener(new UnidadesTerrestres2MouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		}
		
		if(celdaPresionada.tieneUnidad()){
			JMenuItem mitemMovimientoArriba = popupMenu.add(String.format("Mover Arriba"));
			mitemMovimientoArriba.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.Arriba,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoAbajo = popupMenu.add(String.format("Mover Abajo"));
			mitemMovimientoAbajo.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.Abajo,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoDerecha = popupMenu.add(String.format("Mover Derecha"));
			mitemMovimientoDerecha.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.Derecha,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoIzquierda = popupMenu.add(String.format("Mover Izquierda"));
			mitemMovimientoIzquierda.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.Izquierda,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoDiagonalAbajoDerecha = popupMenu.add(String.format("Mover DiagonalAbajoDerecha"));
			mitemMovimientoDiagonalAbajoDerecha.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.DiagonalAbajoDerecha,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoDiagonalAbajoIzquierda = popupMenu.add(String.format("Mover DiagonalAbajoIzquierda"));
			mitemMovimientoDiagonalAbajoIzquierda.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.DiagonalAbajoIzquierda,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoDiagonalArribaDerecha = popupMenu.add(String.format("Mover DiagonalArribaDerecha"));
			mitemMovimientoDiagonalArribaDerecha.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.DiagonalArribaDerecha,celdaPresionada.obtenerUnidad()));
			JMenuItem mitemMovimientoDiagonalArribaIzquierda = popupMenu.add(String.format("Mover DiagonalArribaIzquierda"));
			mitemMovimientoDiagonalArribaIzquierda.addMouseListener(new MovimientoMouseListener(this.ventanaMapa,this.juego,posicionCeldaPresionada,TipoDireccion.DiagonalArribaIzquierda,celdaPresionada.obtenerUnidad()));
			
		}
		
		popupMenu.setEnabled(true);
		popupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			
	}
	
	public static Posicion convertirPixAPosicionCelda(int x, int y) {
		//Resto el origen de coordenadas y divido por la cantidad de pixeles
		return new Posicion(((y-40)/30),((x-5)/30));
	}

}
