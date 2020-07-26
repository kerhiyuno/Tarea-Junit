package inf.junit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class CuentaTest {

	@Test
	public void testGetPila() throws IOException {
		//Given
		Cuenta cuenta = new Cuenta (1000,1000000);
		
		//When
		cuenta.Deposito("1000 CLP");
		cuenta.Deposito("100 CLP");
		cuenta.Deposito("200 CLP");
		cuenta.Deposito("30 USD");
		cuenta.Deposito("40 USD");
		cuenta.Deposito("2 USD");
		List<List<String>> historial = cuenta.getPila();
        List<List<String>> esperado = new ArrayList<List<String>>();
        List<String> transaccion = new ArrayList<>(); 
        transaccion.add("1001000");
        transaccion.add("CLP");
        transaccion.add("1000");
        transaccion.add("USD");
        esperado.add(transaccion);
        List<String> transaccion2 = new ArrayList<>(); 
        transaccion2.add("1001100");
        transaccion2.add("CLP");
        transaccion2.add("1000");
        transaccion2.add("USD");
        esperado.add(transaccion2);
        List<String> transaccion3 = new ArrayList<>(); 
        transaccion3.add("1001300");
        transaccion3.add("CLP");
        transaccion3.add("1000");
        transaccion3.add("USD");
        esperado.add(transaccion3);
        List<String> transaccion4 = new ArrayList<>(); 
        transaccion4.add("1001300");
        transaccion4.add("CLP");
        transaccion4.add("1030");
        transaccion4.add("USD");
        esperado.add(transaccion4);
        List<String> transaccion5 = new ArrayList<>(); 
        transaccion5.add("1001300");
        transaccion5.add("CLP");
        transaccion5.add("1070");
        transaccion5.add("USD");
        esperado.add(transaccion5);
        List<String> transaccion6 = new ArrayList<>(); 
        transaccion6.add("1001300");
        transaccion6.add("CLP");
        transaccion6.add("1072");
        transaccion6.add("USD");
        esperado.add(transaccion6);
		boolean comparacion = historial.containsAll(esperado);
		
		//Then
		Assert.assertTrue(comparacion);
	}

	@Test
	public void testDeposito() throws IOException {
		//Given
		Cuenta cuenta = new Cuenta (1000,1000000);
		
		//When
		int resultado1 = cuenta.Deposito("1000 CLP");
		int resultado2 = cuenta.Deposito("0 CLP");
		int resultado3 = cuenta.Deposito("-1 CLP");
		int resultado4 = cuenta.Deposito("1000 CLX");
		int resultado5 = cuenta.Deposito("1000 USD");
		int resultado6 = cuenta.Deposito("0 USD");
		int resultado7 = cuenta.Deposito("-1 USD");
		int resultado8 = cuenta.Deposito("-1  USD");
		int resultado9 = cuenta.Deposito(" 2 USD");
		int resultado10 = cuenta.Deposito(" 2 USD ");
		int resultado11 = cuenta.Retiro("1");
		
		//Then
		Assert.assertEquals(1001000, resultado1);
		Assert.assertEquals(1001000, resultado2);
		Assert.assertEquals(-1, resultado3);
		Assert.assertEquals(-2, resultado4);
		Assert.assertEquals(2000, resultado5);
		Assert.assertEquals(2000, resultado6);
		Assert.assertEquals(-1, resultado7);
		Assert.assertEquals(-2, resultado8);
		Assert.assertEquals(-3, resultado9);
		Assert.assertEquals(-3, resultado10);
		Assert.assertEquals(-4, resultado11);
	}

	@Test
	public void testRetiro() throws IOException {
		//Given
		Cuenta cuenta = new Cuenta (1000,1000000);
		
		//When
		int resultado1 = cuenta.Retiro("1000 CLP");
		int resultado2 = cuenta.Retiro("0 CLP");
		int resultado3 = cuenta.Retiro("-1 CLP");
		int resultado4 = cuenta.Retiro("1000 CLX");
		int resultado5 = cuenta.Retiro("1000 USD");
		int resultado6 = cuenta.Retiro("0 USD");
		int resultado7 = cuenta.Retiro("-1 USD");
		int resultado8 = cuenta.Retiro("-1  USD");
		int resultado9 = cuenta.Retiro(" 2 USD");
		int resultado10 = cuenta.Retiro(" 2 USD ");
		int resultado11 = cuenta.Retiro("2 USD");
		int resultado12 = cuenta.Retiro("1");
		
		//Then
		Assert.assertEquals(999000, resultado1);
		Assert.assertEquals(999000, resultado2);
		Assert.assertEquals(-1, resultado3);
		Assert.assertEquals(-2, resultado4);
		Assert.assertEquals(-1, resultado5);
		Assert.assertEquals(1000, resultado6);
		Assert.assertEquals(-1, resultado7);
		Assert.assertEquals(-2, resultado8);
		Assert.assertEquals(-3, resultado9);
		Assert.assertEquals(-3, resultado10);
		Assert.assertEquals(998, resultado11);
		Assert.assertEquals(-4, resultado12);
	}
}
