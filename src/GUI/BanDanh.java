package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Logic.Logic;

public class BanDanh extends JPanel implements ActionListener{
	JPanel cardsPanel,tablePanel;
	ArrayList<CardData> cards;
	GameFrame gameFrame;
	
	public BanDanh(GameFrame gmfrm ) {
		
		this.gameFrame = gmfrm;
		this.setLayout(new BorderLayout(5, 5));
		
		this.cardsPanel = new JPanel();
		this.cardsPanel.setBackground(Color.DARK_GRAY);
		this.cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,10));
		
		this.tablePanel = new JPanel();
		this.tablePanel.setBackground(Color.orange);
		this.tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//this.tablePanel.setLayout(new TableLayOut(10, false));
		
		
		this.add(cardsPanel,BorderLayout.SOUTH);
		this.add(tablePanel,BorderLayout.CENTER);
		
		System.out.println("Ban danh da tao!!");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameFrame.getCurrentPlayer() == gameFrame.getIndex())
		{

			Card card = (Card)e.getSource();
			//tablePanel.setComponentZOrder(cTmp, f++);
			if(   gameFrame.getTableSuite() == -1 || 
					card.getCardData().getNuocCo() == gameFrame.getTableSuite()|| 
					!Logic.has(gameFrame.getTableSuite(), this.getCards())					)
			{
				this.cardsPanel.remove(card);
				this.cardsPanel.repaint();
				this.tablePanel.repaint();
				this.revalidate();
				gameFrame.send("C_CPD"+ card.toString());
				Logic.sleep(1000);
				gameFrame.setCurrentPlayer(-1);
			}
		}
		
	}
	// lay cac the bài
	public ArrayList<CardData> getCards()
	{
		ArrayList<CardData> cards = new ArrayList<CardData>();
		for(int i = 0;i<cardsPanel.getComponentCount();i++)
		{
			if(cardsPanel.getComponent(i) instanceof Card)
			{
				cards.add(((Card)cardsPanel.getComponent(i)).getCardData());
			}
		}
		return cards;
	}

}
