package kontrol.main.physics;

import kontrol.main.util.Position;




public class BoundingBox {
	private float width, height, depth;
	public BoundingBox(float width, float height, float depth){
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}
	public float getDepth(){
		return depth;
	}
	public Position getMaxVertex(Position pos){
		return new Position(pos.x()+(width/4+height/2+depth/2), pos.y()+(width/2+height/4+depth/2), pos.z()+(width/2+height/2+depth/4));
	}
	public Position getMinVertex(Position pos){
		return new Position(pos.x()-(width/4+height/2+depth/2), pos.y()-(width/2+height/4+depth/2), pos.z()-(width/2+height/2+depth/4));
	}
}
