/**
 * Author:	Eduard Varshavsky
 * NetID:	18ev
 * Date:	February 21, 2019
 * Desc:	
 */

public final class Subway extends Vehicle
{
	private int numOfCars;
	private int passPerCar;
	
	private char operationalStatus;
	private String operationalDate;
	
	public Subway(int unitNumber, String idNumber, int capacity, int numOfCars,
			int passPerCar, char operationalStatus, String operationalDate)
	{
		super(unitNumber, idNumber, capacity);
		this.numOfCars = numOfCars;
		this.passPerCar = passPerCar;
		this.operationalStatus = operationalStatus;
		this.operationalDate = operationalDate;
	}
}
