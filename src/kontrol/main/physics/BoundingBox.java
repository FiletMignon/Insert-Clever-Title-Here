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
		return new Position(pos.x()+(width+height+depth), pos.y()+(width+height+depth), pos.z()+(width+height+depth));
	}
	public Position getMinVertex(Position pos){
		return new Position(pos.x()-(width+height+depth), pos.y()-(width+height+depth), pos.z()-(width+height+depth));
	}
}
