/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class GoTrain extends Vehicle
{
	public GoTrain(String unitNumber, String idNumber, String capacity)
	{
		super(unitNumber, idNumber, capacity);
	}

	@Override
	public String toString()
	{
		return "GoTrain [unitNumber=" + unitNumber + ", idNumber=" + idNumber +
				", capacity=" + capacity + "]";
	}
}
