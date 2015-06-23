package fiuba.algo3.algocraftTestObligatoriosAtaqueYDefenza;

import java.rmi.NoSuchObjectException;

import fiuba.algo3.algocraft.modelo.Jugador;
import fiuba.algo3.algocraft.modelo.RazaProtoss;
import fiuba.algo3.algocraft.modelo.RazaTerran;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory;
import fiuba.algo3.algocraft.modelo.construciones.Construccion;
import fiuba.algo3.algocraft.modelo.construciones.AbstractConstruccionFactory.TipoConstruccion;
import fiuba.algo3.algocraft.modelo.construciones.protoss.Acceso;
import fiuba.algo3.algocraft.modelo.construciones.protoss.ArchivosTemplarios;
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
import fiuba.algo3.algocraft.modelo.excepciones.MaximaCapacidadDeTransporteSuperadaException;
import fiuba.algo3.algocraft.modelo.excepciones.NoHaySuficientesRecursos;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePudoConstruirException;
import fiuba.algo3.algocraft.modelo.excepciones.NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Posicion;
import fiuba.algo3.algocraft.modelo.turnos.Turno;
import fiuba.algo3.algocraft.modelo.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.unidades.AbstractUnidadFactory.TipoUnidad;
import fiuba.algo3.algocraft.modelo.unidades.movimientos.Movimiento.TipoDireccion;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.protoss.Zealot;
import fiuba.algo3.algocraft.modelo.unidades.protoss.AltoTemplario.TipoAtaqueAltoTemplario;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia;
import fiuba.algo3.algocraft.modelo.unidades.terran.NaveCiencia.TipoAtaqueNaveCiencia;
import fiuba.algo3.algocrafttest.TestBase;

@SuppressWarnings("static-access")
public class UnidadesMagicasEnConjuntoTest extends TestBase{
	@SuppressWarnings("unused")
	public void testAlucinacionYEMPSeVerificaQueLasAlucinacionesMuerenPorNoTenerVida() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss();
		RazaTerran razaTerran = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstruccionesProtoss = unaRaza.getFactoryConstrucciones();
		AbstractConstruccionFactory factoryConstruccionesTerran = razaTerran.getFactoryConstrucciones();
		Posicion posicionAcceso = new Posicion(20,7);
		Posicion posicionPuertoEstelar = new Posicion(15,7);
		Posicion posicionArchivosTemplarios = new Posicion(20,9);
		
		Jugador otroJugador = new Jugador("Nombre",razaTerran,"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicionExpPob = new Posicion(12,3);		
		factoryConstruccionesProtoss.crearConstruccion(unTipo.expansorPoblacion, posicionExpPob , unJugador);

		Acceso unAcceso = (Acceso) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAcceso, unJugador);

		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicionPuertoEstelar, unJugador);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesEspeciales, posicionArchivosTemplarios, unJugador);

		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		AltoTemplario unAltoTemplario = (AltoTemplario) unArchivoTemplario.crearUnidad(unJugador);

		TipoUnidad unTipoUnidad = TipoUnidad.terrestre1;
		Unidad unZealot = (Unidad) unAcceso.crearUnidad(unJugador, unTipoUnidad);
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();

		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
        assertEquals(unZealot.dameCelda(),unMapa.dameCelda(new Posicion(20,6)));

        unZealot.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		unZealot.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		
        assertEquals(unZealot.dameCelda(),unMapa.dameCelda(new Posicion(22,6)));

		assertTrue(unAltoTemplario.atacar(unZealot.dameCelda(), TipoAtaqueAltoTemplario.Alucinacion));
		
		Zealot alucinacionIzquierda = (Zealot) unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad();
		Zealot alucinacionDerecha = (Zealot) unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad();
		
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad() instanceof Zealot);
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad() instanceof Zealot);
		
		assertTrue(alucinacionDerecha.esUnaAlucinacion());
		assertTrue(alucinacionIzquierda.esUnaAlucinacion());
		assertEquals(0,alucinacionIzquierda.obtenerCantidadVida());
		assertEquals(60,alucinacionIzquierda.obtenerCantidadEscudo());
		
	/** Se crean Construcciones **/	
		Construccion expansor4 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(136,134), otroJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
	/** Se crea Barraca Fabrica y Puerto Estelar Terran **/	
		Barraca unaBarraca = (Barraca) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		Fabrica unaFabrica = (Fabrica) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(24,10), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();

	/** Se Crea NAVE CIENCIA  **/
		NaveCiencia unaNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(otroJugador,TipoUnidad.especial1);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		assertTrue(unaNaveCiencia.estaOperativa());
		assertTrue(unaNaveCiencia instanceof NaveCiencia);
		assertEquals(50,unaNaveCiencia.obtenerCantidadEnergia());
		assertEquals(unMapa.dameCelda(new Posicion(24,9)), unaNaveCiencia.dameCelda());

		for (int i=0;i<5;i++) unTurno.avanzarTurno();
		assertEquals(100,unaNaveCiencia.obtenerCantidadEnergia());
		
		assertEquals(200,unaNaveCiencia.obtenerCantidadVida());
		assertTrue(alucinacionDerecha.atacar(unaNaveCiencia.dameCelda()));
		assertEquals(200,unaNaveCiencia.obtenerCantidadVida());
		
		unaNaveCiencia.atacar(unZealot.dameCelda(), TipoAtaqueNaveCiencia.MisilEMP);
		assertEquals(0,unaNaveCiencia.obtenerCantidadEnergia());
		
		assertEquals(100,unZealot.obtenerCantidadVida());
		assertEquals(0,unZealot.obtenerCantidadEscudo());
		assertEquals(0,alucinacionIzquierda.obtenerCantidadVida());
		assertEquals(0,alucinacionIzquierda.obtenerCantidadEscudo());
		assertEquals(0,alucinacionDerecha.obtenerCantidadVida());
		assertEquals(0,alucinacionDerecha.obtenerCantidadEscudo());
		assertFalse(alucinacionDerecha.estaViva());
		assertFalse(alucinacionIzquierda.estaViva());
	}

	
	@SuppressWarnings("unused")
	public void testAltoTemplarioYNaveDeCienciaSinEnergiaDespuesDelDisparoEMP() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss();
		RazaTerran razaTerran = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstruccionesProtoss = unaRaza.getFactoryConstrucciones();
		AbstractConstruccionFactory factoryConstruccionesTerran = razaTerran.getFactoryConstrucciones();
		Posicion posicionAcceso = new Posicion(20,7);
		Posicion posicionPuertoEstelar = new Posicion(15,7);
		Posicion posicionArchivosTemplarios = new Posicion(22,10);
		
		Jugador otroJugador = new Jugador("Nombre",razaTerran,"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicionExpPob = new Posicion(12,3);		
		factoryConstruccionesProtoss.crearConstruccion(unTipo.expansorPoblacion, posicionExpPob , unJugador);

		Acceso unAcceso = (Acceso) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAcceso, unJugador);

		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicionPuertoEstelar, unJugador);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesEspeciales, posicionArchivosTemplarios, unJugador);

		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		AltoTemplario unAltoTemplario = (AltoTemplario) unArchivoTemplario.crearUnidad(unJugador);

		TipoUnidad unTipoUnidad = TipoUnidad.terrestre1;
		Unidad unZealot = (Unidad) unAcceso.crearUnidad(unJugador, unTipoUnidad);
		
		for (int i = 0; i<7 ;i++) unTurno.avanzarTurno();
		
		for (int i= 0; i < 5; i++) unTurno.avanzarTurno();

		assertEquals(125,unAltoTemplario.obtenerCantidadEnergia());
		
        assertEquals(unZealot.dameCelda(),unMapa.dameCelda(new Posicion(20,6)));

        unZealot.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		unZealot.mover(TipoDireccion.Abajo);
		unTurno.avanzarTurno();
		
        assertEquals(unZealot.dameCelda(),unMapa.dameCelda(new Posicion(22,6)));

		assertTrue(unAltoTemplario.atacar(unZealot.dameCelda(), TipoAtaqueAltoTemplario.Alucinacion));
		
		Zealot alucinacionIzquierda = (Zealot) unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad();
		Zealot alucinacionDerecha = (Zealot) unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad();
		
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,5)).obtenerUnidad() instanceof Zealot);
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).celdaOcupada());
		assertTrue(unMapa.dameCelda(new Posicion(22,7)).obtenerUnidad() instanceof Zealot);
		
		assertTrue(alucinacionDerecha.esUnaAlucinacion());
		assertTrue(alucinacionIzquierda.esUnaAlucinacion());
		assertEquals(0,alucinacionIzquierda.obtenerCantidadVida());
		assertEquals(60,alucinacionIzquierda.obtenerCantidadEscudo());
		
	/** Se crean Construcciones **/	
		Construccion expansor4 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(136,134), otroJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
	/** Se crea Barraca Fabrica y Puerto Estelar Terran **/	
		Barraca unaBarraca = (Barraca) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		Fabrica unaFabrica = (Fabrica) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(24,10), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();

	/** Se Crea NAVE CIENCIA  **/
		NaveCiencia unaNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(otroJugador,TipoUnidad.especial1);
		NaveCiencia otraNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(otroJugador,TipoUnidad.especial1);

		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		assertTrue(unaNaveCiencia.estaOperativa());
		assertTrue(otraNaveCiencia.estaOperativa());
		assertTrue(unaNaveCiencia instanceof NaveCiencia);
		assertTrue(otraNaveCiencia instanceof NaveCiencia);
		assertEquals(50,unaNaveCiencia.obtenerCantidadEnergia());
		assertEquals(50,otraNaveCiencia.obtenerCantidadEnergia());
		assertEquals(unMapa.dameCelda(new Posicion(24,9)), unaNaveCiencia.dameCelda());
		assertEquals(unMapa.dameCelda(new Posicion(24,8)), otraNaveCiencia.dameCelda());

		assertTrue(otraNaveCiencia.mover(TipoDireccion.Arriba));
		assertEquals(unMapa.dameCelda(new Posicion(23,8)), otraNaveCiencia.dameCelda());
		unTurno.avanzarTurno();
		assertTrue(otraNaveCiencia.mover(TipoDireccion.Arriba));
		assertEquals(unMapa.dameCelda(new Posicion(22,8)), otraNaveCiencia.dameCelda());
		unTurno.avanzarTurno();
		
		for (int i=0;i<4;i++) unTurno.avanzarTurno();
		assertEquals(110,unaNaveCiencia.obtenerCantidadEnergia());
		
		assertTrue(unaNaveCiencia.atacar(unAltoTemplario.dameCelda(), TipoAtaqueNaveCiencia.MisilEMP));
		assertEquals(0,unAltoTemplario.obtenerCantidadEnergia());
		assertEquals(0,otraNaveCiencia.obtenerCantidadEnergia());
		assertEquals(10,unaNaveCiencia.obtenerCantidadEnergia());
	}
	
	
	@SuppressWarnings("unused")
	public void testAltoTemplarioYNaveDeCienciaConEnergiaDespuesDelDisparoEMPPorEstarFueraDelRadioDeAtaque() throws JugadorConNombreDemasiadoCortoException, NoSuchObjectException, CantidadDeMineralInsuficienteException, CantidadDeGasInsuficienteException, ConstruccionInvalidaPrimeroDebeConstruirUnPuertoEstelarException, ConstruccionInvalidaPrimeroDebeConstruirUnAccesoException, ConstruccionInvalidaPrimeroDebeConstruirUnaBarracaException, NoHaySuficientesRecursos, NoSePudoConstruirException, CapacidadDePoblacionMaximaSuperada, MaximaCapacidadDeTransporteSuperadaException, NoSePuedeAgregarALaNaveDeTransporteUnaUnidadVoladora {
		Mapa unMapa = Mapa.getInstance();
		RazaProtoss unaRaza = new RazaProtoss();
		RazaTerran razaTerran = new RazaTerran(); 
		TipoConstruccion unTipo = null;
		Jugador unJugador = new Jugador("unNombre",unaRaza,"Azul");
		
		AbstractConstruccionFactory factoryConstruccionesProtoss = unaRaza.getFactoryConstrucciones();
		AbstractConstruccionFactory factoryConstruccionesTerran = razaTerran.getFactoryConstrucciones();
		Posicion posicionAcceso = new Posicion(20,7);
		Posicion posicionPuertoEstelar = new Posicion(15,7);
		Posicion posicionArchivosTemplarios = new Posicion(22,10);
		
		Jugador otroJugador = new Jugador("Nombre",razaTerran,"Azul");
		Turno unTurno = Turno.getInstance(unJugador,otroJugador);
		
		// Agrego las construcciones necesarias para crear la construccion actua
		Posicion posicionExpPob = new Posicion(12,3);		
		factoryConstruccionesProtoss.crearConstruccion(unTipo.expansorPoblacion, posicionExpPob , unJugador);

		Acceso unAcceso = (Acceso) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesBasicas, posicionAcceso, unJugador);

		for (int i = 0; i < 8 ;i++) unTurno.avanzarTurno();

		Construccion unPuertoEstelar = (Construccion) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesVoladoras, posicionPuertoEstelar, unJugador);
		unJugador.agregarConstruccion(unPuertoEstelar);
		for (int i = 0; i < 10 ;i++) unTurno.avanzarTurno();
		// Fin de agregado de Construcciones necesarias
		
		unJugador.dameAlmacenMineral().almacenarRecurso(1000);
		unJugador.dameAlmacenGas().almacenarRecurso(1000);
		otroJugador.dameAlmacenMineral().almacenarRecurso(1000);
		otroJugador.dameAlmacenGas().almacenarRecurso(1000);
		ArchivosTemplarios unArchivoTemplario = (ArchivosTemplarios) factoryConstruccionesProtoss.crearConstruccion(unTipo.creadorUnidadesEspeciales, posicionArchivosTemplarios, unJugador);

		assertFalse(unArchivoTemplario.estaOperativa());
		for (int i = 0; i<9 ;i++) unTurno.avanzarTurno();
		assertTrue(unArchivoTemplario.estaOperativa());

		AltoTemplario unAltoTemplario = (AltoTemplario) unArchivoTemplario.crearUnidad(unJugador);
		for (int i=0;i<7;i++) unTurno.avanzarTurno();
		assertEquals(unMapa.dameCelda(new Posicion(22,9)), unAltoTemplario.dameCelda());

		
	/** Se crean Construcciones **/	
		Construccion expansor4 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(134,134), otroJugador);
		Construccion expansor5 = (Construccion) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.expansorPoblacion, new Posicion(136,134), otroJugador);
		for (int i=0;i<6;i++) unTurno.avanzarTurno();
		
	/** Se crea Barraca Fabrica y Puerto Estelar Terran **/	
		Barraca unaBarraca = (Barraca) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesBasicas, new Posicion(15,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		Fabrica unaFabrica = (Fabrica) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesNivel2, new Posicion(25,15), otroJugador);
		for (int i=0;i<12;i++) unTurno.avanzarTurno();		
		PuertoEstelarTerran unPuerto = (PuertoEstelarTerran) factoryConstruccionesTerran.crearConstruccion(TipoConstruccion.creadorUnidadesEspecialesYVoladoras, new Posicion(24,10), otroJugador);
		for (int i=0;i<10;i++) unTurno.avanzarTurno();

	/** Se Crea NAVE CIENCIA  **/
		NaveCiencia unaNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(otroJugador,TipoUnidad.especial1);
		NaveCiencia otraNaveCiencia = (NaveCiencia) unPuerto.crearUnidad(otroJugador,TipoUnidad.especial1);

		for (int i=0;i<10;i++) unTurno.avanzarTurno();
		
		assertTrue(unaNaveCiencia.estaOperativa());
		assertTrue(otraNaveCiencia.estaOperativa());
		assertTrue(unaNaveCiencia instanceof NaveCiencia);
		assertTrue(otraNaveCiencia instanceof NaveCiencia);
		assertEquals(50,unaNaveCiencia.obtenerCantidadEnergia());
		assertEquals(50,otraNaveCiencia.obtenerCantidadEnergia());
		assertEquals(unMapa.dameCelda(new Posicion(24,9)), unaNaveCiencia.dameCelda());
		assertEquals(unMapa.dameCelda(new Posicion(24,8)), otraNaveCiencia.dameCelda());

		assertTrue(otraNaveCiencia.mover(TipoDireccion.Arriba));
		assertEquals(unMapa.dameCelda(new Posicion(23,8)), otraNaveCiencia.dameCelda());
		unTurno.avanzarTurno();
		assertTrue(otraNaveCiencia.mover(TipoDireccion.Arriba));
		assertEquals(unMapa.dameCelda(new Posicion(22,8)), otraNaveCiencia.dameCelda());
		unTurno.avanzarTurno();
		
		for (int i=0;i<4;i++) unTurno.avanzarTurno();
		assertEquals(110,unaNaveCiencia.obtenerCantidadEnergia());
		
		assertTrue(unaNaveCiencia.atacar(unMapa.dameCelda(new Posicion(20,8)), TipoAtaqueNaveCiencia.MisilEMP));
		assertEquals(200,unAltoTemplario.obtenerCantidadEnergia());
		assertEquals(110,otraNaveCiencia.obtenerCantidadEnergia());
		assertEquals(10,unaNaveCiencia.obtenerCantidadEnergia());
	}
}
