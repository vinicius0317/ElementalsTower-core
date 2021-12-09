package jogna3.aplicacao.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import jogna3.aplicacao.ElementalsTower;
import jogna3.aplicacao.helpers.GameInfos;
import jogna3.aplicacao.helpers.GameManage;

public class TelaJogoMapa extends State {

	private Texture fundoMapa;
	
	private Texture btnAgua;
	private Texture btnTerra;
	private Texture btnAr;
	private Texture btnFogo;
	private Texture btnCinza;
	
	private Music trilha;
	
	Vector3 clickPos = new Vector3();
	
	public TelaJogoMapa(GameStateManager gsm) {
		super(gsm);
		fundoMapa = new Texture("Mapa.png");
		
		
		btnAgua = new Texture("AguaC.png");
		btnTerra = new Texture("TerraC.png");
		btnAr = new Texture("ArC.png");
		btnFogo = new Texture("FogoC.png");
		btnCinza= new Texture("Para concluir.png");
		//cam.setToOrtho(false, 1200 / 2, 700 / 2 );
		
		trilha = Gdx.audio.newMusic(Gdx.files.internal("TrilhaMapa.mp3"));
		
		trilha.setLooping(true);
		trilha.setVolume(1.0f);
		trilha.play();
	}

	@Override
	protected void handleInput() {
		if(Gdx.input.justTouched())
		{
			clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			/*if((clickPos.x >= 100 && clickPos.x <= 300) && (clickPos.y >= 400 && clickPos.y <= 500))
			//if((Gdx.input.getX() >= 1000 && Gdx.input.getX() <= 1150) && (Gdx.input.getY() >= 100 && Gdx.input.getY() <= 200))
			{
				this.dispose();
				gsm.set(new TelaTutorial(gsm));
				dispose();
			}*/
			if((clickPos.x >= 50 && clickPos.x <= 120) && (clickPos.y >= 280 && clickPos.y <= 350))
			{
				this.dispose();
				gsm.set(new TelaAgua(gsm));
				dispose();
			}
				
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		//sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(fundoMapa, 0, 0, 1200, 700);
		
		sb.draw(btnTerra, 530, 580, 70, 70);
		sb.draw(btnCinza, 530, 580, 70, 70);
		
		sb.draw(btnAgua, 50, 350, 70, 70);
		sb.draw(btnCinza, 50, 350, 70, 70);
		
		sb.draw(btnFogo, 550, 30, 70, 70);
		sb.draw(btnCinza, 550, 30, 70, 70);
		
		sb.draw(btnAr, 1050, 320, 70, 70);
		sb.draw(btnCinza, 1050, 320, 70, 70);
		/*sb.draw(fundoMapa, 0, 0, 1200, 700);*/
		
		sb.end();
		
	}

	@Override
	public void dispose() {
		
		btnTerra.dispose();
		btnAgua.dispose();
		btnCinza.dispose();
		btnFogo.dispose();
		btnAr.dispose();
		fundoMapa.dispose();
		
		
	}

}
