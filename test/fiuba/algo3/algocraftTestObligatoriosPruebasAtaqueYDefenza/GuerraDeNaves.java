package fiuba.algo3.algocraftTestObligatoriosPruebasAtaqueYDefenza;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
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
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeRealizarAccionException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;
import fiuba.algo3.algocrafttest.TestBase;

public class GuerraDeNaves extends TestBase{

	@SuppressWarnings("null")
	public void testSeSituaUnidadProtossZealotYSeLeAplicaMisilEMP() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{
		
			Mapa mapa = Mapa.getInstance();
		
			RazaProtoss unaRaza = new RazaProtoss(); 
			TipoConstruccion unTipo = null;
			Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
			
			AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
			Posicion unaPosicion = new Posicion(12,3);
			Posicion otraPosicion = new Posicion(15,7);
			
			Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, unaPosicion, unJugador);
			
			Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
			
			Acceso otraConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(unTipo.creadorUnidadesBasicas, otraPosicion, otroJugador);
			
			Turno unTurno = new Turno(unJugador,otroJugador);

			
			unTurno.addObserver(unaConstruccion);
			unTurno.addObserver(otraConstruccion);
			
			for (int i = 0; i<12;i++) unTurno.avanzarTurno();
			assertTrue(unaConstruccion.estaOperativa());
			
			TipoConstruccion unTipoConstruccion = null;
			unJugador.dameAlmacenMineral().almacenarRecurso(1000);
			Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
			Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
			Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
			Construccion expansor5 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(138,138), otroJugador);
			unTurno.addObserver(unExpansor);
			unTurno.addObserver(expansor3);
			unTurno.addObserver(expansor4);
			unTurno.addObserver(expansor5);
			for (int i=0;i<6;i++) unTurno.avanzarTurno();
			
		/*	TipoUnidad unTipoUnidad = null;
			Unidad NaveCiencia = (UnidadTerran) unaConstruccion.crearUnidad(unJugador,unTipoUnidad.especial1);
			UnidadProtoss Zealot= (UnidadProtoss) otraConstruccion.crearUnidad(otroJugador,unTipoUnidad.terrestre1);
			
			unTurno.addObserver(Zealot);
			unTurno.addObserver(NaveCiencia);
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			unTurno.avanzarTurno();
			assertTrue(Zealot.estaOperativa());
			assertTrue(NaveCiencia.estaOperativa());
			
			mapa.agregarUnidad(new Posicion(4,4), Zealot);
			mapa.agregarUnidad(new Posicion(5,5), NaveCiencia);
			assertEquals(40, Zealot.obtenerCantidadVida());
			assertEquals(40,Zealot.obtenerCantidadVida());
			
			NaveCiencia.atacar(Zealot.dameCelda());
			
			assertEquals(40, Zealot.obtenerCantidadVida());
			assertEquals(40,Zealot.obtenerCantidadEscudo());		
*/
		

	}
}
