package jogna3.aplicacao.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import jogna3.aplicacao.helpers.GameManage;
import jogna3.aplicacao.images.ImagesEnemys;




public class Triton {
	
	private static float caminharX = -65;
	private static float caminharY = -65;


	private Vector3 pos;
	private Vector3 velocidade;
	
	private Texture triton;
	public int tritonWidth;
	public int tritonHeight;
	private boolean isWalking;
	private boolean isDead;
	private boolean isHurt;
	public static float tritonLife = 100;
	public float tritonActualLife;
	
	public int x = 1100;
	public int y = 500;
	
	private float sizeLifeBar;
	public String status;
	
	public Rectangle boxCollider;
	
	private AnimationTriton tritonAnimation;
	
	private Rectangle position;

	
	
	
	public Triton(int x, int y)
	{
		/*pos = new Vector3(x, y, 0);
		velocidade = new Vector3(0, 0, 0);
		triton = new Texture("Tritao.png");
		
		status = "vivo";
		
		boxCollider = new Rectangle();
		boxCollider.width = 100;
		boxCollider.height = 80;
		
		
		tritonWidth = 100;
		tritonHeight = 80;*/
		
		pos = new Vector3(x, y, 0);
		velocidade = new Vector3(0, 0, 0);
		triton = new Texture("Tritao.png");
		
		Texture textures = new Texture("Inimigos/Tritao/Poses/Parado/INVERTIDO/TritonsAndando.png");
		tritonAnimation = new AnimationTriton(new TextureRegion(textures), 4, 0.5f);
		position = new Rectangle(x, y, textures.getWidth() / 5, textures.getHeight());
		
		status = "vivo";
		
		
		tritonWidth = 100;
		tritonHeight = 80;
		
		
		
	}
	

	public void drawTritonAnimation(SpriteBatch batch)
	{
			
		batch.draw(ImagesEnemys.tritonAndando[0],1000,500);		
		
	}
	
	
	
	//triton = new Triton(420, 480);
	//triton = new Triton(330, 180);
	public void update(float dt) {
		/*if(pos.x >= 280 && pos.x <= 330)
		{
			velocidade.add(caminharX, 0, 0);
		}
		else
			setCaminharX(0);*/
		
		tritonAnimation.update(dt);
		
		if(pos.x >= 120 && pos.x < 130)
		{
			velocidade.add(caminharX, 0, 0);
		}
		else if(pos.y >= 130 && pos.y <= 180)
		{
			//if()
			
			velocidade.add(caminharX, caminharY, 0);
		}
		else if(pos.x <= 410 && pos.x >= 310)
		{
			if(pos.x >= 340 && pos.x <= 345)
			{
				velocidade.add(0, caminharY, 0);
			}
			else
				velocidade.add(caminharX, caminharY, 0);
			//else {
				//velocidade.add(caminharX, caminharY, 0);
			//}
		}
		else {
			velocidade.add(caminharX, 0, 0);
		}
		//else if(pos.x <= 410 && pos.x >= 330)
		//velocidade.add(caminharX, 0, 0);
		
		velocidade.scl(dt);
		pos.add(velocidade.x, velocidade.y, 0);
		
		//velocidade.scl(1/dt);
	}

	
	public Vector3 getPos() {
		return pos;
	}
	
	public void setCaminharX(float caminharX)
	{
		this.caminharX = caminharX;
	}
	
	public void setCaminharY(float caminharY)
	{
		this.caminharY = caminharY;
	}
	
	/*
	 * public Texture getTriton() { return tritonAnimation.getFrame(); //return
	 * triton; }
	 */
	
	public TextureRegion getTriton() {
		return tritonAnimation.getFrame();
		//return triton;
	}

}
