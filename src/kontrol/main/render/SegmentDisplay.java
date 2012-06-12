package kontrol.main.render;

import kontrol.main.util.Position;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SegmentDisplay {
	public static final boolean[][] SEGMENT_DISPLAY = {
			{true, true, true, true, true, true, false}, 		//0
			{false, true, true, false, false, false, false}, 	//1
			{true, true, false, true, true, false, true}, 		//2
			{true, true, true, true, false, false, true}, 		//3
			{false, true, true, false, false, true, true}, 		//4
			{true, false, true, true, false, true, true}, 		//5
			{true, false, true, true, true, true, true}, 		//6
			{true, true, true, false, false, false, false}, 	//7
			{true, true, true, true, true, true, true}, 		//8
			{true, true, true, true, false, true, true}}; 		//9
	
	private Texture texture;
	private float width;
	private float height;
	private Position pos;
	public SegmentDisplay(String texture, float width, float height, Position pos){
		this.width = width;
		this.height = height;
		this.pos = pos;
		try {
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + texture));
		} catch (Exception d) {
			try {
				this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/" + "blank.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void render(boolean[] displaySegments){
		GL11.glTranslatef(pos.x(), pos.y(), 0);
		texture.bind();
		if(displaySegments[0]){
			GL11.glTranslatef(0, -height/2, 0);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glTranslatef(0, height/2, 0);
		}
		if(displaySegments[1]){
			GL11.glTranslatef(width/2, -height/4, 0);
			GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-width/2, height/4, 0);
		}
		if(displaySegments[2]){
			GL11.glTranslatef(width/2, height/4, 0);
			GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-width/2, -height/4, 0);
		}
		if(displaySegments[3]){
			GL11.glTranslatef(0, height/2, 0);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glTranslatef(0, -height/2, 0);
		}
		if(displaySegments[4]){
			GL11.glTranslatef(-width/2, height/4, 0);
			GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(width/2, -height/4, 0);
		}
		if(displaySegments[5]){
			GL11.glTranslatef(-width/2, -height/4, 0);
			GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
			renderSegment(width*0.6f, height*0.125f);
			GL11.glRotatef(-90, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(width/2, height/4, 0);
		}
		if(displaySegments[6]){
			renderSegment(width*0.6f, height*0.125f);
		}
		GL11.glTranslatef(-pos.x(), -pos.y(), 0);
	}
	private void renderSegment(float width, float height){
	    GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
		GL11.glTexCoord2f(0.0f, 0.0f);
	    GL11.glVertex3f( width, height, 0);         // Top Right Of The Quad (Front)
	    GL11.glTexCoord2f(1.0f,0.0f);
	    GL11.glVertex3f(-width, height, 0);         // Top Left Of The Quad (Front)
	    GL11.glTexCoord2f(1.0f,1.0f);
	    GL11.glVertex3f(-width,-height, 0);         // Bottom Left Of The Quad (Front)
	    GL11.glTexCoord2f(0.0f,1.0f);
	    GL11.glVertex3f( width,-height, 0);         // Bottom Right Of The Quad (Front)
		GL11.glEnd();
	}
}
