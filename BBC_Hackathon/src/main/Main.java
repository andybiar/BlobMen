package main;

import processing.core.*;
import processing.net.*;
import fisica.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class Main extends PApplet {
	
	public static final long serialVersionUID = 1L;
	public static final int NUM_PLAYERS = 1; //zero indexed
	private final PApplet applet = this;
	private FWorld world;
	private Twilio twilio;
	private int port = 5022;
	private ArrayList<BlobMan> players;
	private int playerNumber = 0;
	private int[] playerPhoneNumbers;
	@Override
	public void setup(){
	size(screenWidth,screenHeight);
	smooth();
	frameRate(60);
	Fisica.init(this);
	world = new FWorld();
	world.setEdges();
	world.remove(world.top);
	world.setGravity(0, 100);
	
	LevelMgr.setAppletWorld(applet, world);
	LevelMgr lvlMgr = new LevelMgr();
	
	twilio = new Twilio(this, port);
	BlobMan.setAppletWorld(applet, world);
	
	println("Test");
	//Test BlobMan
	players = new ArrayList<BlobMan>();
	playerPhoneNumbers = new int[NUM_PLAYERS+1];
	int[] num = {1,2,3,4,5,6,7};
	//BlobMan b = new BlobMan(num);
	//players.add(b);
	}
	
	@Override
	public void draw(){
		background(0xaaaaaa);
		for (BlobMan b : players) {
			b.update();
		}
		world.step();
		world.draw();
		doControls();
		
	}
	
	public void doControls(){
		// Get the next available client
				String result = twilio.listenNums();
		    	String[] fromSplit = result.split("From");
		    	String[] bodySplit = result.split("Body=");
		    	if(fromSplit.length  > 3){
		    		String phoneString = fromSplit[4].substring(8,15);
		    		//println("Phone #: "+phoneString);
		    	if(playerNumber<=NUM_PLAYERS){
		    	int[] nums = new int[7];
		    	for(int i = 0; i<phoneString.length(); i++){
		    		nums[i]=(int)phoneString.charAt(i)-48;
		    	}
				if(nums != null){
					players.add(new BlobMan(nums));
					playerPhoneNumbers[playerNumber] = Integer.parseInt(phoneString);
					playerNumber++;
				}
		    	}
		    	else{
		    		if(bodySplit!=null){
		    			String action = bodySplit[1].substring(0,1).toLowerCase();
		    			if(Integer.parseInt(phoneString) == playerPhoneNumbers[0]){
		    	    		doAction(0, action.charAt(0));}
		    			else if(Integer.parseInt(phoneString) == playerPhoneNumbers[1]){
		    	    		doAction(1, action.charAt(0));}
		    			else{
		    				println("Not A Valid Number");
		    			}
		    		}
		    	}
			    }
		
	}
	
	public void doAction(int playerNum, char action){
		println("Player "+playerNum+" does "+action+"!");
		if(action=='j'){
			players.get(playerNum).jump();
		}
		else if(action=='r'){
			players.get(playerNum).faceR();
		}
		else if(action=='l'){
			players.get(playerNum).faceL();
		}
		
	}
	public static void main(String args[]) {
	   PApplet.main(new String[] { "--present", "main.Main" });	  }
}
