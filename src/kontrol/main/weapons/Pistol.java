package kontrol.main.weapons;

import org.lwjgl.opengl.Display;

import kontrol.main.Environment;
import kontrol.main.entities.Bullet;
import kontrol.main.entities.Cube;
import kontrol.main.entities.Entity;
import kontrol.main.entities.Player;
import kontrol.main.entities.SpawnedCube;
import kontrol.main.entities.TrackingBullet;
import kontrol.main.physics.BoundingBox;
import kontrol.main.util.Position;

public class Pistol extends Weapon{
	public Pistol() {
		super(Weapon.WEPTYPE_PISTOLS);
	}
	private int framesSinceLastShot = 0;
	private boolean canShoot = true;
	public void fire(Player player, Environment enviro){
		if(canShoot){
			canShoot = false;
			float xPos = -player.getPosition().x();
			float yPos = -player.getPosition().y();
			float zPos = -player.getPosition().z();
			Position posToShootFrom = new Position(xPos, yPos, zPos);
			Position posToShootTo = Position.screenToWorld(Display.getWidth()/2, Display.getHeight()/2);
			//TODO Make sure the gun shoots forward
			shoot(new TrackingBullet(posToShootFrom, posToShootTo, enviro), enviro);
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
	private void shoot(Entity ent,  Environment enviro) {
		enviro.addEntity(ent);	
	}
	private int framesSinceLastAltShot = 0;
	public void altFire(Player player, Environment enviro){
		if(canShoot){
			canShoot = false;
			Position posToCreateCube = Position.screenToWorld(Display.getWidth()/2, Display.getHeight()/2);
			BoundingBox bounds = new BoundingBox(1,1,1);
			shoot(new SpawnedCube("spawnedcube.png", bounds, posToCreateCube, enviro), enviro);
			System.out.println(this.getClass().getSimpleName() + " Made A Cube");
		}
		if(!canShoot){
			framesSinceLastAltShot++;
		}
		if(framesSinceLastAltShot > 10){
			canShoot = true;
			framesSinceLastAltShot = 0;
		}
	}
}
