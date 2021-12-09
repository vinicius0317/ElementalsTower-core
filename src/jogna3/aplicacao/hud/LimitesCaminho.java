package jogna3.aplicacao.hud;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class LimitesCaminho {
	
	//private Array<Rectangle> rectanglesCaminho;
	private Rectangle rectangleRight, rectangleLeft;
	
	
	
	
	public LimitesCaminho(float x)
	{
		
		
		rectangleRight = new Rectangle(500, 0, 700, 450);
		rectangleLeft = new Rectangle(0, 100, 200, 720);
		
		
	}
	
	public boolean collide(Rectangle enemy)
	{
		return enemy.overlaps(rectangleRight) || enemy.overlaps(rectangleLeft);
	}

}

