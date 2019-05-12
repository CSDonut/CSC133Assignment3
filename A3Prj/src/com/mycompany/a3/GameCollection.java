package com.mycompany.a3;
import java.util.Iterator;
import java.util.Vector;


public class GameCollection implements ICollection {

	private Vector<GameObject> collection;
	
	public GameCollection() {
		collection = new Vector<GameObject>();
	}
	
	public void add(GameObject obj) {
		collection.addElement(obj);
	}
 
	public VectorIterator getIterator() {
		return new VectorIterator();
	}

	public void remove(GameObject obj) {
		
		collection.remove(obj);
	}
	
	public class VectorIterator implements Iterator {

		private int currElementIndex;
		
		public VectorIterator() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			
			if (collection.size() <= 0) {
				return false;
			}
			if(currElementIndex == collection.size() -1) {
				return false;
			}
			return true;
		}

		@Override
		public GameObject next() {
			currElementIndex++;
			return collection.elementAt(currElementIndex);
		}

		
		public void remove() {
			
		}



	}
	

}
