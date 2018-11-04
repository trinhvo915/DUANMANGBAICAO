package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DAO.Nhan;
import GUI.CardData;
import Logic.ConnectedNodeProcessor;
import Logic.Logic;

public class Server implements Runnable,Nhan{

	public ServerSocket serverSocket;
	// co để thực hiện luồng
	private boolean listinegFlag = true;
	// tao mang cac client ket noi toi server
	private ArrayList<NodeConnection> connectedNodes;
	// vi tri hien tai cua client
	private int currentPlayer = 0;
	private int tablehead = 0;
	
	private CardData[] tableCards;
	private ArrayList<Integer> gameModes; // chế độ chơi
	public Server(int listeningPort) throws IOException {
		
		this.serverSocket = new ServerSocket(listeningPort);
		this.connectedNodes = new  ArrayList<NodeConnection>();
		this.tableCards = new CardData[4];
		this.gameModes = new ArrayList<Integer>();
	}
	
	
	@Override
	public void CallBack(String data, Object Caller) {
		String key = data.substring(0, 6);
		String code = data.substring(6);
		if(key.equals("C_Name")) {
			// nhan node cua client nao gui den
			NodeConnection node = (NodeConnection) Caller;
			// gui lai vi tri cua client ket noi
			node.send("S_size"+connectedNodes.size());
			Logic.sleep(1000);
			
			String S_smsC = "";
			String inforClient = ""; // thông tin các client với vị trí,và tên client 
			for (NodeConnection nodeClients : connectedNodes) {
				// gui lại vị trí và tên client
				nodeClients.send("S_indi"+connectedNodes.size()+","+node.getPlayerName());
				// gui lại vị trí và tên client lại các client khác
				S_smsC += nodeClients.getPlayerIndex()+","+nodeClients.getPlayerName()+"-";	
			}
			node.setPlayerIndex(connectedNodes.size());
			// thêm vào mảng các node của cliet nào
			this.connectedNodes.add(node);
			if(connectedNodes.size() !=0) {
				node.send("S_smsC"+S_smsC);
			}
			// nêu có 4 client kết nối tới thì bắt đầu chơi
			if(connectedNodes.size() == 4){
				try {
					Logic.sleep(1000);
					sendToAll("_Start");
					this.listinegFlag=false;
					Logic.sleep(2000);
					// chia bài 3 lá cho clients
					ArrayList<ArrayList<CardData>> Cards = Logic.ChiaBaiChoClients();
					
					// gửi bộ 3 lá bài lại mỗi client
					for(int i =0 ;i<4; i++)
					{
						ArrayList<CardData> clst = Cards.get(i);
						String strCLst = Logic.arrCard(clst);
						connectedNodes.get(i).send("3_card" + strCLst);
					}


					Logic.sleep(1000);

//					connectedNodes.get(tablehead).send("S_STH");
//					Logic.sleep(1000);

					this.gameModes.add(new Integer(5));

				} catch (Exception e) {
					// TODO: handle exception
				}
			}	
		}else if (key.equals("C_CHAT"))
		{
			sendToAll("S_CHAT" + code);
			Logic.sleep(1000);
		}

		 	
	}

	private void sendToAll(String data) {
		try {
			for (NodeConnection nodeClients : connectedNodes) {
				nodeClients.send(data);
			}
		} catch (Exception e) {
			System.out.println("Khong co client nao nhan tin");
		}
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
