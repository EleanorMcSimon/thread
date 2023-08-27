package view;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import Bikes.bikestakc;
import renters.renter;
import shop.bikeshop;

public class view {
	static Semaphore semaphore = new Semaphore(20);
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("days of opperration to simulate");
		 int num = scan.nextInt();
		 num=num*23;
		bikestakc in = new bikestakc();
		in.setbasebike();
		bikeshop ethans = new bikeshop(in,semaphore);
	//	bikeshop human = new bikeshop(in,semaphore);
		//bikeshop fred = new bikeshop(in,semaphore);
		ethans.start();
		for(int hours = 0; hours < num; hours++)
		{
		//	System.out.println("Total available Semaphore permits : "
	             //   + semaphore.availablePermits());
			renter p = new renter(ethans,in,semaphore);
	
			renter m = new renter(ethans,in,semaphore);
			renter n = new renter(ethans,in,semaphore);
			p.start();
			n.start();
			m.start();
		
			
			m.run();
			p.run();
		  n.run();
			
			
			
	
				ethans.run();
			
			
			
			
		}
	}

}
