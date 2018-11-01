package Logic;

import java.util.ArrayList;

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

	public static void sleep(long time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch(Exception ex)
		{
			
		}
	}
}
