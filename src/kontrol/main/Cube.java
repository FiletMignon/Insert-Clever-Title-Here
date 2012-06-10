package kontrol.main;

public class Cube extends Entity{
	public Cube(String texture, Position pos) {
		super(texture, new BoundingBox(1,1,1), pos);
		vel = new Velocity();
	}
	public void setVelocity(float x, float y, float z){
		vel = new Velocity(x, y, z);
	}
	public void act(Environment enviro){
		int entityAmount = enviro.getEntityAmount();
		for(int i = 0; i < entityAmount; i++){
			Entity indexedEnt = enviro.getEntity(i);
			if(isCollidedWith(indexedEnt)){
				vel.setVelocity(0, 0, 0);
			}
			else{
				pos.x(vel.getXVelocity());
				pos.y(vel.getYVelocity());
				pos.z(vel.getZVelocity());
			}
		}
	}
	public void setForce(Position position) {
		force.setForceTo(this.getPosition(), position, 9.8f);
		acc.setAccelerationFromForce(force, mass);
		vel.accelerate(acc);
	}
}
