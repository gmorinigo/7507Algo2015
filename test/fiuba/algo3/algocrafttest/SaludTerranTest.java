package fiuba.algo3.algocrafttest;

import fiuba.algo3.algocraft.modelo.unidades.terran.SaludTerran;

public class SaludTerranTest extends TestBase {

	public void testVerificarSaludInicial(){
		SaludTerran unaSaludTerran = new SaludTerran(100);
		assertTrue(unaSaludTerran.valorDeSalud() == 100);
	}
	
	public void testVerificarEliminacionDeLaSaludFrenteA2Ataques(){
		SaludTerran unaSaludTerran = new SaludTerran(100);
		unaSaludTerran.atacar(50);
		unaSaludTerran.atacar(50);
		assertFalse(unaSaludTerran.tieneVida());
	}

	public void testBajarLaSaludACero(){
		SaludTerran unaSaludTerran = new SaludTerran(100);
		unaSaludTerran.atacar(100);
		assertFalse(unaSaludTerran.tieneVida());
	}
	
	public void testAtaqueMuchoMayorQueLaVida_TieneQueQuedarEnCero(){
		SaludTerran unaSaludTerran = new SaludTerran(100);
		unaSaludTerran.atacar(1000);
		assertTrue(unaSaludTerran.valorDeSalud() == 0);
	}

	
	public void testVerificoElPorcentajeDeLaSalud(){
		SaludTerran unaSaludTerran = new SaludTerran(100);
		unaSaludTerran.atacar(50);
		assertTrue(unaSaludTerran.porcentajeSalud() == 50);
	}
	
	
}
