package Bikes;

import java.util.Stack;

public class bikestakc {
	Stack<bike> bikes = new Stack<>();
public void setbasebike() {
	bike b = new bike();
			b.setprice(2.00);
	b.setnumber(3);
	b.setmodel("BigRed");
	bike n = new bike();
	n.setprice(5.00);
n.setnumber(4);
n.setmodel("BlackAndYellow");
bike no = new bike();
no.setprice(0.99);
no.setnumber(10);
no.setmodel("realDeal");
bike que = new bike();
que.setprice(10.00);
que.setnumber(2);
que.setmodel("Rich only");
bikes.add(no);
bikes.add(que);
bikes.add(n);
bikes.add(b);
}
public void removebike(int index)
{
	bikes.remove(index);
}
public int bikesize()
{
	return bikes.size();
}
public void passinbike(bike b) {bikes.add(b);}
public bike getbike(int f){
	return bikes.get(f);
}
public void addNewbike( double ou, int numbe, String name)
{
	bike cool = new bike();
	cool.setprice(ou);
	cool.setnumber(numbe);
	cool.setmodel(name);
	bikes.add(cool);
}
public int amoutofbikes()
{ int b = 0;

	for(int u=0; u < bikes.size(); u++)
	{
		b =+ bikes.get(u).getNumber();
	}
	return b;
}
}
