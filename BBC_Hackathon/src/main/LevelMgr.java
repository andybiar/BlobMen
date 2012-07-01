package main;

import processing.core.*;
import fisica.*;

public class LevelMgr {
	private static PApplet applet;
	private static FWorld world;
	private int lvl;

	public LevelMgr() {
		// Initialize level 1
		lvl = 0;
		
		FBox floor = new FBox(applet.width, 30);
		floor.setPosition(applet.width/2, applet.height - 30);
		floor.setGrabbable(false);
		world.add(floor);
		
		FBox box = new FBox(40, 40);
		box.setPosition(applet.width/2 + 100, applet.height/2 + 100);
		world.add(box);
	}
	
	public static void setAppletWorld(PApplet applet, FWorld world){
		LevelMgr.applet = applet;
		LevelMgr.world = world;
	}
	
}
