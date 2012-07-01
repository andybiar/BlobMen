package main;

import java.applet.Applet;
import processing.core.*;

import fisica.*;

public class BlobMan {
	private static PApplet applet;
	private static FWorld world;
	private float sideMargin = 35;
	private float bottomMargin = 50;
	private float topMargin = 20;
	private FBlob body;
	private FBody leftArm;
	private FBody rightArm;
	private FBody head;
	private FBody hat;

	public BlobMan (String phoneNum) {
		// phoneNum must be a 7-character String of numbers, a phone number minus the area code
		// call setApplet before you instantiate this class
		
		/* MAP THE PHONE NUMBER TO CHARACTER ATTRIBUTES
		 * DIGIT_INDEX		ATTRIBUTE
		 * 		0				Special Move
		 * 		1				Sound
		 * 		2				Arm Shape
		 * 		3				Head Type
		 * 		4-6				Colors
		 */
		
		createBody();
	}
	
	public void createBody() {
		body = new FBlob();
		body.setAsCircle(50);
		body.setPosition(applet.width/2, applet.height/2);
		
		world.add(body);
		
	}
	
	public static void setAppletWorld(PApplet applet, FWorld world) {
		BlobMan.applet = applet;
		BlobMan.world = world;
	}
	
	
}
