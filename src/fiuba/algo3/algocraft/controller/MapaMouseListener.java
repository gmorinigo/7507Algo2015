package fiuba.algo3.algocraft.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import fiuba.algo3.algocraft.modelo.AlgoCraft;
import fiuba.algo3.algocraft.modelo.JugadorEstado.EstadoDelJugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario.TipoAtaqueAltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia.TipoAtaqueNaveCiencia;
import fiuba.algo3.algocraft.vista.ventanas.VentanaMapa;

public class MapaMouseListener extends MouseAdapter { 
	
	private VentanaMapa ventanaMapa;
	private AlgoCraft juego;
	private static boolean ataqueActivado = false;
	public static Unidad unidad;
	public static TipoAtaqueAltoTemplario tipoAtaqueAltoTemplario;
	public static TipoAtaqueNaveCiencia tipoAtaqueNaveCiencia;
	
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
					this.realizarAtaque(celdaPresionada);
				}
				if(celdaPresionada.celdaOcupada()){
					if(celdaPresionada.obtenerUnidad().getJugador().dameRaza().esRazaProtoss())
						JOptionPane.showMessageDialog(null,"escudo: "+ celdaPresionada.obtenerUnidad().obtenerCantidadEscudo());
					JOptionPane.showMessageDialog(null,"vida: "+ celdaPresionada.obtenerUnidad().obtenerCantidadVida());
				}
				return;
			}
		}

		// Celda con construccion enemiga
		if (celdaPresionada.tieneConstruccion()){
			if (!unTurno.obtenerJugadorConTurno().esConstruccionDelJugador(celdaPresionada.obtenerConstruccion())){
				if (ataqueActivado){
					System.out.println("entre atacar");
					unidad.atacar(celdaPresionada);
					this.desactivarAtaque();
				}
				if(celdaPresionada.celdaOcupada()){
				if(celdaPresionada.obtenerConstruccion().getJugador().dameRaza().esRazaProtoss())
					JOptionPane.showMessageDialog(null,"escudo: "+ celdaPresionada.obtenerConstruccion().obtenerCantidadEscudo());
				JOptionPane.showMessageDialog(null,"vida: "+ celdaPresionada.obtenerConstruccion().obtenerCantidadVida());		
				}
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
				if (!unTurno.obtenerJugadorConTurno().esConstruccionDelJugador(celdaPresionada.obtenerConstruccion())){
					if (ataqueActivado){
						unidad.atacar(celdaPresionada);
						this.desactivarAtaque();
					}
					else JOptionPane.showMessageDialog(null,"Celda Ocupada");
				}
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
				if (ataqueActivado){
					unidad.atacar(celdaPresionada);
					this.desactivarAtaque();
				}
				else JOptionPane.showMessageDialog(null,"Celda Ocupada");
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
			if (!celdaPresionada.obtenerUnidad().esUnAltoTemplario() && !celdaPresionada.obtenerUnidad().esUnaNaveCiencia() && !celdaPresionada.obtenerUnidad().esUnaNaveTransporte()){
				JMenuItem mitemAtacar = popupMenu.add(String.format("Atacar"));
				mitemAtacar.addMouseListener(new AtacarMouseListener(celdaPresionada.obtenerUnidad()));				
			}

			if (celdaPresionada.obtenerUnidad().esUnAltoTemplario()){
				JMenuItem mitemAtacarTormPsio = popupMenu.add(String.format("Atacar con Tormenta Psionica"));
				mitemAtacarTormPsio.addMouseListener(new AtacarConTormentaPsionicaMouseListener(celdaPresionada.obtenerUnidad()));
				
				JMenuItem mitemAtacarAlucinacion = popupMenu.add(String.format("Lanzar Alucinacion"));
				mitemAtacarAlucinacion.addMouseListener(new AtacarConAlucinacionMouseListener(celdaPresionada.obtenerUnidad()));
			}
			
			if (celdaPresionada.obtenerUnidad().esUnaNaveCiencia()){
				JMenuItem mitemAtacarEMP = popupMenu.add(String.format("Atacar con EMP"));
				mitemAtacarEMP.addMouseListener(new AtacarConEMPMouseController(celdaPresionada.obtenerUnidad()));
				
				JMenuItem mitemAtacarRadiacion = popupMenu.add(String.format("Atacar con Radiacion"));
				mitemAtacarRadiacion.addMouseListener(new AtacarConRadiacionMouseController(celdaPresionada.obtenerUnidad()));
			}
			
			if (celdaPresionada.obtenerUnidad().esUnaNaveTransporte()){
				NaveTransporte unaNaveTransporte = (NaveTransporte) celdaPresionada.obtenerUnidad();
				if (unaNaveTransporte.getCapacidadOcupada() > 0){ 
					JMenuItem mitemDescargarUnidades = popupMenu.add(String.format("Descargar Unidades"));
					mitemDescargarUnidades.addMouseListener(new DescargarUnidadesMouseListener(celdaPresionada.obtenerUnidad(), this.juego));
				}
			}
			
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


	private void realizarAtaque(Celda celdaPresionada) {
		if (unidad.esUnAltoTemplario()){
			AltoTemplario unAltoTemplario = (AltoTemplario) unidad;
			try {
				unAltoTemplario.atacar(celdaPresionada, tipoAtaqueAltoTemplario);
			} catch (
					MaximaCapacidadDeTransporteSuperadaException | NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora e) {
				e.printStackTrace();
			}
		}
		else {
			if (unidad.esUnaNaveCiencia()){
				NaveCiencia unaNaveCiencia = (NaveCiencia) unidad;
				unaNaveCiencia.atacar(celdaPresionada, tipoAtaqueNaveCiencia);
			}
			else{
				unidad.atacar(celdaPresionada);							
			}
		}
		this.juego.avisarObservers();	
		desactivarAtaque();
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
		JMenuItem mitemFabrica = popupMenu.add(String.format("Crear Fabrica (Creador Unidades Nivel 2)"));
		mitemFabrica.addMouseListener(new FabricaMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
		JMenuItem mitemUnidadesVoladoras = popupMenu.add(String.format("Crear Puerto Estelar (Creador unidades voladoras y Especiales)"));
		mitemUnidadesVoladoras.addMouseListener(new CreadorUnidadesVoladorasMouseListener(this.ventanaMapa, this.juego,posicionCeldaPresionada));
	}
	
	public static Posicion convertirPixAPosicionCelda(int x, int y) {
		//Resto el origen de coordenadas y divido por la cantidad de pixeles
		return new Posicion(((y-30)/30),((x-5)/30));
	}
	
	public static void activarAtaque(Unidad unaUnidad){
		ataqueActivado = true;
		unidad = unaUnidad;
	}
	
	public static void activarAtaqueAltoTemplario(Unidad unaUnidad, TipoAtaqueAltoTemplario unTipoAtaque){
		ataqueActivado = true;
		unidad = unaUnidad;
		tipoAtaqueAltoTemplario = unTipoAtaque;
	}
	
	public static void activarAtaqueNaveCiencia(Unidad unaUnidad, TipoAtaqueNaveCiencia unTipoAtaque){
		ataqueActivado = true;
		unidad = unaUnidad;
		tipoAtaqueNaveCiencia = unTipoAtaque;
	}
	
	public static void desactivarAtaque(){
		ataqueActivado = false;
		tipoAtaqueAltoTemplario = null;
		tipoAtaqueNaveCiencia = null;
	}

}