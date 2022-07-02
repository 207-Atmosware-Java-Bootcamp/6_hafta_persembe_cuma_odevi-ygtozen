package socketopposite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Client {
	static String filePath = "D:\\Bootcamp\\Odevler\\messages.txt";
	
	public static void main(String[] args) {
		String receiveMessage;// mesaj almak
		String sendMessage; // mesa j göndersin
		
		try {
			Socket socket = new Socket("localhost", 8585);
			
			// Client veri gönderecek
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream, true);
			
			// serverdan gelen veriyi almak
			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream));
			System.out.println("Client: Lütfen mesaj yazınız ");
			
			while (true) {
				// clientten veri almak
				sendMessage = bufferedReader.readLine();
				printWriter.println(sendMessage);
				printWriter.flush();
				
				if ((receiveMessage = bufferedReader2.readLine()) != null) {
					System.out.println("SERVER: " + receiveMessage);
					try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
						File file = new File(filePath);
						Locale locale = new Locale("tr", "TR");
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss ", locale);
						
						Date date = new Date();
						String turkisDateTime = dateFormat.format(date);
						bufferedWriter.write("Server : " + turkisDateTime + " => " + receiveMessage + "\n");
						bufferedWriter.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
