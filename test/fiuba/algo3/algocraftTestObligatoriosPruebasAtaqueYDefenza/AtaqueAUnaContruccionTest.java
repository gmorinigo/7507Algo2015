package fiuba.algo3.algocraftTestObligatoriosPruebasAtaqueYDefenza;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.JugadorEstado.EstadoDelJugador;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Celda;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocrafttest.TestBase;

public class AtaqueAUnaContruccionTest extends TestBase{
	
	public void testDestruirUnaContruccionTerran() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada {
		
		Jugador jug1 = new Jugador("pepeeee", new RazaProtoss(), "salmon");
		Jugador jug2 = new Jugador("juannnn", new RazaTerran(), "salmon");
		Mapa mapa = Mapa.getInstance();
		
		Turno turno = new Turno(jug1, jug2);
		
		Acceso c1 = (Acceso) jug1.dameRaza().getFactoryConstrucciones().crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(10,10), jug1);
		Construccion pilon1 = jug1.dameRaza().getFactoryConstrucciones().crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(15,10), jug1);
		
		Construccion c2 = jug2.dameRaza().getFactoryConstrucciones().crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), jug2);
		
		for (int i = 0; i < 20; i++) {
			turno.avanzarTurno();
		}
		
		assertTrue(c1.estaOperativa());
		assertTrue(pilon1.estaOperativa());
		assertTrue(c2.estaOperativa());
		
		turno.comenzar();
		
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(10,10)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(11,10)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(11,11)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(10,11)));
		
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(15,15)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(16,15)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(15,16)));
		assertTrue(mapa.verificarCeldaOcupada(new Posicion(16,16)));
		
		UnidadProtoss unidad = (UnidadProtoss) c1.crearUnidad(jug1, TipoUnidad.terrestre1);
		turno.addObserver(unidad);
		
		for (int i = 0; i < 20; i++) {
			turno.avanzarTurno();
		}
		
		assertTrue(unidad.estaOperativa());
		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(10,9)));
		
		//11 9
		unidad.mover(TipoDireccion.Abajo);
		turno.avanzarTurno();
		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(11,9)));
		
		//12 9
		unidad.mover(TipoDireccion.Abajo);
		turno.avanzarTurno();
		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(12,9)));
		
		//12 10
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();
		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(12,10)));
		
		//12 11
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();
		
		//12 12
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();
		
		//12 13
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();

		//12 14
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();
		
		//12 15
		unidad.mover(TipoDireccion.Derecha);
		turno.avanzarTurno();

		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(12,15)));

		//13 15
		unidad.mover(TipoDireccion.Abajo);
		turno.avanzarTurno();
		//14 15
		unidad.mover(TipoDireccion.Abajo);
		turno.avanzarTurno();		
		assertTrue(unidad.dameCelda().obtenerPosicion().compararPosicion(new Posicion(14,15)));
		
		Celda celdaObjetivo = Mapa.getInstance().dameCelda(new Posicion(15,15));
		
		assertTrue(jug2.dameEstadoActual() == EstadoDelJugador.Jugando);
		
		for (int i = 0; i < 130; i++) {
			unidad.atacar(celdaObjetivo);
			turno.avanzarTurno();
		}
		
		assertTrue(jug1.dameEstadoActual() == EstadoDelJugador.Ganador);
		assertTrue(jug2.dameEstadoActual() == EstadoDelJugador.Perdedor);
		
	}

}
