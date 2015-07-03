package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.JugadorEstado.EstadoDelJugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class MapaMouseListener extends MouseAdapter { 
	
	private VentanaMapa ventanaMapa;
	private AlgoCraft juego;
	private static boolean ataqueActivado = false;
	public static Unidad unidad;
	
    public MapaMouseListener(VentanaMapa ventanaMapa, AlgoCraft juego) {
    	this.ventanaMapa = ventanaMapa;
    	this.juego = juego;
	}
    
    
	@SuppressWarnings({ "static-access", "unused" })
	public void mouseReleased(MouseEvent arg0) {
		//System.out.println("x" + arg0.getX());
		//System.out.println("y" + arg0.getY());
		if (arg0.getX() < 5 || arg0.getX() > 605) return;
		if (arg0.getY() < 30 || arg0.getX() > 630) return;
		
		Mapa mapaDelJuego = Mapa.getInstance();
		Turno unTurno = Turno.getInstance();
		Posicion posicionCeldaPresionada =  this.convertirPixAPosicionCelda(arg0.getX(),arg0.getY());
		Celda celdaPresionada = mapaDelJuego.dameCelda(posicionCeldaPresionada);
		
		JPopupMenu popupMenu = new JPopupMenu("Menu contextual");

		if(juego.dameElJugadorDelTurno().dameEstadoActual() == EstadoDelJugador.Ganador){ 
			ventanaMapa.setVisible(false);
			JOptionPane.showMessageDialog(null,"Felicitaciones!! Ganador: " + juego.dameElJugadorDelTurno().dameNombre());
		}	
		
		// Celda con unidad enemiga
		if (celdaPresionada.tieneUnidad()){
			if (!unTurno.obtenerJugadorConTurno().esUnidadDelJugador(celdaPresionada.obtenerUnidad())){
				if (ataqueActivado){
					this.desactivarAtaque();
					unidad.atacar(celdaPresionada);
					this.juego.avisarObservers();
				}
				if(celdaPresionada.obtenerUnidad().getJugador().dameRaza().esRazaProtoss())
					JOptionPane.showMessageDialog(null,"escudo: "+ celdaPresionada.obtenerUnidad().obtenerCantidadEscudo());
				JOptionPane.showMessageDialog(null,"vida: "+ celdaPresionada.obtenerUnidad().obtenerCantidadVida());
				return;
			}
		}

		// Celda con construccion enemiga
		if (celdaPresionada.tieneConstruccion()){
			if (!unTurno.obtenerJugadorConTurno().esConstruccionDelJugador(celdaPresionada.obtenerConstruccion())){
				if (ataqueActivado){
					unidad.atacar(celdaPresionada);
					this.desactivarAtaque();
				}
				if(celdaPresionada.obtenerConstruccion().getJugador().dameRaza().esRazaProtoss())
					JOptionPane.showMessageDialog(null,"escudo: "+ celdaPresionada.obtenerConstruccion().obtenerCantidadEscudo());
				JOptionPane.showMessageDialog(null,"vida: "+ celdaPresionada.obtenerConstruccion().obtenerCantidadVida());				
				return;
			}
		}
		
		// Celda con construccion no finalizada
		if (celdaPresionada.tieneConstruccion()){
			if (!celdaPresionada.obtenerConstruccion().estaTerminada()){
				JOptionPane.showMessageDialog(null,"turnos faltantes: "+ celdaPresionada.obtenerConstruccion().dameTurnosFaltantesParaLaCreacion());
				return;
			}
		}
		
		// Celda con mineral con/sin construccion
		if (celdaPresionada.tieneMineral()){
			if (celdaPresionada.celdaOcupada()){
				JMenuItem mitemMineral = popupMenu.add(String.format("CeldaOcupada"));
			}
			else{
				if (unTurno.obtenerJugadorConTurno().dameRaza().esRazaProtoss()){
					JMenuItem mitemMineral = popupMenu.add(String.format("Crear Nexo Mineral (Extractor de Mineral)"));
					mitemMineral.addMouseListener(new CeldaMineralMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
				}
				else{
					JMenuItem mitemMineral = popupMenu.add(String.format("Crear Centro Mineral (Extractor de Mineral)"));
					mitemMineral.addMouseListener(new CeldaMineralMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));					
				}
			}
		}
		
		// Celda con gas con/sin construccion
		if (celdaPresionada.tieneGas()){
			if (celdaPresionada.celdaOcupada()){
				JMenuItem mitemGas = popupMenu.add(String.format("CeldaOcupada"));
			}
			else{
				if (unTurno.obtenerJugadorConTurno().dameRaza().esRazaProtoss()){
					JMenuItem mitemGas = popupMenu.add(String.format("Crear Asimilador (Extractor de Gas)"));
					mitemGas.addMouseListener(new CeldaGasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
				}
				else{
					JMenuItem mitemGas = popupMenu.add(String.format("Crear Refineria (Extractor de Gas)"));
					mitemGas.addMouseListener(new CeldaGasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
				}
			}
		}	
		
		//Celda aerea con unidad
		if (celdaPresionada.esCeldaAerea() && celdaPresionada.tieneUnidad()){
			JMenuItem mitemUnidadAerea = popupMenu.add(String.format("Tiene una unidad aerea"));
		}
		
		// Celda Libre
		if (!celdaPresionada.tieneGas() && !celdaPresionada.tieneMineral() && !celdaPresionada.esCeldaAerea() && !celdaPresionada.celdaOcupada()){ 
			if (unTurno.obtenerJugadorConTurno().dameRaza().esRazaProtoss()){
				this.mostrarOpcionesCeldaLibreProtoss(popupMenu, posicionCeldaPresionada);
			}
			else{
				this.mostrarOpcionesCeldaLibreTerran(popupMenu, posicionCeldaPresionada);
			}
		}
		
		// Celda con construccion que no es extractora
		if(celdaPresionada.tieneConstruccion() && !celdaPresionada.tieneGas() && !celdaPresionada.tieneMineral()){
			if(unTurno.obtenerJugadorConTurno().dameRaza().esRazaProtoss()){
				this.mostrarOpcionesCreacionDeUnidadesProtos(popupMenu,celdaPresionada,posicionCeldaPresionada);
			}
			else{
				this.mostrarOpcionesCreacionDeUnidadesTerran(popupMenu,celdaPresionada,posicionCeldaPresionada);
			}		
		}
		
		// Celda con unidad
		if(celdaPresionada.tieneUnidad()){
			JMenuItem mitemAtacar = popupMenu.add(String.format("Atacar"));
			mitemAtacar.addMouseListener(new AtacarMouseListener(celdaPresionada.obtenerUnidad()));
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


	private void mostrarOpcionesCreacionDeUnidadesTerran(JPopupMenu popupMenu,Celda celdaPresionada,Posicion posicionCeldaPresionada) {
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesBasicas)){
		JMenuItem mitemUnidadesT1 = popupMenu.add(String.format("Crear Marine"));
		mitemUnidadesT1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.terrestre1));
		}
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesNivel2)){
		JMenuItem mitemUnidadesT2 = popupMenu.add(String.format("Crear Golliat"));
		mitemUnidadesT2.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.terrestre2));
		}
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras)){
			JMenuItem mitemUnidadesv1 = popupMenu.add(String.format("Crear Espectro"));
			mitemUnidadesv1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.volador1));
			JMenuItem mitemUnidadesv2 = popupMenu.add(String.format("Crear Nave De Transporte"));
			mitemUnidadesv2.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.volador2));
			JMenuItem mitemUnidadese1 = popupMenu.add(String.format("Crear Nave De Ciencia"));
			mitemUnidadese1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.especial1));			
		}
	}


	private void mostrarOpcionesCreacionDeUnidadesProtos(JPopupMenu popupMenu,Celda celdaPresionada, Posicion posicionCeldaPresionada) {
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesBasicas)){
		JMenuItem mitemUnidadesT1 = popupMenu.add(String.format("Crear Zealot"));
		mitemUnidadesT1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.terrestre1));
		JMenuItem mitemUnidadesT2 = popupMenu.add(String.format("Crear Dragon"));
		mitemUnidadesT2.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.terrestre2));
		}
		
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesVoladoras)){
			JMenuItem mitemUnidadesv1 = popupMenu.add(String.format("Crear Scout"));
			mitemUnidadesv1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.volador1));
			JMenuItem mitemUnidadesv2 = popupMenu.add(String.format("Crear Nave De Transporte"));
			mitemUnidadesv2.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.volador2));
		}
		if(celdaPresionada.obtenerConstruccion().verificarTipoConstruccion(TipoConstruccion.creadorUnidadesEspeciales)){
			JMenuItem mitemUnidadese1 = popupMenu.add(String.format("Crear Alto Templario"));
			mitemUnidadese1.addMouseListener(new UnidadesMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada,TipoUnidad.especial1));			
		}
	}


	private void mostrarOpcionesCeldaLibreProtoss(JPopupMenu popupMenu, Posicion posicionCeldaPresionada){
		JMenuItem mitemPoblacion = popupMenu.add(String.format("Crear Pilon (Expansor Poblacion)"));
		mitemPoblacion.addMouseListener(new ExpansorPoblacionMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemUnidadesBasicas = popupMenu.add(String.format("Crear Acceso (Creador Unidades Basicas)"));
		mitemUnidadesBasicas.addMouseListener(new CreadorUnidadesBasicasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemUnidadesVoladoras = popupMenu.add(String.format("Crear Puerto Estelar (Creador unidades voladoras)"));
		mitemUnidadesVoladoras.addMouseListener(new CreadorUnidadesVoladorasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemArchivosTemplarios = popupMenu.add(String.format("Crear Archivos Templarios (Creador unidades Especiales)"));
		mitemArchivosTemplarios.addMouseListener(new ArchivosTemplariosMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
	}
	
	private void mostrarOpcionesCeldaLibreTerran(JPopupMenu popupMenu, Posicion posicionCeldaPresionada){	
		JMenuItem mitemPoblacion = popupMenu.add(String.format("Crear Deposito de Suministros (Expansor Poblacion)"));
		mitemPoblacion.addMouseListener(new ExpansorPoblacionMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemUnidadesBasicas = popupMenu.add(String.format("Crear Barraca (Creador Unidades Basicas)"));
		mitemUnidadesBasicas.addMouseListener(new CreadorUnidadesBasicasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemFabrica = popupMenu.add(String.format("Crear Fabrica (Creador unidades voladoras y Especiales)"));
		mitemFabrica.addMouseListener(new FabricaMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemUnidadesVoladoras = popupMenu.add(String.format("Crear Puerto Estelar (Creador unidades voladoras y Especiales)"));
		mitemUnidadesVoladoras.addMouseListener(new CreadorUnidadesVoladorasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
	}
	
	public static Posicion convertirPixAPosicionCelda(int x, int y) {
		//Resto el origen de coordenadas y divido por la cantidad de pixeles
		return new Posicion(((y-30)/30),((x-5)/30));
	}
	
	public static void activarAtaque(){
		ataqueActivado = true;
	}
	
	public static void desactivarAtaque(){
		ataqueActivado = false;
	}

}