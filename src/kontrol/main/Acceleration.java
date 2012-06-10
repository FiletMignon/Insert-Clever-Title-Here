package kontrol.main;

public class Acceleration {
	private float xAcc;
	private float yAcc;
	private float zAcc;
	public Acceleration() {
		xAcc=0;
		yAcc=0;
		zAcc=0;
	}
	public Acceleration(float xAcc, float yAcc, float zAcc) {
		this.xAcc = xAcc;
		this.yAcc = yAcc;
		this.zAcc = zAcc;
	}
	public void setAcceleration(float xAcc, float yAcc, float zAcc){
		this.xAcc = xAcc;
		this.yAcc = yAcc;
		this.zAcc = zAcc;
	}
	public float getXAcceleration(){
		return xAcc;
	}
	public float getYAcceleration(){
		return yAcc;
	}
	public float getZAcceleration(){
		return zAcc;
	}
	public void setAccelerationFromForce(Force force, float mass){
		xAcc = force.getXForce() / mass;
		yAcc = force.getYForce() / mass;
		zAcc = force.getZForce() / mass;
	}
}
