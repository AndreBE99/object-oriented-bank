package cuentas;

import java.util.Calendar;

public class CuentaAhorro extends Cuenta {
	
	// Definimos los atributos propios de las
	// cuentas de ahorro
	
	private double cuotaMantenimiento;
	
	// Constructores
	
	public CuentaAhorro () {}
	
	public CuentaAhorro(String nombre, String cuenta, double saldo, double tInt, double cuota) {
		super(nombre, cuenta, saldo, tInt);
		setCuota(cuota);
	}
	
	public void setCuota(double cuota) {
		if (cuota < 0) {
			System.out.println("Error: la cuota de mantenimiento no puede ser negativa");
			return;
		}
		this.cuotaMantenimiento = cuota;
	}
	
	public double getCuota() {
		return this.cuotaMantenimiento;
	}
	
	// Estos son los métodos abstractos que tenemos que implementar
	@Override
	public void comisiones() {
		// TODO Auto-generated method stub
		Calendar fechaActual = Calendar.getInstance();
//		int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
		int dia = 1; // Forzar ejecucion de comando
		
		// las comisiones se cobran los dia 1 de cada mes
		
		if (dia == 1) retiro(cuotaMantenimiento);
	}

	@Override
	public double intereses() {
		// TODO Auto-generated method stub
		Calendar fechaActual = Calendar.getInstance();
//		int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
		int dia = 1; // Forzar ejecucion de comando
		
		if (dia != 1) return 0.0; // no es dia primero no hay intereses
		
		// en caso contrario se calculan los interes
		double interesGenerado = 0.0;
		interesGenerado = getSaldo() * getTipoDeInteres() / 1200.0;
		deposito(interesGenerado);
		return interesGenerado;
	}
	
	@Override
	public String toString() {
		String mensaje = String.format("\t\tCUENTA DE AHORRO\n%s\n",
				super.toString());
		
		return mensaje;
	}

}// fin de la clase CuentaAhorro
