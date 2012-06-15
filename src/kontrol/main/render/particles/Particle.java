package kontrol.main.render.particles;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Particle {
	private Texture texture;
	public Particle(String texture){
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
}
