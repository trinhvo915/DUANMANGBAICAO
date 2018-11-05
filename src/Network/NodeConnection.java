package Network;

import java.io.IOException;
import java.net.Socket;

import DAO.Nhan;
import DAO.XuLy;
import DAOImpl.Connection;
import DAOImpl.InputSteamImpl;
import DAOImpl.OutputStreamImpl;

public class NodeConnection extends Connection{
	// ten nguoi choi
	private String playerName;
	// vi tri nguoi choi
	private int playerIndex;
	// diem nguoi choi
	private int Score =0;
	
	public NodeConnection(Socket socket , Nhan nhan, XuLy xuLy) throws IOException {
		this.nhan = nhan;
		this.socket = socket;
		this.outputStreamImpl = new OutputStreamImpl(this.socket);
		this.inputSteamImpl = new InputSteamImpl(socket, this);
		this.xuLy = xuLy;
		
		Thread outputThread = new Thread(outputStreamImpl);
		Thread inputThread = new Thread(inputSteamImpl);
		
		outputThread.start();
		inputThread.start();
	}

	@Override
	public void CallBack(String data, Object object) {
		try {
			this.xuLy.xuly(data, nhan, this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void send(String data) {
		try {
			this.outputStreamImpl.send(data);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}
	// cong diem cho client win
	public void AddScore(int Score){
		this.Score += Score;
	}

	public void nhanKetNoiClient() {
		this.outputStreamImpl.send("S_Nhan");
		System.out.println("Server da nhan 1 cliet ket noi !!");
	}

	

}
