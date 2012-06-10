package kontrol.main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Screen {
	private boolean running;

	private int width;
	private int height;

	private Environment enviro;
	
	/**
	 * The main constructor for the screen class.
	 * 
	 * The screen class handles all display but should not be used
	 * to initialize anything Game Specific.  That should be handled by the
	 * Game class.
	 * 
	 * @param width Width of the Display
	 * @param height Height of the Display
	 * @param title the Title of the Display
	 */
	public Screen(int width, int height, String title){
		this.width = width;
		this.height = height;
		enviro = new Environment(100, 100, 100);
        init(title);
        Cube cube1 = new Cube("", new Position( 20, 0, -8));
        Cube cube2 = new Cube("", new Position(-20, 0, -8));
        cube1.setForce(cube2.getPosition());
        cube2.setForce(cube1.getPosition());
        enviro.addPlayer(0, new Player("Player", new Position(0,0,0), "Test Dummy"));
        enviro.addEntity(cube1);
        enviro.addEntity(cube2);
		running = true;
        while(running){
            if(Display.isCloseRequested())
            	running=false;
            inputHandling();
            enviro.actAllTheActions();
            render();
            Display.sync(60);
            Display.update();
        }

        Display.destroy();
	}
	
	/**
	 * Render stuff in between the two
	 * RENDER STUFF brackets below.
	 */
	private void render() {
		initRenderLoop();
		enviro.getPlayer(0).cameraView();
        //RENDER STUFF/////////////////////
		enviro.renderAllTheEntities();
        //RENDER STUFF/////////////////////
	}
	/**
	 * Used to initialize certain OpenGL variables
	 * before rendering stuff to the Display
	 */
	private void initRenderLoop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer
		GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	}
	/**
	 * Handle generic input that should not be object
	 * or entity specific.
	 * 
	 * Current Controls Set
	 * ESCAPEKEY - Quit Window
	 * Toggle F1 - Enable pseudo wire-frame mode
	 */
    private void inputHandling() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            running = false;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1)){
    		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        }
        else if(!Keyboard.isKeyDown(Keyboard.KEY_F1)){
    		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        }
	}

    /**
     * Initialize the screen
     * @param title Title of the Display
     */
    private void init(String title){
        try{
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setVSyncEnabled(true);
            Display.setTitle(title);
            Display.create();
        }catch(Exception e){
            System.out.println("Error setting up display");
            System.exit(0);
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);	 // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Background Color
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) Display.getWidth() / (float) Display.getHeight(),
          0.1f,
          100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }
}
