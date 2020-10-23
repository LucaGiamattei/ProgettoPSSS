package dataLayer.pagamento;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataLayer.pagamento.controller.ControllerPagamentoDB;
import dataLayer.pagamento.entities.PagamentoDB;
import utilities.idFasciaOraria;

class TestPagamento {
	static ControllerPagamentoDB contPagamento;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		contPagamento = new ControllerPagamentoDB();
	}

	@Test
	void testGenAndGetTokens() {
		
		
		Vector<PagamentoDB> payments = new Vector<PagamentoDB>();
		contPagamento.genAndGetTokens(new idFasciaOraria(1), payments );
	}

	@Test
	void testSubscribePayment() {
		fail("Not yet implemented");
	}

	@Test
	void testVerifyUserHasPayed() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUsersPayedLesson() {
		fail("Not yet implemented");
	}

}
