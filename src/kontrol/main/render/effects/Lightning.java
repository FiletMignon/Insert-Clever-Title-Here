package kontrol.main.render.effects;

import java.util.Random;

import kontrol.main.util.Position;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Lightning extends Effect{
	private Texture texture;
	private Random rand;
	private int arcs;

	public Lightning(String texture, int arcs){
		super();
		try {
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + texture));
		} catch (Exception d) {
			try {
				this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + "lightning.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.arcs = arcs;
		rand = new Random();
	}
	
	public void render(Position pos1, Position pos2){
		texture.bind();
		GL11.glBegin(GL11.GL_LINES);
			GL11.glTexCoord2f(0.0f, 0.0f);
			pointsToRenderRecur(pos1, pos2, arcs);
//			GL11.glTexCoord2f(0.0f, 1.0f);
		GL11.glEnd();
	}
	private void pointsToRenderRecur(Position pos1, Position pos2, float count){
		if(count <= 0){
			return;
		}
		float noiseX = (rand.nextFloat()-0.5f)/6*count;
		float noiseY = (rand.nextFloat()-0.5f)/6*count;
		float noiseZ = (rand.nextFloat()-0.5f)/6*count;
		
		Position mid = Position.midpoint(pos1, pos2);
		mid.setX(noiseX+mid.x());
		mid.setY(noiseY+mid.y());
		mid.setZ(noiseZ+mid.z());

		GL11.glTexCoord2f(rand.nextFloat(), rand.nextFloat());
		GL11.glVertex3f(pos1.x(), pos1.y(), pos1.z());
		GL11.glTexCoord2f(rand.nextFloat(), rand.nextFloat());
		GL11.glVertex3f(mid.x(), mid.y(), mid.z());
		GL11.glTexCoord2f(rand.nextFloat(), rand.nextFloat());
		GL11.glVertex3f(mid.x(), mid.y(), mid.z());
		GL11.glTexCoord2f(rand.nextFloat(), rand.nextFloat());
		GL11.glVertex3f(pos2.x(), pos2.y(), pos2.z());
		
		pointsToRenderRecur(mid, pos1, count-1);
		pointsToRenderRecur(pos2, mid, count-1);
	}
}
