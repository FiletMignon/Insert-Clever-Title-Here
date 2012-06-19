package kontrol.main.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Model {
	private final int id; //The ID for the VBO (Assigned at object init)
	private final int mode;
	private FloatBuffer vBufferData; //The buffer to draw
	private int vBufferSize; //Size of the buffer for calculations
	private Texture texture; //The texture of the model
	
	public Model(float[] vBuffer, String texturePath, int mode){
		id = createVBOID();
		vBufferSize = vBuffer.length;
	    vBufferData = BufferUtils.createFloatBuffer(vBuffer.length);
	    vBufferData.put(vBuffer);
	    vBufferData.rewind();
	    
	    if(texturePath != null){
			try {
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/textures/" + texturePath));
			} catch (Exception d) {
				try {
					texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/textures/" + "default.png"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }
	    else{
			try {
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/textures/" + "blank.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    this.mode = mode;
	    
	}
	public void render() {
		bufferBind();
		texture.bind();
	    GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
	    GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
	    GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

	    if(mode == GL11.GL_TRIANGLES){
		    GL11.glVertexPointer(3, GL11.GL_FLOAT, 36, 0);
		    GL11.glColorPointer(4, GL11.GL_FLOAT, 36, 12);
		    GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 36, 28);
		    GL11.glDrawArrays(GL11.GL_QUADS, 0, vBufferSize/9);
	    }
	    else if(mode == GL11.GL_QUADS){
		    GL11.glVertexPointer(3, GL11.GL_FLOAT, 36, 0);
		    GL11.glColorPointer(4, GL11.GL_FLOAT, 36, 12);
		    GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 36, 28);
		    GL11.glDrawArrays(GL11.GL_QUADS, 0, vBufferSize/9);
	    }
	    GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	    
	}
	private void bufferBind() {
	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
	    GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBufferData, GL15.GL_STREAM_DRAW );
	}
	private int createVBOID() {
	    IntBuffer buffer = BufferUtils.createIntBuffer(1);
	    GL15.glGenBuffers(buffer);
	    return buffer.get(0);
	}
	public static Model generateRectangularPrismModel(String texturePath, float width, float height, float depth){
		width /= 2;
		height /= 2;
		depth /= 2;
		float[] buffer = {
			//X, Y, Z, R, G, B, A, TexX, TexY,
			//Top
				 width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			    -width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
			    -width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			     width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			//Bottom
				 width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			    -width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			    -width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
			     width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			//Front
			     width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
				-width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			    -width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			     width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			//Back
				 width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			    -width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			    -width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			     width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
			//Left
				-width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
			    -width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			    -width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			    -width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			//Right
				 width,  height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 0.0f,
			     width,  height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 1.0f, 1.0f,
			     width, -height,  depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 1.0f,
			     width, -height, -depth, 1.0f,   1.0f,  1.0f,  1.0f, 0.0f, 0.0f,
				
		};
		return new Model(buffer, texturePath, GL11.GL_QUADS);
	}
}
