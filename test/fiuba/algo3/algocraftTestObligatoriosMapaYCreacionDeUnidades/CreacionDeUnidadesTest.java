package fiuba.algo3.algocraftTestObligatoriosMapaYCreacionDeUnidades;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.terran.Barraca;
import fiuba.algo3.algocraft.modelo.construciones.terran.Fabrica;
import fiuba.algo3.algocraft.modelo.construciones.terran.PuertoEstelarTerran;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeGasInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CantidadDeMineralInsuficienteException;
import fiuba.algo3.algocraft.modelo.excepciones.CapacidadDePoblacionMaximaSuperada;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException;
import fiuba.algo3.algocraft.modelo.excepciones.ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException;
import fiuba.algo3.algocraft.modelo.excepciones.JugadorConNombreDemasiadoCortoException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory;
import fiuba.algo3.algocraft.modelo.unidades.NaveTransporte;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.UnidadFactoryProtoss;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.terran.Espectro;
import fiuba.algo3.algocraft.modelo.unidades.terran.Golliat;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Dragon;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocrafttest.TestBase;

@SuppressWarnings("static-access")
public class CreacionDeUnidadesTest extends TestBase{
	
	public void testCrearUnidadDragon() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		//AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();

		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaConstruccion);
		
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre2);
		
		unTurno.addObserver(unaUnidad);

		for (int i=0;i<8;i++) unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof Dragon);
	}
	
	public void testCrearUnidadZealot() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaProtoss unaRaza = new RazaProtoss(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		//AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		unTurno.addObserver(unaConstruccion);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre1);
		
		unTurno.addObserver(unaUnidad);

		for (int i=0;i<7;i++) unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof Zealot);

	}
	
	public void testCrearUnidadGolliat() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		//AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaFabrica.crearUnidad(unJugador,TipoUnidad.terrestre2);
		
		unTurno.addObserver(unaUnidad);
		for (int i=0;i<7;i++) unTurno.avanzarTurno();
		
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof Golliat);

	}

	public void testCrearUnidadEspectro() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, NoHaySuficientesRecursos, JugadorConNombreDemasiadoCortoException, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		//AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();

		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.volador1);
		
		unTurno.addObserver(unaUnidad);
		for (int i=0;i<8;i++) unTurno.avanzarTurno();
		
		assertTrue(unaUnidad.estaOperativa());
		
		assertTrue(unaUnidad instanceof Espectro);

	}

	public void testCrearUnidadNaveCiencia() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		//AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();

		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.especial1);
		
		unTurno.addObserver(unaUnidad);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof NaveCiencia);

	}
	
	public void testCrearUnidadNaveTransporte() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		Fabrica unaFabrica = (Fabrica) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), unJugador);
		
		unTurno.addObserver(unaFabrica);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();
		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(25,25), unJugador);
		
		unTurno.addObserver(unPuerto);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unPuerto.crearUnidad(unJugador,TipoUnidad.volador2);
		
		unTurno.addObserver(unaUnidad);
		for (int i=0;i<8;i++) unTurno.avanzarTurno();
		
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof NaveTransporte);
	}

	public static AbstractUnidadFactory getFactoryUnidades(){
	    
		return new UnidadFactoryProtoss();
	}

	@SuppressWarnings("unused")
	public void testCrearUnaUnidadSinPoblacionDisponibleSeEsperaExcepcion() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		
		Barraca unaBarraca = (Barraca) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaBarraca);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		

		try {
			Unidad unaUnidad = (Unidad) unaBarraca.crearUnidad(unJugador,TipoUnidad.terrestre1);
		} catch (CapacidadDePoblacionMaximaSuperada e) {
			return;
		}
		fail();
	}
	
	public void testCrearUnaUnidadConPoblacionDisponible() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		//AbstractUnidadFactory factoryUnidades = getFactoryUnidades();
		//TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",new RazaProtoss(),"Azul");
		
		RazaProtoss unaRaza = new RazaProtoss(); 
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",new RazaProtoss(),"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor3 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(130,130), unJugador);
		Construccion expansor4 = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor3);
		unTurno.addObserver(expansor4);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		Acceso unaConstruccion = (Acceso) factoryConstrucciones.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), unJugador);
		
		unTurno.addObserver(unaConstruccion);
		
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unaUnidad = (Unidad) unaConstruccion.crearUnidad(unJugador,TipoUnidad.terrestre2);
		
		unTurno.addObserver(unaUnidad);

		for (int i=0;i<8;i++) unTurno.avanzarTurno();
		assertTrue(unaUnidad.estaOperativa());
		assertTrue(unaUnidad instanceof Dragon);
	}
	/*
	public void testCrecimientoYDecrecimientoDePoblacion() throws NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, JugadorConNombreDemasiadoCortoException, NoHaySuficientesRecursos, CapacidadDePoblacionMaximaSuperada, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoSePudoConstruirException{	
		RazaTerran unaRaza = new RazaTerran(); 
		RazaProtoss otraRaza = new RazaProtoss();
		TipoUnidad unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		AbstractUnidadFactory factoryUnidades = unaRaza.getFactoryUnidades();
		AbstractUnidadFactory otrafactoryUnidades = otraRaza.getFactoryUnidades();
		
		TipoConstruccion unTipoConstruccion = null;
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		AbstractConstruccionFactory factoryConstrucciones = unaRaza.getFactoryConstrucciones();
		AbstractConstruccionFactory otrafactoryConstrucciones = otraRaza.getFactoryConstrucciones();
		Jugador otroJugador = new Jugador("Nombre",otraRaza,"Azul");
		Turno unTurno = new Turno(unJugador,otroJugador);
		Construccion unExpansor = (Construccion) factoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,124), unJugador);
		Construccion expansor2 = (Construccion) otrafactoryConstrucciones.crearConstruccion(unTipoConstruccion.expansorPoblacion, new Posicion(124,126), otroJugador);
		unTurno.addObserver(unExpansor);
		unTurno.addObserver(expansor2);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		Unidad unidadJugador1 = (Unidad) factoryUnidades.crearUnidad(unTipo.terrestre1,unJugador);
		Unidad unidadJugador2 = (Unidad) otrafactoryUnidades.crearUnidad(unTipo.terrestre1,otroJugador);
		
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
		assertEquals(1, unJugador.dameCantidadPoblacion());
		assertEquals(2, otroJugador.dameCantidadPoblacion());


		assertEquals(40, unidadJugador1.obtenerCantidadVida());
		unidadJugador2.atacar(unidadJugador1.dameCelda());
		assertEquals(20, unidadJugador1.obtenerCantidadVida());
	
		unTurno.avanzarTurno();
		
		unidadJugador2.atacar(unidadJugador1.dameCelda());
		assertEquals(0, unidadJugador1.obtenerCantidadVida());

		assertFalse(unidadJugador1.estaViva());
		
		assertEquals(0, unJugador.dameLimiteDePoblacion());
	}*/
}


	
	
