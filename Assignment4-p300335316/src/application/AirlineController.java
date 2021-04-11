package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class AirlineController implements Initializable{
	
	ObjectOutputStream ob;

    @FXML
    private AnchorPane sss;
    @FXML
    private RadioButton R_airline;
    @FXML
    private RadioButton R_airlineNum;
    @FXML
    private RadioButton R_depAirport;
    @FXML
    private RadioButton R_arrivalAirport;
    @FXML
    private ToggleGroup Search_option;
    @FXML
    private ChoiceBox<String> airline;
    @FXML
    private ChoiceBox<String> airline_Num;
    @FXML
    private ChoiceBox<String> depAirport;
    @FXML
    private ChoiceBox<String> arrivalAirport;
    @FXML
    private Button search;
    @FXML
    private Button close;
    @FXML
    private ListView<String> list;

    FlightInfo[] flightInfo = new FlightInfo[100];
    int i = 0;
    String c1 = null;
	String c2 = null;
	String c3 = null;
	String c4 = null;
	ObservableList<String> Info = FXCollections.observableArrayList ("Airline" + "\t\t" + "AirlineNo" + "\t\t" + "Departure" + "\t" + "Arrival");
    
	private ObservableList<String> AirlineSelection = FXCollections.observableArrayList("Air Canada","Air India");
	private ObservableList<String> NumSelection = FXCollections.observableArrayList("AC102","AC104","AC107","AC113","AI011");
	private ObservableList<String> DepAirportSelection = FXCollections.observableArrayList("YVR","YYZ");
	private ObservableList<String> ArrivalAirportSelection = FXCollections.observableArrayList("YVR","YYZ","NRT");
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try
		{   //Populate choice box
			airline.setDisable(false);
			airline.getItems().addAll(AirlineSelection);
			airline.setValue("Select Airline");
			
			airline_Num.setDisable(false);
			airline_Num.getItems().addAll(NumSelection);
			airline_Num.setValue("Select Airline Number");
			
			depAirport.setDisable(false);
			depAirport.getItems().addAll(DepAirportSelection);
			depAirport.setValue("Select Departure Airport");
			
			arrivalAirport.setDisable(false);
			arrivalAirport.getItems().addAll(ArrivalAirportSelection);
			arrivalAirport.setValue("Select Arrival Airport");		
		}
			catch(NullPointerException e){
				e.getMessage();
				}
		
		
		try
		{
			//To open input stream
			FileReader fr=new FileReader("D:\\Flight.txt");
			BufferedReader br=new BufferedReader(fr);
			String buffer = null;
		
			//while there is a next line
			while((buffer=br.readLine())!=null) {
				c1 = buffer.split(",")[0];
			    c2 = buffer.split(",")[1];
			    c3 = buffer.split(",")[2];
			    c4 = buffer.split(",")[3];
			    FlightInfo flight = new FlightInfo(c1, c2, c3, c4);
				flightInfo[i] = flight;		
				i++;
			}
			//Close a file
			br.close();
		}
		catch(IOException e)
		{
			System.out.print("File is not there "+e);
		}
		
		list.setItems(Info);
	}
	
	@FXML
	void Airline_onclick(ActionEvent event) throws IOException {
		airline.setDisable(false);
		airline_Num.setDisable(true);
		depAirport.setDisable(true);
		arrivalAirport.setDisable(true);
	    }
	@FXML
	void AirlineNum_onclick(ActionEvent event) throws IOException {
		airline.setDisable(true);
		airline_Num.setDisable(false);
		depAirport.setDisable(true);
		arrivalAirport.setDisable(true);
		}
	@FXML
	void DepAirport_onclick(ActionEvent event) throws IOException {
		airline.setDisable(true);
		airline_Num.setDisable(true);
		depAirport.setDisable(false);
		arrivalAirport.setDisable(true);
		}
	@FXML
	void ArrivalAirport_onclick(ActionEvent event) throws IOException {
		airline.setDisable(true);
		airline_Num.setDisable(true);
		depAirport.setDisable(true);
		arrivalAirport.setDisable(false);
		}
	
	@FXML
	void onsearch(ActionEvent event) {
		list.getItems().clear();
		ObservableList<String> Info = FXCollections.observableArrayList ("Airline" + "\t\t" + "AirlineNo" + "\t\t" + "Departure" + "\t" + "Arrival");
		list.setItems(Info);
		String airline1;
		String airline_Num1;
		String depAirport1;
		String arrivalAirport1;
		ObservableList<String> Info1 = null;
		
	    if (R_airline.isSelected()) {
	    	airline1 = airline.getSelectionModel().getSelectedItem();
	    	for (i=0; i<5; i++) {
	    		if (flightInfo[i].name.equals(airline1)) {
	    			Info1 = FXCollections.observableArrayList (flightInfo[i].toString());
	    			list.getItems().addAll(Info1);
	    		}
	    	}
	    }
	    if (R_airlineNum.isSelected()) {
	    	airline_Num1 = airline_Num.getSelectionModel().getSelectedItem();
	    	for (i=0; i<5; i++) {
	    		if (flightInfo[i].number.equals(airline_Num1)) {
	    			Info1 = FXCollections.observableArrayList (flightInfo[i].toString());
	    			list.getItems().addAll(Info1);
	    		}
	    	}
	    }
	    if (R_depAirport.isSelected()) {
	    	depAirport1 = depAirport.getSelectionModel().getSelectedItem();
	    	for (i=0; i<5; i++) {
	    		if (flightInfo[i].depAirport.equals(depAirport1)) {
	    			Info1 = FXCollections.observableArrayList (flightInfo[i].toString());
	    			list.getItems().addAll(Info1);
	    		}
	    	}
	    }
	    if (R_arrivalAirport.isSelected()) {
	    	arrivalAirport1 = arrivalAirport.getSelectionModel().getSelectedItem();
	    	for (i=0; i<5; i++) {
	    		if (flightInfo[i].arrivalAirport.equals(arrivalAirport1)) {
	    			Info1 = FXCollections.observableArrayList (flightInfo[i].toString());
	    			list.getItems().addAll(Info1);
	    		}
	    	}
	    }
		
	}
	
	
	
	@FXML
	void onclose(ActionEvent event) {
		Platform.exit();
	}
}

