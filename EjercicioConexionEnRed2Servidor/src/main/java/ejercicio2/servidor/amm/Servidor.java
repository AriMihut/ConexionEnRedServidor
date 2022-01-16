package ejercicio2.servidor.amm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	

	public static void main(String[] args) {
		
		int puerto = 6000;
		
		try {
			ServerSocket server = new ServerSocket(puerto);
			
			System.out.println("Inicio el servidor y espero en el puerto 6000 la petición del clte: ");
			
			Socket cliente = server.accept();
			
//			//creo flujo de entrada para recibir la petición del clte:
			DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
//			System.err.println(flujoEntrada.readLine());/
			
			String numero = flujoEntrada.readUTF();
			System.err.println(numero);

//			//creo flujoSalida para enviar respuesta al clte:
			
			double media = calcularMedia(numero);
			System.err.println(media);
			
			DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
			flujoSalida.writeDouble(media);
//			System.out.println("He calculado la media " + media);
//			System.out.println("Envío mensaje de salida.");
			
			flujoEntrada.close();
			flujoSalida.close();
			cliente.close();
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static double calcularMedia(String numeros) {
		int suma = 0;
		
//		char[] arrayNunmeros = numeros.toCharArray();
//		for(int i = 0; i < arrayNunmeros.length; i++) {
//			suma += Integer.parseInt(String.valueOf(arrayNunmeros[i]));
//		};

		
		for(int i = 0; i < numeros.length(); i++) {
			suma += Integer.parseInt(String.valueOf(numeros.charAt(i)));
		}
		
		return suma/5;
	}

}
