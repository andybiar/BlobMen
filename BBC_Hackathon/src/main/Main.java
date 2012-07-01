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
	private int port = 5022;
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
	world.setGravity(0, 100);
	
	LevelMgr.setAppletWorld(applet, world);
	LevelMgr lvlMgr = new LevelMgr();
	
	twilio = new Twilio(this, port);
	BlobMan.setAppletWorld(applet, world);
	
	println("Test");
	//Test BlobMan
	players = new ArrayList<BlobMan>();
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
		
		// Get the next available client
		int[] nums = twilio.listenNums();
		if(nums != null){
			players.add(new BlobMan(nums));
		}
	}
	public static void main(String args[]) {
	   PApplet.main(new String[] { "--present", "main.Main" });	  }
}
