package GUI;

public class CardData {
	private int giaTri;
	private int nuocCo;
	
	public CardData(int giaTri , int nuocCo) {
		// tao du lieu cho nuoc co cua la bai 1 = co ; 2 = ro ; 3 = chuon ; 4 bich
		if(nuocCo > 4) {
			nuocCo = 4;
		}else if (nuocCo < 1) {
			nuocCo = 1;
		}else {
			this.nuocCo = nuocCo;
		}
		// tao du lieu cho gia tri cua la co ; 2-3-4-5-6-7-8-9-10-J-Q-K-A
		if(giaTri > 14) {
			giaTri = 14;
		}else if(giaTri < 1) {
			giaTri = 1;
		}else {
			this.giaTri = giaTri;
		}
		
	}
	
	public void setNuocCo(int nuocCo) {
		this.nuocCo = nuocCo;
	}
	public int getNuocCo() {
		return this.nuocCo;
	}
	public void setGiaTri(int giaTri) {
		this.giaTri = giaTri;
	}
	public int getGiaTri() {
		return this.giaTri;
	}
	
}
