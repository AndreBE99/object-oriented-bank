package cuentas;

import java.util.Calendar;

public class CuentaCorriente extends Cuenta {
	
	// Atributos propios de las cuentas corrientes
	private int transacciones ;  // número de transacciones realizadas cada mes
	private double importePorTransaccion; // costo de cada transacción excedente
	private int transaccionesExentas; // número de transacciones sin costo por mes
	
	// métodos
	
	public CuentaCorriente() {}  //constructor vacío
	
	public CuentaCorriente(String nombre, String cuenta, double saldo, double tInt, 
			double importeTransaccion, int transaccionesLibres) {
		super(nombre, cuenta, saldo, tInt);
		this.transacciones = 0 ; // inicializa las transacciones a 0
		setImportePorTransaccion(importeTransaccion);
		setTransaccionesExentas(transaccionesLibres);
	}
	
	// Setters & getters
	
	public void decrementarTransacciones () {
		this.transacciones -- ;
	}
	
	public void setImportePorTransaccion( double importeTransaccion) {
		if (importeTransaccion < 0) {
			System.out.println("Error: el importe no puede ser negativo");
			return;
		}
		this.importePorTransaccion = importeTransaccion;
	}
	
	public double getImportePorTransaccion() { return this.importePorTransaccion;}
	
	public void setTransaccionesExentas(int transaccionesExentas) {
		if (transaccionesExentas < 0) {
			System.out.println("Error: las transacciones exentas no pueden ser negativas");
			return;
		}
		this.transaccionesExentas = transaccionesExentas;
	}
	
	public int getTransaccionesExentas() { return this.transaccionesExentas;}
	
	public void deposito(double cantidad) {
		super.deposito(cantidad);
		this.transacciones ++;
	}
	
	public void retiro(double cantidad) {
		super.retiro(cantidad);
		this.transacciones ++;
	}

	// Estos son los métodos que se deben implementar de la clase abstracta
	@Override
	public void comisiones() {
		// las comisiones se cobran a principio de mes y consiste en una cuota por transaccion
		// exedente
		// Al calcular las comisiones el contador de trasacciones se regresa a 0
		
		Calendar fechaActual = Calendar.getInstance();
//		int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
		int dia = 1; // Forzar ejecucion de comando
		
		if (dia == 1) {
			int n = this.transacciones - this.transaccionesExentas;
			if (n > 0) retiro (n* this.importePorTransaccion);
			this.transacciones = 0;
		}
	}
	
	//Para el calculo de los intereses la cuenta corriente funciona de la siguiente manera
	// los primeros 3000 (saldo mínimo) solamente pagan un 0.5% de interés anual)
	// el resto del saldo genera intereses de acuerdo al tipo de interés que se definió en la cuena
	// los intereses generados se calculan el día 1 de cada mes
	@Override
	public double intereses() {
		Calendar fechaActual = Calendar.getInstance();
//		int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
		int dia = 1; // Forzar ejecucion de comando
		
		if (dia !=1) return 0.0; // si no es dia 1 no hay intereses
		
		double interesesGenerados = 0.0;
		
		if (getSaldo() <= 3000)
			interesesGenerados = getSaldo() * 0.5 / 1200.0;
		else {
			interesesGenerados = (3000 * 0.5 / 1200.0) +
					((getSaldo() - 3000) * getTipoDeInteres() / 1200.0);
		}
		deposito(interesesGenerados);
		decrementarTransacciones();
		
		return(interesesGenerados);
	}
	
	@Override
	public String toString() {
		String mensaje = String.format("\t\tCUENTA CORRIENTE\n%s\tTransacciones exentas: %d\n\tTransacciones: %d\n\tImporte por transaccion: $%.2f\n",
				super.toString(), this.transaccionesExentas, this.transacciones, this.importePorTransaccion);
		
		return mensaje;
	}
	
	// FALTA EL METODO toString( )

} // fin de la clase CuentaCorriente
