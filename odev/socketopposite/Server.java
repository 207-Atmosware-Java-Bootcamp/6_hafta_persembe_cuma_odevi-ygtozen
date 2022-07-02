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
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Server {
	static String filePath = "D:\\Bootcamp\\Odevler\\messages.txt";
	
	public static void main(String[] args) {
		
		String receiveMessage;// mesaj almak
		String sendMessage; // mesa j göndersin
		System.out.println("Server Hazır");
		
		try {
			ServerSocket serverSocket = new ServerSocket(8585);
			Socket socket = serverSocket.accept();
			
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			PrintWriter printWriter = new PrintWriter(outputStream, true);
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader receiveRead = new BufferedReader(new InputStreamReader(inputStream));
			
			while (true) {
				if ((receiveMessage = receiveRead.readLine()) != null) {
					System.out.println("CLIENT: " + receiveMessage);
					try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
						File file = new File(filePath);
						Locale locale = new Locale("tr", "TR");
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss ", locale);
						Date date = new Date();
						String turkisDateTime = dateFormat.format(date);
						bufferedWriter.write("Clıent : " + turkisDateTime + " => " + receiveMessage + "\n");
						bufferedWriter.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				sendMessage = bufferedReader.readLine();
				printWriter.println(sendMessage);
				printWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
