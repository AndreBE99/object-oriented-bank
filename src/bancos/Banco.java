package bancos;

import java.util.ArrayList;
import java.util.List;

import cuentas.*;
import tools.Leer;

public class Banco {
	
	private List<Cuenta> clientes = null;
	
	public Banco() {
		this.clientes = new ArrayList<>();
	}
	
	public boolean addCustomer() {
		Cuenta newCustomer = crearCuenta();
		if (searchCustomer(newCustomer) != -1) {
			System.out.println("Error: La cuenta ya existe!");
			return false;
		} else {
			clientes.add(newCustomer);
			return true;
		}
	}
	
	public boolean deleteCustomer() {
		System.out.println("Captura el nombre o el número de cuenta a eliminar: ");
		String key = Leer.dato();
		int index = searchCustomer(key);
		if (index == -1) {
			System.out.println("Error: La cuenta no existe en este banco!");
			return false;
		}
		System.out.println("Se ha borrado la cuenta:\n" + clientes.remove(index));
		return true;
	}
	
	public int searchCustomer(Cuenta customer) {
		return clientes.indexOf(customer);
	}
	
	public int searchCustomer(String key) {
		int index = -1;
		for(int i=0; i<clientes.size(); i++) {
			if(clientes.get(i).getNombre().equals(key) || clientes.get(i).getCuenta().equals(key)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public boolean performTransaction(String transaction, String key, double cantidad) {
		int indice = searchCustomer(key);
		
		if(indice != -1) {
			Cuenta cuenta = clientes.get(indice);
			if(transaction.equals("Depósito")) {
				cuenta.deposito(cantidad);
			} else {
				cuenta.retiro(cantidad);
			}
			return true;
		}
		return false;
	}
	
	private Cuenta crearCuenta() {
		int option = tipoDeCuenta();
		Cuenta newCustomer = null;
		switch (option) {
			case 1:
				System.out.println("\tCUENTA DE AHORRO\n");
				break;
			case 2:
				System.out.println("\tCUENTA CORRIENTE\n");
				break;
			case 3:
				System.out.println("\tCUENTA CORRIENTE CON INTERES\n");
				break;
		}
		System.out.println("Captura el nombre del cliente: ");
		String nombre = Leer.dato();
		System.out.println("Captura el número de cuenta: ");
		String numeroDeCuenta = Leer.dato();
		System.out.println("Captura el saldo inicial: ");
		double saldo = Leer.datoDouble();
		System.out.println("Captura la tasa de interés: ");
		double tasa = Leer.datoDouble();
		if(option == 1) {
			System.out.println("Capture la cuota de mantenimiento: ");
			double cuota = Leer.datoDouble();
			newCustomer = new CuentaAhorro(nombre, numeroDeCuenta,saldo, tasa, cuota);
		} else {
			System.out.println("Capture el número de transacciones excentas: ");
			int transaccionesExcentas = Leer.datoInt();
			System.out.println("Capture el importe por transacción: ");
			double importe = Leer.datoDouble();
			if(option == 3) {
				newCustomer = new CuentaCorrienteInteres(nombre, numeroDeCuenta,
						saldo, tasa, importe, transaccionesExcentas);
			} else {
				newCustomer = new CuentaCorriente(nombre, numeroDeCuenta,
						saldo, tasa, importe, transaccionesExcentas);
			}
		}
		return newCustomer;
	}
	
	private int tipoDeCuenta() {
		int tipo = 0;
		String mensaje = String.format("Por favor capture el tipo de cuenta:\n"
				+"\t1.- Cuenta de ahorro\n\t2.- Cuenta corriente\n"
				+"\t3.- Cuenta corriente con interés");
		do {
			System.out.println(mensaje);
			tipo =Leer.datoInt();
		} while((tipo < 1) || (tipo > 3));
		return tipo;
	}
	
	public void printCustomer(String key) {
		int indice = searchCustomer(key);
		
		if(indice != -1) {
			System.out.println("\t\tINFORMACION DE LA CUENTA:\n");
			Cuenta cuenta = clientes.get(indice);
			System.out.println(cuenta);
		} else {
			System.out.println("Error: La cuenta no existe en este Banco!");
		}
	}
	
	public void printCustomers() {
		if(clientes.size() == 0) System.out.println("\t\tLA LISTA ESTA VACIA!");
		else {
			System.out.println("\t\tLISTA DE TODAS LAS CUENTAS:\n");
			for(int i=0; i<clientes.size(); i++) {
				Cuenta cuenta = clientes.get(i);
				System.out.println(cuenta);
			}
		}
	}
	
	public int getNumberOfCustomers() {
		return clientes.size();
	}
	
	public void cobrarComisiones() {
		System.out.println("Cobrando comisiones...");
		for(int i=0; i<clientes.size(); i++) {
			Cuenta cuenta = clientes.get(i);
			cuenta.comisiones();
		}
		System.out.println("Se cobraron todas las comisiones!");
		printCustomers();
	}
	
	public void pagarIntereses() {
		System.out.println("Pagando intereses...");
		for(int i=0; i<clientes.size(); i++) {
			Cuenta cuenta = clientes.get(i);
			cuenta.intereses();
		}
		System.out.println("Se pagaron todos los intereses!");
		printCustomers();
	}
}
