package jogna3.aplicacao;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class ElementalsTower extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera; 
	private Rectangle iconeJogar;
	private Texture iconeJogarImg;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("fundoTeste.png");
		iconeJogarImg = new Texture(Gdx.files.internal("iconStart.png"));
		
		
		iconeJogar = new Rectangle();
		iconeJogar.x =  600;
		iconeJogar.y = 150;
		iconeJogar.width = 10;
		iconeJogar.height = 10;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(iconeJogarImg,  iconeJogar.x,  iconeJogar.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
