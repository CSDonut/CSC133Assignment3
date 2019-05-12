package com.mycompany.a3;
import java.util.Iterator;

public interface ICollection {
	
	public void add(GameObject x);
	public Iterator getIterator();
	public void remove(GameObject x);

}
