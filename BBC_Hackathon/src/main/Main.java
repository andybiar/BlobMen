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
import ddf.minim.*;

public class Main extends PApplet {
	
	public static final long serialVersionUID = 1L;
	public static final int NUM_PLAYERS = 0; //zero indexed
	private final PApplet applet = this;
	private FWorld world;
	private Twilio twilio;
	private int port = 5023;
	private ArrayList<BlobMan> players;
	private int playerNumber = 0;
	private int[] playerPhoneNumbers;
	Minim minim;
	AudioSnippet jump;
	AudioSnippet turn;
	
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
	
	minim = new Minim(this);
	turn = minim.loadSnippet("turn.wav");
	jump = minim.loadSnippet("jump.wav");
	
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
		background(0x7CB490);
		for (BlobMan b : players) {
			b.update();
		}
		world.step();
		world.draw();
		doControls();
		
		//boolean win = checkWin();
		
	}
	
//	public boolean checkWin() {
//		boolean playersWin = true;
//		for (BlobMan b : players) {
//			if 
//		}
//	}
	
	public void doControls(){
		// Get the next available client
				String result = twilio.listenNums();
		    	String[] fromSplit = result.split("From");
		    	String[] bodySplit = result.split("Body=");
		    	String phoneString = null;
		    	if(fromSplit.length  > 4){
		    		if(fromSplit[4].length()>=15){
		    		 phoneString= fromSplit[4].substring(8,15);}
		    		//println("Phone #: "+phoneString);
		    	if(playerNumber<=NUM_PLAYERS && phoneString != null){
		    	int[] nums = new int[7];
		    	for(int i = 0; i<phoneString.length(); i++){
		    		nums[i]=(int)phoneString.charAt(i)-48;
		    	}
				if(nums != null){
					players.add(new BlobMan(nums));
					println("Player "+playerNumber+" joined: "+phoneString);
					playerPhoneNumbers[playerNumber] = Integer.parseInt(phoneString);
					playerNumber++;
				}
		    	}
		    	else{
		    		if(bodySplit!=null && phoneString!=null){
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
			jump.rewind();
			jump.play();
		}
		else if(action=='r'){
			players.get(playerNum).faceR();
			turn.rewind();
			turn.play();
		}
		else if(action=='l'){
			players.get(playerNum).faceL();
			turn.rewind();
			turn.play();
		}
		
	}
	
	public void stop()
	{
	  // always close Minim audio classes when you are done with them
	  turn.close();
	  jump.close();
	  // always stop Minim before exiting
	  minim.stop();
	  
	  super.stop();
	}

	
	public static void main(String args[]) {
	   PApplet.main(new String[] { "--present", "main.Main" });	  }
}
