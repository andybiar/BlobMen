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
	private final PApplet applet = this;
	private FWorld world;
	private Twilio twilio;
	private int port = 5027;
	private ArrayList<BlobMan> players;

	@Override
	public void setup(){
	size(screenWidth,screenHeight);
	smooth();
	frameRate(60);
	Fisica.init(this);
	world = new FWorld();
	world.setEdges();
	world.remove(world.top);
	world.remove(world.left);
	world.remove(world.right);
	world.setGravity(0, 10);
	
	twilio = new Twilio(this, port);
	BlobMan.setAppletWorld(applet, world);
	
	//Test BlobMan
	players = new ArrayList<BlobMan>();
	int[] num = {1,2,3,4,5,6,7};
	BlobMan b = new BlobMan(num);
	players.add(b);
	}
	
	@Override
	public void draw(){
		background(0xaaaaaa);
		for (BlobMan b : players) {
			b.update();
		}
		world.step();
		world.draw();
		
		// Get the next available client
		  Client thisClient = twilio.server.available();
		  // If the client is not null, and says something, display what it said
		  if (thisClient !=null) {
		    String whatClientSaid = thisClient.readString();
		    if (whatClientSaid != null) {
		    	String[] split = whatClientSaid.split("From");
		    	println("Test:"+split[1].substring(0, 9));
		    } 
		  } 
	}
	public static void main(String args[]) {
	   PApplet.main(new String[] { "--present", "main.Main" });	  }
}
