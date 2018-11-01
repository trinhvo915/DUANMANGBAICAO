package DAOImpl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import DAO.Gui;

public class OutputStreamImpl implements Runnable , Gui{
	private Socket  connection;
	private DataOutputStream dataOutputStream;
	private boolean coCheck = false; // cờ kiểm tra gửi hay ko ?
	private String data = "";
	
	public OutputStreamImpl() {
		
	}

	public  OutputStreamImpl(Socket  connection) throws IOException {
		this.connection = connection;
		dataOutputStream = new DataOutputStream(this.connection.getOutputStream());
	}
	
	@Override
	public void send(String data) {
		this.coCheck = true;
		this.data = data;
	}

	@Override
	public void run() {
		while(true) {
			// neu cocheck = true // co data
			try {
				if(coCheck) {
					System.out.println("send : " + data);
					dataOutputStream.writeUTF(data);
					dataOutputStream.flush();
					coCheck = false;
				}
				Thread.sleep(1000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
		}
	}
	
}
