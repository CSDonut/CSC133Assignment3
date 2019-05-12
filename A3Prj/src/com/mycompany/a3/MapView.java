package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameCollection.VectorIterator;

public class MapView extends Container implements Observer, IObserver{
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		this.gw = gw;
//		origin = new Coordinate();
		Container mapContainer = new Container();
		mapContainer.setLayout(new BorderLayout());
		this.add(mapContainer);
	}
	
    public void update(Observable observable, Object gameWorldObject) {
//    	VectorIterator collection = ((IGameWorld)gameWorldObject).getCollection().getIterator();
//    	  System.out.println("========================================");
//    	  while(collection.hasNext()){
//    	  	System.out.println(collection.next().toString());
//    	  }
//    	  System.out.println("Width: " + getWidth());
    	 repaint();
    	 
    } 
    
    @Override
	public void paint(Graphics g) {
    	super.paint(g);

    	VectorIterator vector  = gw.getCollection().getIterator();
    	while(vector.hasNext()){
  	  		vector.next().draw(g, new Point(getX(), getY()));		
  	  	}
	}	
    
	@Override
	public void pointerPressed(int x, int y) {
		VectorIterator vector = gw.getCollection().getIterator();
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		while (vector.hasNext()) {
			GameObject temp = (GameObject) vector.next();
			if (temp instanceof ISelectable) {
				if (((ISelectable)temp).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((ISelectable)temp).setSelected(true);
				} else {
					((ISelectable)temp).setSelected(false);
				}
			}

		}
		repaint();
	}
}



 