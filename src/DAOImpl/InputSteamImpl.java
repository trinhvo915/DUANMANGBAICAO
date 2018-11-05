package DAOImpl;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import DAO.Nhan;
import Logic.Logic;

public class InputSteamImpl implements Runnable{
	private Socket connecton;
	private Nhan nhan;
	private DataInputStream dataInputStream;
	
	public  InputSteamImpl(Socket connecton,Nhan nhan) throws IOException {
		try {
			this.connecton = connecton;
			this.nhan = nhan;
			this.dataInputStream = new DataInputStream(connecton.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				// nhân du liệu
				String data = dataInputStream.readUTF();
				// trả lại 
				nhan.CallBack(data, this);
				Logic.sleep(1000);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
