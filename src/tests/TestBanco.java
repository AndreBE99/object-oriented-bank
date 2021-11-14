package tests;

import bancos.Banco;

public class TestBanco {

	public static void main(String[] args) {

		Banco myBank = new Banco();
		
		myBank.addCustomer();
		myBank.addCustomer();
		myBank.addCustomer();
		
		myBank.deleteCustomer();
		
	}

}
