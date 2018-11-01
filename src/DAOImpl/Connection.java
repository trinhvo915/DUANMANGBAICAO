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
		this.xuLy = xuLy;
		this.nhan = nhan;
		
		this.socket = new Socket(Ip, Port);
		this.outputStreamImpl = new OutputStreamImpl(socket);
		this.inputSteamImpl = new InputSteamImpl(socket, nhan);
		// tao 2 luong gui va nhan du lieu
		Thread outputThread = new Thread(outputStreamImpl);
		Thread inputThread = new Thread(inputSteamImpl);
		
		outputThread.start();
		inputThread.start();
	}


	@Override
	public void send(String data) {
		this.outputStreamImpl.send(data);
	}
	@Override
	public void CallBack(String data, Object object) {
		xuLy.xuly(data, this.nhan, this);
	}
}
