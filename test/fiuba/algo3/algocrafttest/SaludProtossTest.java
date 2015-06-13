package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.unidades.protoss.SaludProtoss;

public class SaludProtossTest extends TestBase {

	public void testVerificarSaludInicial(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		assertTrue(unaSaludProtoss.valorDeSalud() == 100);
	}
	
	public void testVerificarFuncionamientoDelEscudoFrenteA2Ataques(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(50);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.valorDeSalud() == 100);
	}
	
	public void testVerificarEliminacionDelEscudo(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(101);
		assertTrue(unaSaludProtoss.valorDeSalud() == 99);
	}
	
	public void testBajarLaSaludACero(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(100);
		unaSaludProtoss.atacar(100);
		assertTrue(unaSaludProtoss.valorDeSalud() == 0);
	}
	
	public void testAtaqueMuchoMayorQueLaVida_TieneQueQuedarEnCero(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(1000);
		assertTrue(unaSaludProtoss.valorDeSalud() == 0);
	}
	
	public void testRegeneracionDeEscudo(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(100);
		unaSaludProtoss.regenerarEscudo(50);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.valorDeSalud() == 100);	
	}
	
	public void testVerificoElPorcentajeDeLaSalud(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.porcentajeSalud() == 100);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.porcentajeSalud() == 100);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.porcentajeSalud() == 50);
		unaSaludProtoss.atacar(30);
		assertTrue(unaSaludProtoss.porcentajeSalud() == 20);
	}

	public void testVerificoElPorcentajeDelEscudo(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(50);
		assertTrue(unaSaludProtoss.porcentajeEscudo() == 50);
		unaSaludProtoss.atacar(30);
		assertTrue(unaSaludProtoss.porcentajeEscudo() == 20);
	}
	
	public void testVerificarRecuperacionDelEscudoConElPasoDeLosTurnosYLaRegeneracionMaximaDelMismo(){
		SaludProtoss unaSaludProtoss = new SaludProtoss(100,100);
		unaSaludProtoss.atacar(10);
		assertTrue(unaSaludProtoss.valorEscudo() == 90);
		unaSaludProtoss.pasarTurno();
		assertTrue(unaSaludProtoss.valorEscudo() == 95);
		unaSaludProtoss.pasarTurno();
		assertTrue(unaSaludProtoss.valorEscudo() == 100);
		unaSaludProtoss.pasarTurno();
		assertTrue(unaSaludProtoss.valorEscudo() == 100);
		unaSaludProtoss.pasarTurno();
		assertTrue(unaSaludProtoss.valorEscudo() == 100);
		unaSaludProtoss.pasarTurno();
		assertTrue(unaSaludProtoss.valorEscudo() == 100);
	}
	
}

