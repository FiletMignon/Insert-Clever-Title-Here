package kontrol.main.physics;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 * @author wiful
 * @version 1
 *
 */
public class Velocity {
	private float xVel;
	private float yVel;
	private float zVel;
	
	public Velocity() {
		xVel = 0;
		yVel = 0;
		zVel = 0;
	}
	public Velocity(float xVel, float yVel, float zVel) {
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
	}
	/**
	 * @param X direction velocity, Y Direction Velocity, Z Direction Velocity
	 * sets the velocities to the new velocities
	 * 
	 */
	public void setVelocity(float xVel, float yVel, float zVel){
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
	}
	public float getXVelocity(){
		return xVel;
	}
	public float getYVelocity(){
		return yVel;
	}
	public float getZVelocity(){
		return zVel;
	}
	public void accelerate(Acceleration accel){
		xVel = xVel + accel.getXAcceleration() * 1/60.f;
		yVel = yVel + accel.getYAcceleration() * 1/60.f;
		zVel = zVel + accel.getZAcceleration() * 1/60.f;
	}
	public static FloatBuffer getVelocityBuffer(Velocity vel){
		FloatBuffer returnValue = BufferUtils.createFloatBuffer(3);
		returnValue.put(vel.getXVelocity());
		returnValue.put(vel.getYVelocity());
		returnValue.put(vel.getZVelocity());
		returnValue.flip();
		return returnValue;
	}
}
