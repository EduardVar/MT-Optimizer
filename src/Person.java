/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class Person
{
	private String ID;
	
	private char modality;
	private char ageGroup;
	
	private int hour;
	
	private String date;
	
	public Person(String ID, char modality, char ageGroup, int hour, String date)
	{
		this.ID = ID;
		this.modality = modality;
		this.ageGroup = ageGroup;
		this.hour = hour;
		this.date = date;
	}
	
	
}
