package com.mycompany.a3;
import java.util.Observer;

public interface IObservable {
     public void addObserver(Observer observer);
     public void notifyObservers();
}
