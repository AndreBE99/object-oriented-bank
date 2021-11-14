package bancos;

import tools.Leer;

public class AplicacionBancaria {

	public static void main(String[] args) {
		
		Banco myBank = new Banco();
		
		int action = 0;
		
		do {
			action = getAction();
			switch (action) {
				case 1:
					System.out.println("A�adir un nuevo cliente");
					if(myBank.addCustomer()) {
						System.out.println("El cliente se ha a�adido correctamente.");
					}
					break;
				case 2:
					System.out.println("Eliminando un cliente");
					if(myBank.deleteCustomer()) {
						System.out.println("La cuenta se elimin� correctamente");
					}
					break;
				case 3:
				case 4:
					System.out.println("Captura el nombre del cliente o el n�mero de cuenta: ");
					String customer = Leer.dato();
					if(myBank.searchCustomer(customer) == -1) {
						System.out.println("El cliente no existe en el Banco!");
						break;
					}
					System.out.println("Captura la cantidad a operar:");
					double cantidad = Leer.datoDouble();
					if(action==3) myBank.performTransaction("Dep�sito", customer, cantidad);
					else myBank.performTransaction("Retiro", customer, cantidad);
					break;
				case 5:
					System.out.println("Captura el nombre del cliente o el n�mero de cuenta: ");
					customer = Leer.dato();
					myBank.printCustomer(customer);
					break;
				case 6:
					myBank.printCustomers();
					break;
				case 7:
					myBank.cobrarComisiones();
					myBank.pagarIntereses();
					break;
				case 8:
					break;
			}
		} while(action != 9);
		
		System.out.println("\n\tAdi�s! :)");

	}
	
	private static void printMenu() {
		System.out.println("\n\t\t\tMen�");
		System.out.println("\t1.- A�adir un cliente al Banco");
		System.out.println("\t2.- Borrar un cliente del Banco");
		System.out.println("\t3.- Realizar un dep�sito");
		System.out.println("\t4.- Realizar un retiro");
		System.out.println("\t5.- Imprimir informaci�n de una cuenta");
		System.out.println("\t6.- Imprimir informaci�n de todas las cuentas");
		System.out.println("\t7.- Realizar operaciones de mantenimiento");
		System.out.println("\t8.- Mostrar el men�");
		System.out.println("\t9.- Salir\n");
	}
	
	private static int getAction() {
		int action = 0;
		do {
			printMenu();
			System.out.println("Selecciona una opci�n: ");
			action = Leer.datoInt();
		} while((action < 1) || (action > 9));
		return action;
	}

}
