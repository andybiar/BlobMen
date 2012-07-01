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
		// call setAppletWorld before you instantiate this class
		
		/* MAP THE PHONE NUMBER TO CHARACTER ATTRIBUTES
		 * DIGIT_INDEX		ATTRIBUTE
		 * 		0				Special Move
		 * 		1				Sound
		 * 		2				Arm Shape
		 * 		3				Head Type
		 * 		4-6				Colors
		 */
		
		createBody();
		createHead("8"); //phoneNum.charAt(3)
	}
	
	private void createBody() {
		body = new FBlob();
		body.setAsCircle(applet.width/2, applet.height/2, 70);
		
		world.add(body);
	}
	
	private void createHeadHelp(int code) {
		switch (code) {
		case 3: // Square head, circle hat
			head = new FBox(40, 40);

			break;
		case 2 : // Round head, triangle hat
			
			break;
		case 1 : // Square head, triangle hat
			
			break;
		case 0 : // Round head, round hat
			
			break;
		}	
		

		head.setPosition(applet.width/2, applet.height/2 - 35);
		world.add(head);

	}
	
	private void createHead(String headCode) {
		if (headCode.compareTo("8") >= 0) createHeadHelp(3);
		
		// Determine what numerical range the digit falls in and call createHeadHelp
//		if (hash >= 8) createHeadHelp(3);
//		else if (hash >= 5) createHeadHelp(2);
//		else if (hash >= 2) createHeadHelp(1);
//		else createHeadHelp(0);
	}
	
	public static void setAppletWorld(PApplet applet, FWorld world) {
		BlobMan.applet = applet;
		BlobMan.world = world;
	}
	
	public void update() {
	}
	
	
}
