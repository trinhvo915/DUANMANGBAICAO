package Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import GUI.CardData;

public class Logic {
	
	
	
	public static boolean has(int suit , ArrayList<CardData> cards )
	{
		boolean result = false;
		for(CardData card :cards)
		{
			result = result || (card.getNuocCo() ==  suit);
		}
		return result;
	}	

	public static void sleep(long time){
		try
		{
			Thread.sleep(time);
		}
		catch(Exception ex)
		{
			
		}
	}
	// chia 3 lá bài
	private static ArrayList<CardData> Chia_3_Card()
	{
		ArrayList<CardData>  cards = new ArrayList<CardData>();
		
		for(int i = 0 ; i < 4 ; i ++)
		{
			for (int j = 0; j < 13; j++) {
				cards.add( new CardData(j+2,i+1));
				
			}
		}
		return cards;
	}
	// trả lại mỗi client 3 lá bài đã được sắp xếp
	public  static ArrayList<ArrayList<CardData>> ChiaBaiChoClients()
	{
		ArrayList<ArrayList<CardData>> ArrayCardClients = new ArrayList<ArrayList<CardData>>();
		ArrayList<CardData> allCards  = Chia_3_Card();
		
		
		
		boolean [] selected = new boolean[52];
		
		for(int i = 0 ;i<52; i ++) selected[i]= false;
		
		Random rnd = new Random(new Date().getTime());
		int choice = rnd.nextInt(52);
		
		for(int i = 0; i < 4 ; i ++ )
		{
			ArrayList<CardData> tmp = new ArrayList<CardData>();
			
			for(int j = 0 ; j<3 ; j++)
			{	
				while(selected[choice])
				{
					choice = rnd.nextInt(52);
				}
				
				tmp.add(allCards.get(choice));
				selected[choice]= true;
			}
			// sắp xếp theo nước cờ và giá trị lá bài
			ArrayCardClients.add(SortToSuits(QuickSort(tmp)));
		}
		
		
		return ArrayCardClients;
		
		
	}
	// sắp xếp theo cơ - ro - chuon - bich
	private static ArrayList<CardData> SortToSuits(ArrayList<CardData> Arrsort) {
		ArrayList<CardData> Sorted = new ArrayList<CardData>();
		int index = 0;
		
		for(int i = 0 ; i < 4; i ++)
		{
			for(int j = 0 ; j < Arrsort.size();j++)
			{
				if((i + 1) == Arrsort.get(j).getNuocCo())
				{
					Sorted.add( Arrsort.get(j));
				}
			}		
			
			index++;
		}
		
		return Sorted;
	}
	// sắp xếp theo giá trị thẻ bài 2-3-4 - ... - J-Q-K-A
	private static ArrayList<CardData> QuickSort(ArrayList<CardData> list) {
		if(list.size() <=1) {
			return list;
		}
		
		ArrayList<CardData> arrsLon = new ArrayList<CardData>();
		ArrayList<CardData> arrNho = new ArrayList<CardData>();
		CardData cardData = list.get(0);
		
		for(int i = 1 ; i < list.size() ; i++)
		{
			if (cardData.getGiaTri() > list.get(i).getGiaTri()) {
				arrsLon.add(list.get(i));
			}else {
				arrNho.add(list.get(i));
			}
		}
		
		arrNho = QuickSort(arrNho);
		arrsLon = QuickSort(arrsLon);
		
		arrNho.add(cardData);
		arrNho.addAll(arrsLon);
		
		return (arrNho);
	}

	public static String arrCard(ArrayList<CardData> Cards) {
		String strCards = "";
		for(CardData card : Cards)
		{
			strCards += "" + card.getGiaTri() + "," + card.getNuocCo()+ "-";
		}
		return strCards;
	}
	
	public static ArrayList<CardData> ViewsCards(String StrCards)
	{
		ArrayList<CardData> Cards = new ArrayList<CardData>();
		
		String[] clst = StrCards.split("-");
		
		for(int i = 0 ; i<clst.length;i++)
		{
			int value = Integer.parseInt(clst[i].split(",")[0]);
			int suit = Integer.parseInt(clst[i].split(",")[1]);
			Cards.add(new CardData(value, suit));
		}
		
		return Cards;
	}
}
