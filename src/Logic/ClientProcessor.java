package Logic;

import DAO.Gui;
import DAO.Nhan;
import DAO.XuLy;

public class ClientProcessor implements XuLy{
	private Nhan callback;
	private Gui sender;
	
	public ClientProcessor() {
		
	}
	
	public ClientProcessor(Nhan callback, Gui sender) {
		this.callback = callback;
		this.sender = sender;
	}

	@Override
	public void xuly(String data, Nhan nhan, Gui send) {
		String key = data.substring(0, 6);
		String code = data.substring(6);
		if(key.equals("S_Nhan")) { // nhan Server_Nhan
			send.send("C_Nhan"); // gui Client Nhan
		}else if(key.equals("S_Name")) {
			nhan.CallBack(key, send);
		}else if(key.equals("S_size")) {
			System.out.println("Vi tri cua ban : "+code);
			nhan.CallBack(data, send);
		}
		
		
		
	}

	@Override
	public void xuly(String data) {
		xuly(data, this.callback, this.sender);
	}
	

}
