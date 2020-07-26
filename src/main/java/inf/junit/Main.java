package inf.junit;
import java.util.Scanner;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		 int sesiones = 0;
		 Cuenta cuenta = new Cuenta(1000,1000000);
		 Scanner input = new Scanner(System.in);  // Create a Scanner object
		 
		 while (true) {
			 if (sesiones<3) {
				 System.out.println("Ingrese un valor para iniciar sesión:");
				 input.nextLine();
				 sesiones=sesiones+1;
				 int operaciones=0;
				 while (true) {
					 	 if(operaciones >= 4) {
					 		 System.out.println("Ha alcanzado el numero maximo de operaciones, su sesión se ha cerrado");
					 		 break;
					 	 }
						 System.out.println("Bienvenido a Banco Azul, selecciona operación a realizar: \n"
						 		+ "\"1\" Depósito\r\n" + 
						 		"\r\n" + 
						 		"\"2\" Retiro \r\n" + 
						 		"\r\n" + 
						 		"\"3\" Ver transacciones\r\n" + 
						 		"\r\n" + 
						 		"\"4\" Cerrar sesión");
						 String opcion = input.nextLine();  // Read user input
						 System.out.println("Ha seleleccionado la opción: " + opcion);  // Output user input
 
						 if (opcion.equals("1")==true) {
							 operaciones=operaciones+1;
							 System.out.println("Ingrese monto a depositar y moneda. Ejemplos: 1000 CLP, 500 USD");
							 String deposito = input.nextLine();
							 int respuesta= cuenta.Deposito(deposito);
							 if (respuesta==-1 || respuesta==-2 || respuesta==-3|| respuesta==-4) {
								 operaciones=operaciones-1;
							 }
							 
						 }
						 else if (opcion.equals("2")==true) {
							 operaciones=operaciones+1;
							 System.out.println("Retiro");
							 System.out.println("Cuanto desea retirar:");
							 String retiro = input.nextLine();
							 int respuestar=cuenta.Retiro(retiro);
							 if(respuestar==-1 || respuestar== -2 || respuestar==-3 || respuestar==-4) {
								 operaciones=operaciones-1;
							 }
						 }
						 else if (opcion.equals("3")==true) {
							 operaciones=operaciones+1;
							 System.out.println("Ver transacciones");
							 cuenta.getPila();
						 }
						 else if (opcion.equals("4")==true) {
							 System.out.println("Cerrar Sesión");
							 break;
						 }
						 else {
							 System.out.println("Error: Debe ingresar una opción válida");
						 }
				 }
			 }else {
				 System.out.println("Se ha alcanzado el numero máximo de sesiones permitidas");
				 break;
			 }
		 }
		 input.close();
	}
}
