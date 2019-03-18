/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	This class is used to store and calculate attributes of a passenger
 * 			Also used to catch any errors in input during instantiation.
 */

import java.util.Map;

public final class Passenger
{
	//In industry, this would use a function to get the current date or the
	//day all the transactions were made. (Use this for project purposes)
	private static String globalDate = "NONE";
	
	//Sets float constants for size passenger takes up based on age
	private static final float CHILD_SIZE = 0.75f;
	private static final float ADULT_SIZE = 1.0f;
	private static final float SENIOR_SIZE = 1.25f;
	
	private String ID;
	
	private char modality;
	private char ageGroup;
	
	private int hour;
	private String date;
	
	private float size = 0;
	
	/**
	 * 
	 * @param item0
	 * @param item1
	 * @param item2
	 * @param item3
	 * @param item4
	 * @throws IDFormatException
	 * @throws AgeFormatException
	 * @throws ModalityFormatException
	 * @throws HourOutOfRangeException
	 * @throws InvalidDateException
	 */
	public Passenger(String item0, String item1, String item2, String item3,
			String item4) throws IDFormatException, AgeFormatException,
			ModalityFormatException, HourOutOfRangeException, 
			InvalidDateException
	
	{	
		this.ID = item0;
		this.modality = item1.length() == 1 ? item1.charAt(0) : '?';
		this.ageGroup = item2.length() == 1 ? item2.charAt(0) : '?';
		this.hour = Integer.parseInt(item3);
		this.date = item4;
		
		checkForExceptions(item1, item2, item3);
		setPassengerSize();
	}

	public void checkForExceptions(String item1, String item2, String item3)
			throws IDFormatException, AgeFormatException,
			ModalityFormatException, HourOutOfRangeException, 
			InvalidDateException
	{
		if (!(ID.length() == 7 || (ID.length() == 16 && ID.charAt(0) == 'T') || ID.equals("*")))
			throw new IDFormatException(ID);
		
		if (!(modality == 'S' || modality == 'G' || modality == 'X' ||
				modality == 'C' || modality == 'D'))
			throw new ModalityFormatException(item1);
		
		if (!(ageGroup == 'C' || ageGroup == 'A' || ageGroup == 'S'))
			throw new AgeFormatException(item2);
					
		if (hour < 1 || hour > 24)
			throw new HourOutOfRangeException(item3);
		
		//In industry, this will check via records if the date matches records
		if (globalDate.equals("NONE"))
			//For this program, set first date in ridership.txt as global date
			globalDate = date;
		else
			//Checks if the date provided does not matches the global date
			if (!(date.equals(globalDate)))
				//Throws invalid date exception to show the date is wrong
				throw new InvalidDateException(date);
	}
	
	public void setPassengerSize()
	{
		switch (ageGroup)
		{
		case 'C':
			size = CHILD_SIZE;
			break;
		case 'A':
			size = ADULT_SIZE;
			break;
		case 'S':
			size = SENIOR_SIZE;
			break;
		}
	}
	
	public void addToInfo(Map<Integer, Map<Character, Float>>allInfo)
	{
		Map<Character, Float> inner = allInfo.get(hour);	
		
		float newSize = inner.get(modality) + size;
		
		inner.put(modality, newSize);		
		allInfo.put(hour, inner);
	}
	
	public int getHour()
	{
		return hour;
	}

	public char getModality()
	{
		return modality;
	}

	public float getSize()
	{
		return size;
	}
}
