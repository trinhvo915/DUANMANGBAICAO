package DAOImpl;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import DAO.Gui;
import DAO.Nhan;
import DAO.XuLy;
import GUI.GameFrame;
import Logic.ClientProcessor;

public class Connection implements Nhan,Gui{
	protected Socket socket;
	protected InputSteamImpl inputSteamImpl;
	protected OutputStreamImpl outputStreamImpl;
	
	protected XuLy xuLy;
	protected Nhan nhan;
	
	public Connection() {
	}
	
	
	public Connection(String Ip, int Port, Nhan nhan,XuLy xuLy) throws UnknownHostException, IOException {
		try {
			this.xuLy = xuLy;
			this.nhan = nhan;
			
			this.socket = new Socket(Ip, Port);
			this.outputStreamImpl = new OutputStreamImpl(socket);
			this.inputSteamImpl = new InputSteamImpl(socket, this);
			// tao 2 luong gui va nhan du lieu
			Thread outputThread = new Thread(outputStreamImpl);
			Thread inputThread = new Thread(inputSteamImpl);
			
			outputThread.start();
			inputThread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Override
	public void send(String data) {
		try {
			this.outputStreamImpl.send(data);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void CallBack(String data, Object object) {
		try {
			xuLy.xuly(data, this.nhan, this);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
