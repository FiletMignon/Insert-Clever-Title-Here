package kontrol.main;

import java.nio.FloatBuffer;

import kontrol.main.entities.Entity;
import kontrol.main.entities.Player;
import kontrol.main.render.effects.Lightning;
import kontrol.main.util.Position;


import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Screen {
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
		enviro = new Environment(25, 25, 25);
        init(title);
	}

	private boolean running;
	public void run(){
		running = true;
        while(running){
            if(Display.isCloseRequested())
            	running=false;
    		initRenderLoop();
            render();
            inputHandling();
            enviro.actAllTheActions();
            displayHUD();
            Display.sync(60);
            Display.update();
        }

        Display.destroy();
	}
	
	public boolean addEntity(Entity ent){
		if(ent instanceof Player){
			return false;
		}
		enviro.addEntity(ent);
		return true;
	}

	public void setMainPlayer(Player player){
		enviro.addPlayer(0, player);
	}
//	Lightning lightning;
	public void addPlayer(Player player){
		enviro.addPlayer(enviro.getAmountOfPlayers()-1, player);
//		lightning = new Lightning("", 4, 4);
	}
	public Environment getEnvironment(){
		return enviro;
	}
	
	/**
	 * Render stuff here
	 */
	private void render() {
		enviro.getPlayer(0).cameraView();
        //RENDER STUFF/////////////////////
		enviro.renderAllTheEntities();
//		lightning.render(new Position(0,0,0), new Position(8,0,0));
//		lightning.render(new Position(0,0,0), new Position(-8,0,0));
//		lightning.render(new Position(0,0,0), new Position(0,8,0));
//		lightning.render(new Position(0,0,0), new Position(0,-8,0));
//		lightning.render(new Position(0,0,0), new Position(0,0,8));
//		lightning.render(new Position(0,0,0), new Position(0,0,-8));
        //RENDER STUFF/////////////////////
	}
	public void displayHUD(){
		initHUD();
		enviro.getPlayer(0).hud();
	}
	public void initHUD(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();

	    GLU.gluOrtho2D(0.0f, Display.getWidth(), Display.getHeight(), 0.0f);

	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glLoadIdentity();
	    GL11.glTranslatef(0.375f, 0.375f, 0.0f);
	    GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	/**
	 * Used to initialize certain OpenGL variables
	 * before rendering stuff to the Display
	 */
	private void initRenderLoop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer
		GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        
        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) Display.getWidth() / (float) Display.getHeight(),
          0.1f,
          5000.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}
	/**
	 * Handle generic input that should not be object
	 * or entity specific.
	 * 
	 * Current Controls Set
	 * ESCAPEKEY - Quit Window
	 * Toggle F1 - Enable pseudo wire-frame mode
	 */
	private boolean spaceWasPressed = false;
    private void inputHandling() {
    	if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !spaceWasPressed){
    		Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
    		Mouse.setGrabbed(!Mouse.isGrabbed());
    		spaceWasPressed = true;
    	}
    	else if(!Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
    		spaceWasPressed = false;
    	}
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
          5000.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
        
//        GL11.glEnable(GL11.GL_FOG);

        FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
        fogColor.put(0.75f).put(0.75f).put(0.75f).put(1.0f).flip(); //Make the color for the fog
        GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR); //Set the mode of the fog
        GL11.glFog(GL11.GL_FOG_COLOR, fogColor); //Fog color being added to the fog
//        GL11.glFogf(GL11.GL_FOG_DENSITY, 0.03f); //Density fo the fog (Note: Only used during Exponential fog)
        GL11.glHint(GL11.GL_FOG_HINT, GL11.GL_DONT_CARE); //The glHint for the fog 
        GL11.glFogf(GL11.GL_FOG_START, 25.0f);
        GL11.glFogf(GL11.GL_FOG_END, 75.0f);
    }
}
