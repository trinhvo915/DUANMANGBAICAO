package Logic;

import DAO.Gui;
import DAO.Nhan;
import DAO.XuLy;
import Network.NodeConnection;

public class ConnectedNodeProcessor implements XuLy{
	private Nhan callback;
	private Gui sender;
	
	public ConnectedNodeProcessor() {
	}
	public ConnectedNodeProcessor(Nhan callback, Gui sender) {
		this.callback = callback;
		this.sender = sender;
	}
	@Override
	public void xuly(String data, Nhan nhan, Gui send) {
		String key = data.substring(0, 6);
		String code = data.substring(6);
		if(key.equals("C_Nhan")) {// nhan tu Client
			System.out.println("Nhan thanh cong Client !!!");
			System.out.println("Yeu cau nhap ten !!!");
			send.send("S_Name"); // gui Client de nhan Username Client
		}else if(key.equals("C_Name")) {
			((NodeConnection)send).setPlayerName(code);
			System.out.println("Player Name : "+code);
			nhan.CallBack(key, send);	
		}else if(key.equals("C_CHAT")) {
			nhan.CallBack(data, send);
			System.out.println("Chat"+code);
		}
		
	}
	@Override
	public void xuly(String data) {
		xuly(data, callback, sender);
	}
	

	

}
