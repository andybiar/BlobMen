package main;

import processing.core.*;
import fisica.*;

public class LevelMgr {
	private static PApplet applet;
	private static FWorld world;
	private int lvl;
	private float w;
	private float h;
	private static FCircle goal;

	public LevelMgr() {
		// Initialize level 1
		lvl = 0;
		w = applet.width;
		h = applet.height;
		
		FBox floor = new FBox(w, h/20);
		floor.setPosition(w/2, h*.975f);
		floor.setGrabbable(false);
		floor.setStatic(true);
		world.add(floor);
		
		FBox rightStep = new FBox(w/10, h/7);
		rightStep.setPosition(7.5f*w/10, 7*h/10);
		world.add(rightStep);
		
		FBox smRStep = new FBox(w/10, h/12);
		smRStep.setPosition(6.5f*w/10, 7*h/10);
		world.add(smRStep);
		
		goal = new FCircle(100);
		goal.setFill(255, 240, 0);
		goal.setPosition(w/2, h/2);
		goal.setGrabbable(false);
		goal.setStatic(true);
		world.add(goal);
		
		BlobMan.setSpawn(w/10, h*2/3);
	}
	
	public static void setAppletWorld(PApplet applet, FWorld world){
		LevelMgr.applet = applet;
		LevelMgr.world = world;
	}
	
	private void lvl2() {
		FBox floor = new FBox(w, h/20);
		floor.setPosition(w/2, h*.975f);
		floor.setGrabbable(false);
		floor.setStatic(true);
		world.add(floor);
		
		FBox rightWall = new FBox(w/5, h/2);
		rightWall.setPosition(w*4.5f/5, h*19/20 - h/4);
		rightWall.setGrabbable(false);
		rightWall.setStatic(true);
		world.add(rightWall);
		
		FBox leftPlat = new FBox(w/2, h/7);
		leftPlat.setPosition(w/4, h*2/3);
		leftPlat.setGrabbable(false);
		leftPlat.setStatic(true);
		world.add(leftPlat);
		

		
		FBox goal = new FBox(w/10, h/10);
		goal.setPosition(w/4, h/5);
		goal.setFill(255, 255, 0);
		goal.setStatic(true);
		world.add(goal);
	}
	
	public static FBody getGoal() {
		return goal;
	}
	
}
