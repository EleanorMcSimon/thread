package view;

public class que {


		  private int [] que;
		  private int nextIn,nextOut,filled,queSize;

		  public que(int size) {
		       que = new int[size];
		       filled = 0;
		       nextIn = nextOut = 0;
		       queSize = size;
		  }

		  public boolean empty() { return (filled == 0); }

		  public boolean full() { return (filled == queSize);  }

		  public synchronized void deposit(int item){
		    try {
		        while (full()) wait();

		        que[nextIn] = item;
		        nextIn = (nextIn+1)%queSize;
			filled++;

		        notifyAll();
		    }
		    catch(InterruptedException e) { }
		   }

		  public synchronized int fetch(){
		    int item = 0;
		    try {
		        // nothing in queue
		        while (empty()) wait();

		        item = que[nextOut];
		        nextOut=(nextOut+1)%queSize;
		        filled--;

		        notifyAll();
		    }
		    catch(InterruptedException e){ }

		    return item;
		 }
		
}
