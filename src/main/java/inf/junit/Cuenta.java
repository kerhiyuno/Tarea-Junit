package inf.junit;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Cuenta {
	
	private int saldousd;
	private int saldoclp;
	
	private int MAXIMO_RETIRO_USD = 100;
	private int MAXIMO_RETIRO_CLP = 200000;
	private int MINIMO_TRANSACCION = 0;
	
	List<List<String>> pila = new ArrayList<List<String>>();

	public Cuenta(int saldousd,int saldoclp) {
		super();
		this.saldousd = saldousd;
		this.saldoclp = saldoclp;
	}

	
	public List<List<String>> getPila() throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true));
		pw.println(dateFormat.format(date)+ "  Se ha consultado el historial de transacciones");
		pila.forEach(System.out::println);
		pw.close();
		return pila;
	}	
	
	public int getSaldousd() {
		return saldousd;
	}
	public int getSaldoclp() {
		return saldoclp;
	}
	
	public static boolean esNumero(String numero) {
        boolean esnumero;
        try {
            Integer.parseInt(numero);
            esnumero = true;
        } catch (NumberFormatException excepcion) {
        	esnumero = false;
        }
        return esnumero;
    }
	
	
	
	public int Deposito(String deposito) throws IOException {
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 Date date = new Date();
		 PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true));
		 String[] arrOfStr = deposito.split(" ", 2);
		 if (arrOfStr.length==1) {
				pw.println(dateFormat.format(date)+ " -4R Error: El valor ingresado no sigue el formato aceptado (Valor Moneda)");
			    pw.close();
			    System.out.println("Error: El valor ingresado no sigue el formato aceptado (Valor Moneda)");
			    return -4;
			}
		 if(esNumero(arrOfStr[0])==true) {
			 if(arrOfStr[1].equals("CLP")) {
				 System.out.println("Ha depositado: " + arrOfStr[0] +" CLP");
				 if(Integer.parseInt(arrOfStr[0])<MINIMO_TRANSACCION ) {
					 System.out.println("Error: El valor minimo a retirar es 0");
					 pw.println(dateFormat.format(date)+ "  -1D Error: Se ha intentado retirar un valor menor al minimo");
					 pw.close();
					 return -1;
				 }
				 this.saldoclp = this.saldoclp + Integer.parseInt(arrOfStr[0]);
				 System.out.println("Deposito exisoto. Nuevo saldo: " + this.saldoclp + " CLP");
				 var x = List.of(Integer.toString(this.saldoclp),"CLP",Integer.toString(this.saldousd),"USD");
				 pila.add(x);
				 pw.println(dateFormat.format(date)+ "  Se ha depositado "+arrOfStr[0]+" CLP. Nuevo saldo CLP: "+Integer.toString(this.saldoclp));
				 pw.close();
				 return this.saldoclp;
			 }
			 else if(arrOfStr[1].equals("USD")) {
				 System.out.println("Ha depositado: " + arrOfStr[0]+" USD");
				 if(Integer.parseInt(arrOfStr[0])<MINIMO_TRANSACCION ) {
					 System.out.println("Error: El valor minimo a retirar es 0");
					 pw.println(dateFormat.format(date)+ " -1D Error: Se ha intentado retirar un valor menor al minimo");
					 pw.close();
					 return -1;
				 }
				 this.saldousd = this.saldousd + Integer.parseInt(arrOfStr[0]);
				 System.out.println("Deposito exitoso. Nuevo saldo: " + this.saldousd + " USD");
				 var x = List.of(Integer.toString(this.saldoclp),"CLP",Integer.toString(this.saldousd),"USD");
				 pila.add(x);
				 pw.println(dateFormat.format(date)+"  Se ha depositado "+arrOfStr[0]+" USD. Nuevo saldo USD: "+Integer.toString(this.saldoclp));
				 pw.close();
				 return this.saldousd;
			 }
			 else {
				 System.out.println("Error: Debes ingresar un valor valido");
				 pw.println(dateFormat.format(date)+ " -2D Error: Se ha ingresado un valor no valido");
				 pw.close();
				 return -2;
			 }
		 }
		 else {
			 System.out.println("Error: Debes ingresar un valor valido");
			 pw.println(dateFormat.format(date)+ " -3D Error: Se ha ingresado un valor no valido");
			 pw.close();
			 return -3;
		 }

	}
	
	public int Retiro(String retiro) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true));
		String[] arrOfStr = retiro.split(" ", 2);
		if (arrOfStr.length==1) {
			pw.println(dateFormat.format(date)+ " -4R Error: El valor ingresado no sigue el formato aceptado (Valor Moneda)");
		    pw.close();
		    System.out.println("Error: El valor ingresado no sigue el formato aceptado (Valor Moneda)");
		    return -4;
		}
		 if(esNumero(arrOfStr[0])==true) {
			 if(arrOfStr[1].equals("CLP")) {
				 if(Integer.parseInt(arrOfStr[0])>MAXIMO_RETIRO_CLP || Integer.parseInt(arrOfStr[0])<MINIMO_TRANSACCION ) {
					 System.out.println("Error: El valor a retirar puede ser de 0 a 200000 CLP");
					 pw.println(dateFormat.format(date)+ " -1R Error: Se ha intentado retirar un valor fuera del rango permitido 0-200000");
					 pw.close();
					 return -1;
				 }
				 System.out.println("Ha retirado: " + arrOfStr[0] +" CLP");
				 this.saldoclp = this.saldoclp - Integer.parseInt(arrOfStr[0]);
				 System.out.println("Retiro exitoso.. Nuevo saldo: " +this.saldoclp);
				 var x = List.of(Integer.toString(this.saldoclp),"CLP",Integer.toString(this.saldousd),"USD");
				 pila.add(x);
				 pw.println(dateFormat.format(date)+"  Se ha Retirado "+arrOfStr[0]+" CLP. Nuevo saldo CLP: "+Integer.toString(this.saldoclp));
				 pw.close();
				 return this.saldoclp;
			 }
			 else if(arrOfStr[1].equals("USD")) {
				 if(Integer.parseInt(arrOfStr[0])>MAXIMO_RETIRO_USD || Integer.parseInt(arrOfStr[0])<MINIMO_TRANSACCION ) {
					 System.out.println("Error: El valor a retirar puede ser de 0 a 100 USD");
					 pw.println(dateFormat.format(date)+ " -1R Error: Se ha intentado retirar un valor fuera del rango permitido 0-100");
					 pw.close();
					 return -1;
				 }
				 System.out.println("Ha retirado: " + arrOfStr[0]+" USD");
				 this.saldousd = this.saldousd - Integer.parseInt(arrOfStr[0]);
				 var x = List.of(Integer.toString(this.saldoclp),"CLP",Integer.toString(this.saldousd),"USD");
				 pila.add(x);
				 System.out.println("Retiro exitoso. Nuevo saldo: " +this.saldousd);
				 pw.println(dateFormat.format(date)+"  Se ha retirado "+arrOfStr[0]+" USD. Nuevo saldo USD: "+Integer.toString(this.saldoclp));
				 pw.close();
				 return this.saldousd;
			 }
			 else {
				 System.out.println("Error: Debes ingresar un valor valido");
				 pw.println(dateFormat.format(date)+ " -2R Error: Se ha ingresado un valor no valido");
				 pw.close();
				 return -2;
			 }
		 }
		 else {
			 pw.println(dateFormat.format(date)+ " -3R Error: Se ha ingresado un valor no valido");
			 pw.close();
			 System.out.println("Error: Debes ingresar un valor valido");
			 return -3;
		 }
		
	}
	
}
