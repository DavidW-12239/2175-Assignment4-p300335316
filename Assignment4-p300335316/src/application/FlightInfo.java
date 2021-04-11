package application;


import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class FlightInfo implements Serializable{

	final String name;
	final String number;
	final String depAirport;
	final String arrivalAirport;
	
	public FlightInfo(String name, String number, String depAirport, String arrivalAirport) {
		super();
		this.name = name;
		this.number = number;
		this.depAirport = depAirport;
		this.arrivalAirport = arrivalAirport;	
	}
	
	public String nameProperty() {
		return this.name;
	}
	
	public String numberProperty() {
		return this.number;
	}
	
	public String depAirportProperty() {
		return this.depAirport;
	}
	
	public String arrivalAirportProperty() {
		return this.arrivalAirport;
	}
	
	public String toString() {
		return name + "\t" + number + "  \t\t  " + depAirport + "  \t\t  " + arrivalAirport;
	}
	
    

}
