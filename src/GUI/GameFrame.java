package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import DAO.Gui;
import DAO.Nhan;
import DAOImpl.Connection;
import Logic.ClientProcessor;
import Logic.Logic;



public class GameFrame extends JFrame implements Gui,Nhan{

	private BanDanh banDanh;
	private BanChat banChat;
	private Connection connection;
	
	
	
	private String PlayerName;
	private int Index;
	private int CurrentPlayer = 0;
	private int tableSuite = -1;
	
	@Override
	public void CallBack(String data, Object object) {
		String key = data.substring(0, 6);
		String code = data.substring(6);
		if(key.equals("S_Name")) {
			connection.send("C_Name"+this.PlayerName); // C_Name = Client_Name
		}else if(key.equals("S_size")) {
			this.Index = Integer.parseInt(code);
			System.out.println("Vi tri da thiet lap !!");
		}else if(key.equals("S_indi")) {
			int index = Integer.parseInt(code.split(",")[0]);
			String name = code.split(",")[1];
			this.banChat.AddPlayer(name,index);
		}else if(key.equals("S_smsC")) {
			System.out.println("Nhan danh sach client !!");
			String [] players = code.split("-");
			// ban đầu có 1 client kết nối server nên server gui về sẻ ko có client đầu tiên
			// nên bắt try catch
			for (int i = 0; i < players.length; i++) {
				try {
					String name =(players[i].split(",")[1]);
					int indx = Integer.parseInt(players[i].split(",")[0]);
					this.banChat.AddPlayer(name, indx);
				}catch (Exception e) {
				}
			}
			// ban đâu chưa có nodeConnettion ko thêm vào nên client phải tự add vào list ds
			this.banChat.AddPlayer(this.PlayerName, 0);
		}else if(key.equals("3_card")){
			this.banDanh.ShowCards(Logic.ViewsCards(code));
		}else if(key.equals("C_CHAT")) {
			this.connection.send("C_CHAT"+this.PlayerName+":"+code);
			
		}else if (key.equals("S_CHAT"))
		{
			System.out.println("nhan tin nhan !!");
			this.banChat.AppendToChat(code);
		}
		
	}

	@Override
	public void send(String data) {
		this.connection.send(data);
		
	}
	
	public GameFrame(String IP , int Port,String PlayerName) throws IOException {

		this.setBackground(Color.black);
		this.PlayerName = PlayerName;
		this.connection = new Connection(IP, Port, this, new ClientProcessor());
		
		this.setTitle("Bai Cao - Mang LAN");
		this.setSize(900, 500);
				
		this.setLayout( new BorderLayout(5, 5) );
		
		this.banDanh = new BanDanh(this);
		banDanh.setBackground(Color.black);
		
		this.banChat = new BanChat(this);
		this.banChat.setBackground(Color.black);
		
		this.add(banChat,BorderLayout.EAST);
		
		this.add(banDanh,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println("Tao Khung Chuong trinh !!!");
	}	
	

	public BanDanh getBanDanh() {
		return banDanh;
	}

	public void setBanDanh(BanDanh banDanh) {
		this.banDanh = banDanh;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public int getIndex() {
		return Index;
	}

	public void setIndex(int index) {
		Index = index;
	}

	public int getCurrentPlayer() {
		return CurrentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		CurrentPlayer = currentPlayer;
	}

	public int getTableSuite() {
		return tableSuite;
	}

	public void setTableSuite(int tableSuite) {
		this.tableSuite = tableSuite;
	}
	
	

}
