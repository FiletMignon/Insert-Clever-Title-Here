package kontrol.main.weapons;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import kontrol.main.Environment;
import kontrol.main.entities.Bullet;
import kontrol.main.entities.Entity;
import kontrol.main.entities.Player;
import kontrol.main.util.Position;

public class GravityGun extends Weapon {

	public GravityGun() {
		super(WEPTYPE_SUPPORT_ENVIRONMENT);
	}
	private int framesSinceLastShot = 0;
	private boolean canShoot = true;
	public void fire(Player player, Environment enviro){
		if(canShoot){
			canShoot = false;
			float xPos = player.getPosition().x();
			float yPos = player.getPosition().x();
			float zPos = player.getPosition().x();
			Position posToShootFrom = player.getNewPosition();
			Position posToShootTo = Position.screenToWorld(Display.getWidth()/2, Display.getHeight()/2);
			posToShootFrom.setXRot(player.getPosition().xRot());
			posToShootFrom.setYRot(player.getPosition().yRot());
			posToShootFrom.setZRot(player.getPosition().zRot());
			Entity ent = getEntityAt(posToShootTo, enviro);
			//TODO Make sure the gun shoots forward
			shoot(ent);
			System.out.println(this.getClass().getSimpleName() + " Fired!");
		}
		if(!canShoot){
			framesSinceLastShot++;
		}
		if(framesSinceLastShot > 4){
			canShoot = true;
			framesSinceLastShot = 0;
		}
	}
	private Entity getEntityAt(Position pos, Environment enviro) {
		ArrayList<Entity> entities = enviro.getEntities();
		Entity closest = entities.get(0);
		for(int i = 1; i < enviro.getEntityAmount(); i++){
			Entity x = entities.get(i);
			if(x.getPosition().x()-pos.x() < closest.getPosition().x()- pos.x()){
				if(x.getPosition().y() - pos.y() < closest.getPosition().y() -pos.y()){
					if(x.getPosition().z() - pos.z() < closest.getPosition().y() -pos.y()){
						closest = x;
					}
				}
			}
		}
		return closest;
	}
	private void shoot(Entity ent) {
		System.out.println(ent);
	}

}
