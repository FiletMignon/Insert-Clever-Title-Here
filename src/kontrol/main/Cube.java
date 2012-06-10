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
			if(!isCollidedWith(indexedEnt) && (vel.xVel!=0 && vel.yVel!=0 && vel.zVel!=0)){
				applyVelocity();
			}
			else{
				System.out.println("Collide");
			}
		}
	}
	
}
