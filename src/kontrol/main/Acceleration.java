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
	public float getzAcceleration(){
		return zAcc;
	}

}
