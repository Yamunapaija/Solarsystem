package SolarAnimation;

import javafx.scene.image.Image;

public class SimpleSolar {
	private double sunSize = 0.2;					// size sun should be drawn
	private double sunX, sunY;						// position of sun
	
	private double earthSize = 0.05;
	private double earthAngle;	
	private double earthX, earthY;// angle of earth .. for calculating its position
	private double earthOrbitSize = 0.2;			// defines relevant sizes (<1 so that positions in range -0.5 .. +0.5
	private double earthspeed = 0.0004;
	
	private double marsSize = 0.05;
	private double marsAngle;	
	private double marsX, marsY;// angle of earth .. for calculating its position
	private double marsOrbitSize;			// defines relevant sizes (<1 so that positions in range -0.5 .. +0.5
	private double marsspeed = earthspeed/2;
	
	private Image earth;							// images of earth and sun
	private Image sun;
	private Image mars;
	/**
	 * construct simple solar system
	 */
    public SimpleSolar() {
    	earth = new Image(getClass().getResourceAsStream("earth.png"));		// load image of earth
    	sun = new Image(getClass().getResourceAsStream("sun.png"));
    	mars = new Image(getClass().getResourceAsStream("Mars.png"));
    	sunX = 0.5;															// set position of sun
    	sunY = 0.5;
    	
    	earthX = 0.6;
    	earthY = 0.6;
    	earthAngle = 0.0;	// initialise earth
    	
    	marsX = 0.6;
    	marsY = 0.6;
    	marsAngle = 0.0;    
    }
    
	/**
	 * update position of planet(s) at specified angle 
	 * @param angle		angle (time dependent) of planet(s)
	 */
	public void updateSystem (double angle) {
		// set angle of earth appropriately
		earthAngle = angle;
		earthAngle += 2 * Math.PI * angle * earthspeed;
		earthX = earthOrbitSize * Math.cos(earthAngle);
		earthY = earthOrbitSize * Math.sin(earthAngle);
		
		marsAngle = angle;
		marsAngle += 2 * Math.PI * angle * marsspeed;
		marsOrbitSize = sunSize * 2 ;
	    marsX = marsOrbitSize * Math.cos(marsAngle);
	    marsY = marsOrbitSize * Math.sin(marsAngle);
	}

	/** 
	 * set sun at position passed
	 * @param x		x position, in canvas coordinates
	 * @param y
	 
	public void setSystem(MyCanvas mc, double x, double y) {
		// note x,y in range 0.. canvassize

		sunX = x/mc.getXCanvasSize();
		sunY = y/mc.getYCanvasSize();

		sunX = Math.min(Math.max(x, 0), 1);
		sunY = Math.min(Math.max(y, 0), 1);
		
		
		drawSystem(); 
		
		
	} */
	 public void setSystem(MyCanvas mc, double x, double y) {
	        // Ensure x and y are within the canvas size
	        x = Math.min(Math.max(x, 0), mc.getXCanvasSize());
	        y = Math.min(Math.max(y, 0), mc.getYCanvasSize());

	        // Normalize x and y to the range [0, 1]
	        sunX = x / mc.getXCanvasSize();
	        sunY = y / mc.getYCanvasSize();

	       
	    }

	/**
	 * drawImage into canvas, at position x,y relative to sun, but scale the x,y and sz before drawing
	 * @param mc	canvas
	 * @param i		image
	 * @param x		x position		in range -0.5..0.5
	 * @param y		y position
	 * @param sz	size
	 */
	public void drawImage (MyCanvas mc, Image i, double x, double y, double sz) {
		int cs = mc.getXCanvasSize();
		mc.drawImage (i, (x+sunX)*cs, (y+sunY)*cs, sz*cs);		// add sun's position to positions then * canvas size	
	}
	
	/**
	 * draw system  into specified canvas
	 * @param mc		canvas
	 */
	public void drawSystem (MyCanvas mc) {
		mc.clearCanvas();					// first clear canvas 
		drawImage( mc, sun, 0, 0, sunSize );				// draw sun at 0,0
		drawImage( mc, earth, earthX, earthY, earthSize );
		drawImage( mc, mars, marsX, marsY, marsSize );
		// call drawImage to draw earth at position set by earth's angle and orbit size
	}

	/**
	 * return information about planets as a string
	 */
	public String toString() {
		String res = "Earth is at " + Math.round(earthAngle)+"." +"\n " +"Mars is at " + Math.round(marsAngle)+".";
	return res;
	}
}


