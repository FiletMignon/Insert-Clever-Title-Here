package kontrol.main;

import java.util.ArrayList;

import kontrol.main.entities.Entity;
import kontrol.main.entities.Player;
import kontrol.main.util.Position;


public class Environment {
	private float width, height, depth;
	private ArrayList<Entity> entities;
	private Player players[];
	public Environment(float width, float height, float depth){
		this.width = width;
		this.height = height;
		this.depth = depth;
		entities = new ArrayList<Entity>(0);
		players = new Player[1];
	}
	public boolean isOutOfBounds(Entity ent){
		float x = ent.getPosition().x();
		float y = ent.getPosition().y();
		float z = ent.getPosition().z();
		if(ent.isCollidedWith(new Position(x+width/2, y, z))){
			return true;
		}
		if(ent.isCollidedWith(new Position(x, y+height/2, z))){
			return true;
		}
		if(ent.isCollidedWith(new Position(x, y, z+depth/2))){
			return true;
		}
		return false;
	}
	public void addEntity(Entity ent){
		entities.add(ent);
	}
	public Player getPlayer(int index) {
		return players[index];
	}
	public void addPlayer(int index, Player player){
		players[index] = player;
	}
	
	public int getEntityAmount(){
		return entities.size();
	}
	
	public Entity getEntity(int index){
		return entities.get(index);
	}
	
	public void removeEntity(int index){
		entities.remove(index);
	}

	public void actAllTheActions(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).act(this);
		}
		for(int i = 0; i < players.length; i++){
			players[i].act();
		}
		handleAllTheWeapons();
	}
	public void renderAllTheEntities(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render();
		}
		for(int i = 0; i < players.length; i++){
			players[i].render();
		}
	}
	public void handleAllTheWeapons(){
		for(int i = 0; i < players.length; i++){
			players[i].handleWeapons(this);
		}
	}
}
