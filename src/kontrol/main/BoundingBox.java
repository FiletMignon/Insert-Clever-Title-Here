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
}
