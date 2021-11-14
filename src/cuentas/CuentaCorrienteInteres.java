package cuentas;

import java.util.Calendar;

public class CuentaCorrienteInteres extends CuentaCorriente {
	
	// no tiene ningún atributo propio, solamente los heredados
	
	// constructores
	
	public CuentaCorrienteInteres() {}  // constructor vacío
	
	public CuentaCorrienteInteres(String nombre, String cuenta, double saldo, double tipoInteres,
			double importePorTransaccion, int transaccionesLibres) {
		
		super(nombre, cuenta, saldo, tipoInteres,importePorTransaccion, transaccionesLibres);
		// cómo no tenemos ningún atributo propio, solamente invocamos el constructor de la superclase
	}
	
	@Override
	public double intereses() {
		// Esta cuenta paga los intereses sobre todo el saldo, siempre y cuando el
		// saldo sea mayor a 3000, de lo contrario no paga nada
		
		Calendar fechaActual = Calendar.getInstance();
//		int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
		int dia = 1; // Forzar ejecucion de comando
		
		if ((dia != 1) || (getSaldo() < 3000)) return 0.0;
		
		// acumulamos los intereses generados
		double interesesGenerados = 0.0;
		interesesGenerados = getSaldo() * getTipoDeInteres() / 1200.0;
		deposito(interesesGenerados); 
		decrementarTransacciones();
		
		return interesesGenerados;
	}
	
	@Override
	public String toString() {
		String mensaje = String.format("\t\tCUENTA CORRIENTE INTERESES\n%s",
				super.toString());
		
		return mensaje;
	}

}
