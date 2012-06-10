package kontrol.main;

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
		return new Position(pos.x()+(width/2+height/2+depth/2), pos.y()+(width/2+height/2+depth/2), pos.z()+(width/2+height/2+depth/2));
	}
	public Position getMinVertex(Position pos){
		return new Position(pos.x()-(width/2+height/2+depth/2), pos.y()-(width/2+height/2+depth/2), pos.z()-(width/2+height/2+depth/2));
	}
}
