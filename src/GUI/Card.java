package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class Card extends JButton{
	// du liec gom the cardData(giatri , nuocCo);
	private CardData cardData;
	// 1 cai anh
	private BufferedImage imageCard;
	// string chua A - J - Q - K 
	private String lable;
	// ==========/
	int tableIndex = -1;
	
	// tao font chu tren the card J K Q A
	private Font CARDS_FONT  = new Font("Arial",1,15);
	private int R = 10; //  ban kinh
	int BODER = 1; // BODER KHI DUA CHUOT VAO THI SẺ SÁNG LÊN
	
	// constructor
	public Card(CardData cardData) {
		this.cardData = cardData;
		handleImage();
		handleCardValue();
	}
	// ham xu ly lay anh va gan nuoc co
	private void handleImage() {
		if(this.cardData.getNuocCo() <= 1 ) {
			this.imageCard = LinkImages.getCo();
		}else if(this.cardData.getNuocCo() == 2){
			this.imageCard = LinkImages.getRo();
		}else if(this.cardData.getNuocCo() == 3){
			this.imageCard = LinkImages.getChuon();
		}else if(this.cardData.getNuocCo() >=4) {
			this.imageCard = LinkImages.getBich();
		}
	}


	// ham xu ly lay gia tri cua la co ex : 1 or K or Q ..
	private void handleCardValue() {
		if(this.cardData.getGiaTri() == 11) {
			this.lable = "J";
		}else if(this.cardData.getGiaTri() == 12) {
			this.lable = "Q";
		}else if(this.cardData.getGiaTri() == 13) {
			this.lable = "K";
		}else if(this.cardData.getGiaTri() == 14) {
			this.lable = "A";
		}else {
			// doi kieu int => String trong java = String.valueOf();
			this.lable  = String.valueOf(this.cardData.getGiaTri()); 
		}
		
	}

	
	public CardData getCardData() {
		return cardData;
	}
	public void setCardData(CardData cardData) {
		this.cardData = cardData;
	}
	public BufferedImage getImageCard() {
		return imageCard;
	}
	public void setImageCard(BufferedImage imageCard) {
		this.imageCard = imageCard;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	
	public int getTableIndex() {
		return tableIndex;
	}
	public void setTableIndex(int tableIndex) {
		this.tableIndex = tableIndex;
	}
	public Font getCARDS_FONT() {
		return CARDS_FONT;
	}
	public void setCARDS_FONT(Font cARDS_FONT) {
		CARDS_FONT = cARDS_FONT;
	}
	public int getR() {
		return R;
	}
	public void setR(int r) {
		R = r;
	}
	public int getBODER() {
		return BODER;
	}
	public void setBODER(int bODER) {
		BODER = bODER;
	}
	// CUSTOM LẠI JBUTTON ĐỂ HIỂN THỊ THẺ BÀI
	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D graphics2d = (Graphics2D) g;		
	 	paintCardBody(graphics2d);
	}
	// THỰC HIỆN VẺ LÁ CỜ GỒM CUSTOM LẠI KÍCH THỨC LÁ BÀI - CHIỀU CAO,RỘNG MÀU SẮC - VỊ TRÍ CỦA GIÁ TRỊ THẺ BÀI
	private void paintCardBody(Graphics2D graphics2d) {

		//Body
		GradientPaint bodyGradient = new GradientPaint(200 - this.getWidth()/2,0, Color.white, 200- this.getWidth()/2, this.getHeight() + 250, Color.yellow);
		graphics2d.setPaint(bodyGradient);
		graphics2d.fillRoundRect(0, 0, this.getWidth() - (BODER), this.getHeight() - (BODER), R, R);
		
		//Frame
		graphics2d.setColor(Color.BLACK);		
		graphics2d.drawRoundRect(0, 0, this.getWidth() - (BODER), this.getHeight() - (BODER ), R, R);
	
		//Image
		double factor = 75/100.0;
		int imgDim;
		if   (this.getWidth() < this.getHeight()) imgDim =(int) (this.getWidth() * factor);
		else imgDim =(int) (this.getHeight() * factor);
		
		int xPos =  1 + (this.getWidth() - imgDim) / 2;
		int yPos =  1 + (this.getHeight() - imgDim) / 2;
		graphics2d.drawImage(this.imageCard, xPos, yPos, imgDim, imgDim, this);
		
		//Label		
		int sxPos1  = 5;
		int syPos1 = 15;
		int sxPos2  = this.getWidth() - (15 + ((lable.length()-1) * 5)) ;
		int syPos2 = this.getHeight() - 5;
		graphics2d.setFont(CARDS_FONT);
		graphics2d.setColor(Color.black);
		graphics2d.drawString(this.lable, sxPos1, syPos1);
		graphics2d.drawString(this.lable, sxPos2, syPos2);
	}
	// CUSTOM LAI BORDER THẺ BÀI KHI RÊ CHUỘT VÀO SẺ SÁNG LÊN
	@Override
	protected void paintBorder(Graphics g) {
		//Frame
		Graphics2D graphics2d = (Graphics2D) g;
		
		Point p = (this.getMousePosition());
		if(p != null){
			if(this.contains(p) && this.tableIndex ==-1)
			{
				g.setColor(Color.yellow);		
				g.drawRoundRect(0, 0, this.getWidth() - (BODER), this.getHeight() - (BODER ), R, R);
		
			}
			
		}
	}
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(50, 70);
	}
	// XUẤT GIÁ TRỊ VÀ NƯỚC CỜ THẺ BÀI
	@Override
	public String toString() {
		return this.cardData.getGiaTri() +","+ this.cardData.getNuocCo();
	}
}
