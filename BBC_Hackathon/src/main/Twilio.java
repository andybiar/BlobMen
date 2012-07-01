package main;

import processing.core.*;
import processing.net.*;


public class Twilio extends PApplet{
	
	
public Server server;
public int port;

public String listenNums(){
	 Client thisClient = server.available();
	  // If the client is not null, and says something, display what it said
	  if (thisClient !=null) {
	    String whatClientSaid = thisClient.readString();
	    if (whatClientSaid != null) {
	    	return whatClientSaid;
	    	
	    	//thisClient.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Gather timeout=\"10\" finishOnKey=\"*\"><Say>Please enter your pin number and then press star.</Say></Gather></Response>");
	    	}
	    else{println("Nothing");}
	    
	  } 
	  
	  return "";
}

public Twilio(PApplet parent, int port){
	this.server = new Server(parent, port);
}

}

