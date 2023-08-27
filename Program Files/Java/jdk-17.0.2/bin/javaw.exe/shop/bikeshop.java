package shop;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

import Bikes.bike;
import Bikes.bikestakc;
import renters.renter;

public class bikeshop  extends Thread {
	
	bikestakc b;
	double madeMoney= 0.00;
	  private Queue buffer;
	static Semaphore semaphore;
	  @Override
	    public void run() {
		
		  try {
				
				semaphore.acquire();
				semaphore.tryAcquire(NORM_PRIORITY);
				//System.out.print(semaphore.availablePermits());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		  cleanandfixbikes();
		  sellotheriteams();
		  System.out.println(madeMoney);
		  semaphore.release();
	  }

	public bikeshop(bikestakc n, Semaphore s)
	{
		this.b = n;
	this.semaphore =s;
	}

	public void cleanandfixbikes()
	{
		for(int n = 0; n < b.bikesize(); n++)
		{
	bike s	= b.getbike(n);
		b.removebike(n);
		b.passinbike(s);
		}
	}
public void sellotheriteams()
{

Random r = new Random();
	double randomValue = 5 + (1000 - 5) * r.nextDouble();
	double did = 0 + (100 - 0) * r.nextDouble();
	if(did >= 50)
	{
		madeMoney += did;
	}
}
public void getNewBike(double cost, int amout, String n)
{
	b.addNewbike(cost, amout, n);
}
synchronized public double renturnbike(double damage,double time, int bike)
{
if(bike != -1)
{
	double recost = 0.0;
	damage *= 50;
	recost = b.getbike(bike).getprice()*time+damage;
	int hero = b.getbike(bike).getNumber();
	hero++;
	
	b.getbike(bike).setnumber(hero);
	System.out.print("bikein"+b.getbike(bike).getModel());
	madeMoney = madeMoney +recost;
	 System.out.println(madeMoney);
	return recost;
}
return 0.00;
}
synchronized public int rentoutbike(double buget, double time){
	bike check;
	int biketoget = -1;
	double fullbuget = buget*time;
	//System.out.println(fullbuget);
	for(int n = 0; n < b.bikesize(); n++)
	{
		double cost = b.getbike(n).getprice()*time;
		//System.out.println("c"+ cost);
	if(buget >= cost)
	{
		if(biketoget != -1)
		{
			double oldCost = b.getbike(biketoget).getprice()*time;
			if(oldCost <= cost)
			{
				biketoget = n;	
			}
			
			
		}
		else {
			biketoget = n; 
		
		}
		
	}
	}
	if(biketoget != -1)
	{
		int ru =b.getbike(biketoget).getNumber();
		
		if(ru == 0)
		{
		return -1;	
		}
		else
		{
			ru--;
			//b.getbike(biketoget).setnumber(ru);
		//	System.out.print(biketoget);
			return biketoget;		
		}
	
	}
	else
	{
		return -1;
	}
	
}
	  
}
