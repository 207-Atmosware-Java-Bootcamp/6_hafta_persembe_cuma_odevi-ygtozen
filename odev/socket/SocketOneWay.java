package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SocketOneWay {
	static Random random = new Random();
	public static final int PORT = 1234;
	static String ipAdress = "localhost";
	static Client client = new Client();
	
	// RandomPort
	private static final int randomPort() {
		
		return (random.nextInt(1024, 65535) + 1);
		
	}
	
	static void serverMethod() {
		String clientValue;
		// PORT = randomPort();
		System.out.println("Port: " + PORT);
		System.out.println("Server Hazir...");
		try (DataInputStream dataInputStream = new DataInputStream(new ServerSocket(PORT).accept().getInputStream())) {
			clientValue = dataInputStream.readUTF();
			int number = Integer.parseInt(clientValue);
			number = (int) Math.pow(number, 2);
			System.out.println("\n");
			System.out.println(number);
		} catch (Exception e) {
			System.err.println("hata");
			e.printStackTrace();
			
		}
	}
	
	// Server
	private static void serverMethod1() {
		try {
			ServerSocket socket = new ServerSocket(randomPort());// port oluşturma
			System.out.println("Server Hazir");
			Socket successPort = socket.accept();// Socet kabul
			InputStreamReader inputStreamReader = new InputStreamReader(successPort.getInputStream());// okuma
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			// Clientten Gelen veriyi alma ve gosterme
			String str = bufferedReader.readLine();
			System.out.println("Client : " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
