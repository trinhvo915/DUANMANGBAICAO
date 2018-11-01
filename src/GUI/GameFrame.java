package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import DAO.Gui;
import DAO.Nhan;
import DAOImpl.Connection;
import Logic.ClientProcessor;



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
