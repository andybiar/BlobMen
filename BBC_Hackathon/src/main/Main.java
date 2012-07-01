package main;

import processing.core.*;
import fisica.*;

public class Main extends PApplet {
	
	public static final long serialVersionUID = 1L;
	private final PApplet applet = this;
	private FWorld world;
	private Twilio twilio;
	private int port = 5027;

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
	println("Test");
	//Test BlobMan
	BlobMan b = new BlobMan("1234567");
	}
	
	@Override
	public void draw(){
		background(0xaaaaaa);
		world.step();
		world.draw();
	}
	  public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "main.Main" });
		  }
}
