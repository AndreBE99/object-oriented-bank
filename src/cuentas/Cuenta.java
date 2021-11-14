package cuentas;

public abstract class Cuenta {
	// declaramos los atributos que tendrán todas las cuentas
	private String nombre;
	private String cuenta;
	private double saldo;
	private double tipoDeInteres;
	
	// Constructores
	
	public Cuenta () { }
	
	public Cuenta ( String nombre, String cuenta, double saldo, double tInt) {
		setNombre(nombre);
		setCuenta(cuenta);
		setSaldo(saldo);
		setTipoDeInteres(tInt);
	}

	// setters & getters
	
	public void setNombre(String nombre) {
		if (nombre.length() == 0) {
			System.out.println("Error: Nombre está vacío");
			return;
		}
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void setCuenta(String cuenta) {
		if (cuenta.length() == 0) {
			System.out.println("Error: Cuenta está vacía");
			return;
		}
		this.cuenta = cuenta;
	}
	
	public String getCuenta() {
		return this.cuenta;
	}
	
	public void setSaldo(double saldo) {
		if (saldo < 0) {
			System.out.println("Error: el saldo no puede ser negativo");
			return;
		}
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void setTipoDeInteres(double tInt) {
		if (tInt < 0 ) {
			System.out.println("Error: el interés no puede ser negativo");
			return;
		}
		this.tipoDeInteres = tInt;
	}
	
	public double getTipoDeInteres() {
		return this.tipoDeInteres;
	}
	
	// Definimos métodos abstractos que van a ser implementados
	// por los diferentes tipos de cuenta
	
	public abstract void comisiones();
	
	public abstract double intereses();
	
	public void deposito(double cantidad) {
		if (cantidad < 0) {
			System.out.println("Error: la cantidad no puede ser negativa");
			return;
		}
		this.saldo += cantidad;
	}
	
	public void retiro(double cantidad) {
		if ((this.saldo - cantidad) < 0) {
			System.out.println("Error: no dispone de fondos suficientes");
			return;
		}
		this.saldo -= cantidad;
	}
	
	public String toString() {
		String mensaje = "";
		mensaje = String.format("\tNombre :\t %20s\n"
				+ "\tCuenta : \t %20s\n"
				+ "\tSaldo :\t\t\t $%10.2f\n"
				+ "\tTasa Interes :\t\t\t %3.1f",this.nombre, this.cuenta,this.saldo, this.tipoDeInteres);
		return mensaje +"%\n";
		
	}
	
} // fin de la clase Cuenta
