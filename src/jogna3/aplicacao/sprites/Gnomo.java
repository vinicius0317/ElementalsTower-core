package jogna3.aplicacao.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Gnomo {
	
	private static float caminharX = -45;
	private static float caminharY = -45;


	private Vector3 pos;
	private Vector3 velocidade;
	
	private Texture gnomo;
	
	
	public int gnomoWidth;
	public int gnomoHeight;
	private boolean isWalking;
	private boolean isDead;
	private boolean isHurt;
	public static float gnomoLife = 100;
	public float gnomoActualLife;
	
	public int x = 1120;
	public int y = 470;
	
	private float sizeLifeBar;
	public String status;
	
	public Gnomo(int x, int y) {
		
		pos = new Vector3(x, y, 0);
		velocidade = new Vector3(0, 0, 0);
		gnomo = new Texture("Gnomo.png");
		
		status = "vivo";
		
		
		gnomoWidth = 60;
		gnomoHeight = 130;
		
	}
	
	public void update(float dt) {
		
		if(pos.x >= 120 && pos.x < 130)
		{
			velocidade.add(caminharX, 0, 0);
		}
		else if(pos.y >= 130 && pos.y <= 180)
		{
			velocidade.add(caminharX, caminharY, 0);
		}
		else if(pos.x >= 385 && pos.x <= 550)
		{
			if(pos.x >= 385 && pos.x <= 390)
			{
				velocidade.add(0, caminharY, 0);
			}
			else
				velocidade.add(caminharX, caminharY, 0);
		}
		else {
			velocidade.add(caminharX, 0, 0);
		}
		
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
	
	public Texture getGnomo() {
		return gnomo;
	}
	
	

}
