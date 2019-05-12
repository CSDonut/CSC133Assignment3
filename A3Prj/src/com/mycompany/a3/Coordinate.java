package com.mycompany.a3;

public class Coordinate {
	private double x, y;
	
	//constructor
	public Coordinate(){
		x = 0.0;
		y = 0.0;
	}
	
	//Accessors and Mutators for x and y coordinate
	public Coordinate(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setCoordinateX(double x){
		this.x = x;
	}
	
	public double getCoordinateX(){
		return x;
	}
	
	public void setCoordinateY(double y){
		this.y = y;
	}
	
	public double getCoordinateY(){
		return y;
	}
}
