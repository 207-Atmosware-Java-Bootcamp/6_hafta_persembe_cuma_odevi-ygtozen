package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	
	static void clientMethod() {
		// port
		// ipAdress
		String ipAdress = SocketOneWay.ipAdress;
		// user data
		String vocabulary = JOptionPane.showInputDialog("Lutfen biseyler yaziniz");
		
		try (DataOutputStream dataOutputStream = new DataOutputStream(new Socket(ipAdress, 1234).getOutputStream())) {
			dataOutputStream.writeUTF(vocabulary);
		} catch (IOException ex) {
			System.out.println("IO");
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("EX");
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		clientMethod();
	}
}
