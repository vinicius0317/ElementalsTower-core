package jogna3.aplicacao.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


import jogna3.aplicacao.helpers.GameInfos;
import jogna3.aplicacao.images.ImagesEnemys;






public class TritonTracker extends Sprite {
	
	private float velocityX = 0;
	private float velocityY = 0;
	private float lastVelocityX;
	private float distanceTarget;
	private float angulo;
	public float vel = 170f;
	
	public static float tritonLife = 100;
	public float tritonActualLife;
	
	public Rectangle boxCollider;
	
	public Rectangle tritonHitBox;
	
	public byte tritonOnHit = 0;
	
	private float sizeLifeBar;
	
	private boolean isWalking;
	private boolean isDead;
	private boolean isHurt;
	
	public float lastPositionX;
	public float lastPositionY;
	
	
	private byte contadorWalking = 0;
	private byte contadorDead;
	
	public byte frameRepeat = 5;
	
	public int anguloIncrement = 0;
	
	public String status;
	
	
	
	public TritonTracker() 
	{
		
		super(new Texture("Tritao.png"));
		setPosition(400, 500);
		tritonActualLife = tritonLife;
		isWalking = false;
		isDead = false;
		
		boxCollider = new Rectangle();
		boxCollider.width = 80;
		boxCollider.height = 135;
		
		tritonHitBox = new Rectangle();
		tritonHitBox.width = 50f;
		tritonHitBox.height = 90f;
		
		contadorWalking = 0;
		contadorDead = 0;
		
		status = "alive";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setVelocityX(float velocityX)
	{
		this.velocityX = velocityX;
	}
	
	public float getVelocityX()
	{
		return this.velocityX;
	}
	
	public void setVelocityY(float velocityY)
	{
		this.velocityY = velocityY;
	}
	
	public float getVelocityY()
	{
		return this.velocityY;
	}
	
	public void setWalking(boolean isWalking)
	{
		this.isWalking = isWalking;
	}
	
	public void setLastVelocityX(float lastVelocityX)
	{
		this.lastVelocityX = lastVelocityX;
	}


	public boolean getDead() 
	{
		return isDead;
	}

	public void setDead(boolean isDead) 
	{
		this.isDead = isDead;
	}
	
	public float getDistanceTarget()
	{
		return this.distanceTarget;
	}

}
