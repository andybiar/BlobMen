package main;

import processing.core.*;
import processing.net.*;


public class Twilio extends PApplet{
	
	
public Server server;
public int port;

public int[] listenNums(){
	 Client thisClient = server.available();
	  // If the client is not null, and says something, display what it said
	  if (thisClient !=null) {
	    String whatClientSaid = thisClient.readString();
	    if (whatClientSaid != null) {
	    	String[] split = whatClientSaid.split("From");
	    	if(split.length  > 3){
	    		String phoneString = split[4].substring(8,15);
	    		println("Phone #: "+phoneString);
	    	int[] nums = new int[7];
	    	for(int i = 0; i<phoneString.length(); i++){
	    		nums[i]=(int)phoneString.charAt(i)-48;
	    	}
	    	return nums;
	    	
	    	//thisClient.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Gather timeout=\"10\" finishOnKey=\"*\"><Say>Please enter your pin number and then press star.</Say></Gather></Response>");
	    	}
	    	else{println("Nothing");}
	    	
	    }
	  } 
	  
	  return null;
}

public Twilio(PApplet parent, int port){
	this.server = new Server(parent, port);
}

}

