package DAOImpl;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import DAO.Nhan;

public class InputSteamImpl implements Runnable{
	private Socket connecton;
	private Nhan nhan;
	private DataInputStream dataInputStream;
	
	public  InputSteamImpl(Socket connecton,Nhan nhan) throws IOException {
		this.connecton = connecton;
		this.nhan = nhan;
		this.dataInputStream = new DataInputStream(connecton.getInputStream());
	}
	@Override
	public void run() {
		while(true) {
			try {
				// nhân du liệu
				String data = dataInputStream.readUTF();
				// trả lại 
				nhan.CallBack(data, this);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
