package jogna3.aplicacao.telas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TelaCreditos extends State{

	private Texture imgFundo;
	
	protected TelaCreditos(GameStateManager gsm) {
		super(gsm);
		
		imgFundo = new Texture("Creditos.png");
		
	}

	@Override
	protected void handleInput() {
		
	}

	@Override
	public void update(float dt) {
		
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		
		sb.draw(imgFundo, 0, 0, 1200, 700);
		
		sb.end();
	}

	@Override
	public void dispose() {
		
		
	}
	
	

}
