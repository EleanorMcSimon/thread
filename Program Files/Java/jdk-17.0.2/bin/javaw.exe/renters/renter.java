package renters;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import Bikes.bikestakc;
import shop.bikeshop;

public class renter  extends Thread {
	bikestakc bike;
	bikeshop bikeShop;
	double buget;
	double wantedtime;
	double damage = 0.0;
	int bikes;
	  private Queue buffer;
	static Semaphore semaphore;
	
	public renter(bikeshop n, bikestakc holo,Semaphore s)
	{
		this.bikeShop = n;
	
		this.bike = holo;
		this.semaphore = s;
		
	}
	public int getRandomNumberUsingNextInt(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	public void setbuget()
	{
		Random r = new Random();
		double randomValue = 2 + (150 - 2) * r.nextDouble();
		buget = randomValue;
		double rn = 0.30 + (24 - 0.30) * r.nextDouble();
		wantedtime = rn;
	}
	public void rentbike()
	{
	
		
		bikes = bikeShop.rentoutbike(buget, wantedtime);
		if(bikes != -1)
		{
		System.out.println("bike Out"+bike.getbike(bikes).getModel());
		}
	}
	public void Givebackbike()
	{
		
		bikeShop.renturnbike(damage, wantedtime, bikes);
	}
	public void timeonbike()
	{
		int random = getRandomNumberUsingNextInt(0,50);
	
		if(random > 39)
		{
			wantedtime++;
		}
		if(random < 5)
		{
			switch(random)
			{
			case 4: damage++;
			break;
			case 3: damage=+1.25;
			break;
			case 2: damage=+2.00;
			break;
			case 1: damage=+3.25;
			break;
			case 0: damage=+10;
			}
		}
	}


	  @Override
	    public void run() {
		  
			
			  setbuget();
			  try {
					
					semaphore.acquire();
					semaphore.tryAcquire(NORM_PRIORITY);
					
					rentbike(); 
					//System.out.print(semaphore.availablePermits());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
			  
                  
                  // calling release() after a successful acquire()
              //    System.out.println(name + " : releasing lock...");
                  
				if(bikes == -1)
				{
					semaphore.release();
					try {
						  Thread.sleep(1000);
						run();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}             
			  
			 
			 
			  
              
	if(bikes != -1)
	{
		  timeonbike();
		  int timeout = (int) wantedtime * 5;
		  try {
			  semaphore.release();
			
			Thread.sleep(timeout);
System.out.print(Thread.activeCount());

Givebackbike();


		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		
		
	
	}	

	  }
	
	

}
