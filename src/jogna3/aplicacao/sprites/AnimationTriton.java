package jogna3.aplicacao.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class AnimationTriton {
	
	private Array<TextureRegion> frames;
	private float maxFrameTime;
	private float currentFrameTime;
	private int frameCount;
	private int frame;
	
	private float currentSpriteTime;
	private float maxSpriteCount;
	private int spriteCount;
	private int sprite;
	
	public static Sprite tritonWalking[];  
	
	
	public AnimationTriton(TextureRegion region, int frameCount, float cycleTime)
	{
		
		/*tritonWalking = new Sprite[7];
		
		for (int i = 0; i < 7; i++)
		{
			tritonWalking[i] = new Sprite(new Texture(new String("Inimigos/Tritao/Poses/Parado/INVERTIDO/T"+ (i+1) + ".png")));
		}*/
		
		//maxSpriteCount = 7;
		
		frames = new Array<TextureRegion>();
		int frameWidth = region.getRegionWidth() / frameCount;
		for (int i = 0; i < frameCount; i++)
		{
			frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
		}
		this.frameCount = frameCount;
		maxFrameTime = cycleTime / frameCount;
		frame = 0;
		
	}
	
	public void update(float dt)
	{
		currentSpriteTime += dt;
		if(currentFrameTime > maxFrameTime)
		{
			frame++;
			currentFrameTime = 0;
		}
		if(frame >= frameCount)
			frame = 0;
	}
	
	public TextureRegion getFrame() {
		return frames.get(frame);
	}
	
	public static void DisposeImages()
	{
		for(int i = 0; i < 7; i++)
		{
			tritonWalking[i].getTexture().dispose();
		}
	}
	

}
