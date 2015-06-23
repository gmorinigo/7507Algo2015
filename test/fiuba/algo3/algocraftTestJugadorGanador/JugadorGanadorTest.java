package fiuba.algo3.algocraftTestJugadorGanador;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.UnidadProtoss;
import fiuba.algo3.algocraft.modelo.unidades.UnidadTerran;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocrafttest.TestBase;

public class JugadorGanadorTest extends TestBase{

	public void testJugadorGanador()  throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada{
	    Mapa mapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss(); 
		RazaTerran otraRaza = new RazaTerran();
		
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		Jugador otroJugador = new Jugador("otroNombre",otraRaza,"Rojo");
		
		AbstractConstruccionFactory factoryConstruccionesP = unaRaza.getFactoryConstrucciones();
		AbstractConstruccionFactory factoryConstruccionesT = otraRaza.getFactoryConstrucciones();
		Posicion unaPosicion = new Posicion(12,3);
		Posicion otraPosicion = new Posicion(15,7);
		
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		Acceso unaConstruccion = (Acceso) factoryConstruccionesP.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, unaPosicion, unJugador);
		Barraca otraConstruccion = (Barraca) factoryConstruccionesT.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, otraPosicion, otroJugador);

		for (int i = 0; i<18;i++) unTurno.avanzarTurno();
		assertTrue(unaConstruccion.estaOperativa());
		
		unTurno.comenzar();
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		Construccion unExpansor = (Construccion) factoryConstruccionesP.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstruccionesP.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstruccionesT.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstruccionesT.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(138,138), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
	
		assertTrue(unExpansor.estaOperativa());
		assertTrue(expansor3.estaOperativa());
		assertTrue(expansor4.estaOperativa());
		assertTrue(expansor5.estaOperativa());
		
		assertTrue(unJugador.estaEnJuego());
		assertTrue(otroJugador.estaEnJuego());
		
		UnidadProtoss unaUnidad = (UnidadProtoss) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		UnidadTerran otraUnidad = (UnidadTerran) otraConstruccion.crearUnidad(otroJugador,TipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);
		unTurno.addObserver(otraUnidad);
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(otraUnidad.estaOperativa());

		System.out.println(otroJugador.dameCantidadPoblacion());
		
		mapa.agregarUnidad(new Posicion(4,4), unaUnidad);
		mapa.agregarUnidad(new Posicion(5,5), otraUnidad);
		
		while(otraUnidad.estaViva()) {
			unaUnidad.atacar(otraUnidad.dameCelda());
			unTurno.avanzarTurno();
		}
		
		System.out.println(otraUnidad.obtenerCantidadVida());
		System.out.println(otroJugador.dameCantidadPoblacion());
		
		assertTrue(mapa.agregarUnidad(new Posicion(16,6), unaUnidad));
		
		for (int i = 0; i < 10; i++) {
			unaUnidad.atacar(otraConstruccion.dameCeldas().get(0));
			unTurno.avanzarTurno();	
		}
		
		System.out.println(otraConstruccion.obtenerCantidadVida());
	}
		
}
