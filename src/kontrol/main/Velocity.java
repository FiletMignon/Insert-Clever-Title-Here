/**
 * 
 */
package kontrol.main;

/**
 * @author wiful
 * @version 1
 *
 */
public class Velocity {
	protected float xVel;
	protected float yVel;
	protected float zVel;
	
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
		xVel = xVel + accel.getXAcceleration() * 0.01f;
		yVel = yVel + accel.getYAcceleration() * 0.01f;
		zVel = zVel + accel.getZAcceleration() * 0.01f;
	}
}
