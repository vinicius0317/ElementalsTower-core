package jogna3.aplicacao;

import java.awt.Menu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import jogna3.aplicacao.telas.GameStateManager;
import jogna3.aplicacao.telas.MenuState;
import jogna3.aplicacao.telas.State;

public class ElementalsTower extends ApplicationAdapter {
	//SpriteBatch batch;
	private GameStateManager gsm;
	private SpriteBatch batch;
	
	Texture img;
	private OrthographicCamera camera; 
	private Rectangle iconeJogar;
	private Texture iconeJogarImg;
	private Rectangle iconeCreditos;
	private Texture iconeCreditosImg;
	private Rectangle iconeOptions;
	private Texture iconeOptionsImg;
	
	
	
	//State state = new State();
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("fundoTeste.png");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		
		/*batch = new SpriteBatch();
		img = new Texture("fundoTeste.png");
		iconeJogarImg = new Texture(Gdx.files.internal("iconStart.png"));
		iconeCreditosImg = new Texture(Gdx.files.internal("iconCredits.png"));
		iconeOptionsImg = new Texture(Gdx.files.internal("iconOptions.png"));
		
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		
		iconeJogar = new Rectangle();
		iconeJogar.x =  1000;
		iconeJogar.y = 200;
		iconeJogar.width = 10;
		iconeJogar.height = 10;
		
		iconeOptions = new Rectangle();
		iconeOptions.x =  1000;
		iconeOptions.y = 170;
		iconeOptions.width = 10;
		iconeOptions.height = 10;
		
		iconeCreditos = new Rectangle();
		iconeCreditos.x =  1000;
		iconeCreditos.y = 100;
		iconeCreditos.width = 10;
		iconeCreditos.height = 10;*/
	}

	@Override 
	public void render () {
		
		ScreenUtils.clear(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		
		/*ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gsm.update(Gdx.graphics.getDeltaTime());
		
		
		
		batch.draw(img, 0, 0);
		batch.draw(iconeJogarImg,  iconeJogar.x,  iconeJogar.y);
		batch.draw(iconeOptionsImg,  iconeOptions.x,  iconeOptions.y);
		batch.draw(iconeCreditosImg,  iconeCreditos.x,  iconeCreditos.y);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
