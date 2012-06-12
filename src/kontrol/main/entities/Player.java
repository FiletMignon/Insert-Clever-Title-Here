package kontrol.main.entities;


import kontrol.main.Environment;
import kontrol.main.physics.BoundingBox;
import kontrol.main.render.SegmentDisplay;
import kontrol.main.util.Position;
import kontrol.main.weapons.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Player extends Entity {
	private String playerName;
	
	private WeaponSet[] weapons;
	private int currentWeaponType;
	private int currentWeaponIndex;
	
	private int health;
	
	public Player(String texture, Position pos, String playerName) {
		super(texture, new BoundingBox(1, 1, 1), pos);
		this.playerName = playerName;
		weapons = new WeaponSet[9];
		for(int i = 0; i < 9; i++){
			weapons[i] = new WeaponSet();
		}
		currentWeaponType = 0;
		currentWeaponIndex = 0;
		weapons[Weapon.WEPTYPE_PISTOLS].addWeapon(new Pistol());
		health = 123;
	}

	/**
	 * Gets the name of the player
	 * @return The name of the player
	 */
	public String getPlayerName(){
		return playerName;
	}
	public void render(){
		//Render Nothing
	}
	private SegmentDisplay testDisplay = new SegmentDisplay("", 50, 80, new Position(200,200,0));
	private SegmentDisplay healthDisplay1 = new SegmentDisplay("", 25, 40, new Position(100,550,0));
	private SegmentDisplay healthDisplay10 = new SegmentDisplay("", 25, 40, new Position(140,550,0));
	private SegmentDisplay healthDisplay100 = new SegmentDisplay("", 25, 40, new Position(180,550,0));
	private int displayShow = 0;
	private int frames = 0;
	public void hud(){
		if(health < 1){
			
		}
		else{
			frames++;
			if(frames > 60){
				health--;
				frames = 0;
			}
			int healthDigit;
			health
			healthDisplay1.render(SegmentDisplay.SEGMENT_DISPLAY[healthDigit]);
			healthDisplay10.render(SegmentDisplay.SEGMENT_DISPLAY[healthDigit]);
			healthDisplay100.render(SegmentDisplay.SEGMENT_DISPLAY[healthDigit]);
			GL11.glTranslatef(Display.getWidth()/2, Display.getHeight()/2, 0);
			GL11.glBegin(GL11.GL_LINE_LOOP);
				GL11.glVertex3f( 0.0f, 8.0f, 0.0f);         // Top 
				GL11.glVertex3f(-8.0f, 0.0f, 0.0f);         // Left
				GL11.glVertex3f( 0.0f, -8.0f, 0.0f);         // Bottom
				GL11.glVertex3f( 8.0f, 0.0f, 0.0f);         // Right
			GL11.glEnd();
			GL11.glBegin(GL11.GL_LINE_LOOP);
			    GL11.glVertex3f( 6.0f, 6.0f, 0.0f);         // Top Right
			    GL11.glVertex3f(-6.0f, 6.0f, 0.0f);         // Top Left
			    GL11.glVertex3f(-6.0f,-6.0f, 0.0f);         // Bottom Left
			    GL11.glVertex3f( 6.0f,-6.0f, 0.0f);         // Bottom Right
			GL11.glEnd();
			GL11.glTranslatef(-Display.getWidth()/2, -Display.getHeight()/2, 0);
		}
	}
	public void act(Environment enviro){
		applyPhysics(enviro);
		input();
	}
	private float speed = 0.05f;//The speed of the player
	private float mouseSensivity = 0.05f;//The speed of the player
	/**
	 * Handle the input of the player and
	 * move accordingly
	 */
	private boolean useMouse = true;
	public void input(){
    	if(useMouse){
        	pos.addXRot(-Mouse.getDY()*mouseSensivity);
        	pos.addYRot(Mouse.getDX()*mouseSensivity);
    		Mouse.setGrabbed(true);
    	}
    	else{
    		Mouse.setGrabbed(false);
    	}
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	speed = 0.25f;
        }
        if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
        	speed = 0.05f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
        	pos.forward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
        	pos.backward(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){ 
        	pos.strafeL(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){ 
        	pos.strafeR(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Z)){  
        	pos.down(speed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){  
        	pos.up(speed);
        }
}

	/**
	 * Fire the current weapon.
	 */
	private void fire(Environment enviro) {
		weapons[currentWeaponType].getWeapon(currentWeaponIndex).fire(this, enviro);
	}
	/**
	 * Fire the alternate fire of the current
	 * weapon.
	 */
	private void altFire(Environment enviro) {
		weapons[currentWeaponType].getWeapon(currentWeaponIndex).altFire(this, enviro);
	}

	/**
	 * Used before rendering to revolve the world around you
	 * in order to get a perspective of things  :P
	 */
	public void cameraView(){
        //rotate xRot around the X axis
        GL11.glRotatef(getPosition().xRot(), 1.0f, 0.0f, 0.0f);
        //rotate yRot around the Y axis
        GL11.glRotatef(getPosition().yRot(), 0.0f, 1.0f, 0.0f);
        //rotate zRot around the Z axis
        GL11.glRotatef(getPosition().zRot(), 0.0f, 0.0f, 1.0f);
        //translate to the position vector's location
        GL11.glTranslatef(getPosition().x(), getPosition().y(), getPosition().z());
	}

	public void handleWeapons(Environment enviro) {
        if(Mouse.isButtonDown(0)){
    		fire(enviro);
        }
        else if(Mouse.isButtonDown(1)){
    		altFire(enviro);
        }
	}
}
