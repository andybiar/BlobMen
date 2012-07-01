package main;

import processing.core.*;
import fisica.*;

public class Main extends PApplet {
	public static final long serialVersionUID = 1L;
	public final PApplet applet = this;
	public final FWorld world = new FWorld();
	
	@Override
	public void setup(){
	size(200,200);
	frameRate(60);
	Fisica.init(this);
	world.setEdges();
	
	BlobMan.setAppletWorld(applet, world);
	
	//Test BlobMan
	BlobMan b = new BlobMan("1234567");
	}
	
	@Override
	public void draw(){
		world.step();
	}
}
