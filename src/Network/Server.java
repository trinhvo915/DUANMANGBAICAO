package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DAO.Nhan;
import GUI.CardData;
import Logic.ConnectedNodeProcessor;

public class Server implements Runnable,Nhan{

	public ServerSocket serverSocket;
	// co để thực hiện luồng
	private boolean listinegFlag = true;
	// tao mang cac client ket noi toi server
	private ArrayList<NodeConnection> connectedNodes;
	// vi tri hien tai cua client
	private int currentPlayer = 0;
	
	
	private CardData[] tableCards;
	public Server(int listeningPort) throws IOException {
		
		this.serverSocket = new ServerSocket(listeningPort);
		this.connectedNodes = new  ArrayList<NodeConnection>();
		this.tableCards = new CardData[4];
		
	}
	
	
	@Override
	public void CallBack(String data, Object object) {
		String key = data.substring(0, 5);
		String code = data.substring(5);
		
		
		 	
	}

	@Override
	public void run() {
		while (true){
			if(this.listinegFlag){

				System.out.println("Waiting for players");
				try {
					
					Socket client = serverSocket.accept();
					NodeConnection player = new NodeConnection(client,this,new ConnectedNodeProcessor());
					player.nhanKetNoiClient();
				} 
				catch (Exception e) {e.printStackTrace();}
			}
			else
			{
				Thread.currentThread().stop();
			}
		}
		
	}

}
