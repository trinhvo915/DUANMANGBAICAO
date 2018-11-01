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
		String key = data.substring(0, 5);
		String code = data.substring(5);
		
		
	}

	@Override
	public void xuly(String data) {
		xuly(data, callback, sender);
	}
	

}
