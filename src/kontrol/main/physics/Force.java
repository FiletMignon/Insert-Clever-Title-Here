package kontrol.main.physics;

import kontrol.main.util.Position;


public class Force {
	private float xFor;
	private float yFor;
	private float zFor;
	public Force(){
		xFor = 0;
		yFor = 0;
		zFor = 0;
	}
	public void setForce(float x, float y, float z){
		xFor = x;
		yFor = y;
		zFor = z;
	}
	public void setForceTo(Position self, Position other, float magnitude){
		double x = Math.pow(self.x()-other.x(),2);
		double y = Math.pow(self.y()-other.y(),2);
		double z = Math.pow(self.z()-other.z(),2);
		double tmp = Math.sqrt(x+y+z);
		x = self.x()-other.x();
		y = self.y()-other.y();
		z = self.z()-other.z();
		x /= tmp;
		y /= tmp;
		z /= tmp;
		x *= -magnitude;
		y *= -magnitude;
		z *= -magnitude;
		setForce((float)x, (float)y, (float)z);
	}
	public float getXForce(){
		return xFor;
	}
	public float getYForce(){
		return yFor;
	}
	public float getZForce(){
		return zFor;
	}
}
