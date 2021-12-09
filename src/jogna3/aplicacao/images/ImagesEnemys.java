package jogna3.aplicacao.images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class ImagesEnemys {
	
	public static Texture[] tritonAndando;
	public static Sprite gnomoAndando[];
	
	
	public static void loadEnemysImage()
	{
		/*tritonLifebar = new Texture("Zombie/LifeBar/black lifebar.png");
		tritonRedLifebar = new Sprite(new Texture("Zombie/LifeBar/red lifebar.png"));*/
		
		tritonAndando = new Texture[7];
		for(int i = 0;i<7;i++)
		{
			//tritonAndando[i] = new Sprite(new Texture(new String("Inimigos/Tritao/Poses/Parado/INVERTIDO/T"+ (i+1) +".png")));
			tritonAndando[i] = new Texture(new String("Inimigos/Tritao/Poses/Parado/INVERTIDO/T"+ (i+1) +".png"));
		}
		
		gnomoAndando = new Sprite[5];
		for(int i = 0;i<5;i++)
			gnomoAndando[i] = new Sprite(new Texture(new String("Inimigos/Gnomo/POSES/Parado/INVERTIDO/GP"+ (i+1) +".png")));
		
	
	}
	
	public static void disposeImages()
	{
		for (int i = 0;i<7;i++)
		{
			/* tritonAndando[i].getTexture().dispose(); */
			tritonAndando[i].dispose();
		}
		
		
		for (int i = 0;i<5;i++)
		{
			gnomoAndando[i].getTexture().dispose();
		}
		/*tritonRedLifebar.getTexture().dispose();;
		tritonLifebar.dispose();*/
		
	}

}
