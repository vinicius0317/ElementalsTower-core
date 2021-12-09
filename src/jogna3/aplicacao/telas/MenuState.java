package jogna3.aplicacao.telas;

import org.ietf.jgss.GSSManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import jogna3.aplicacao.telas.TelaJogoMapa;

public class MenuState extends State{

	private Texture fundo;
	private Texture fundoMapa;
	/*private static final int fundoWidth = 1200;
	private static final int fundoHeight = 720;*/
	private Texture btnJogarr;
	private Texture btnCreditoss;
	private Texture btnOpcoes;
	
	private Texture capaInicio;
	private Texture btnCredito;
	private Texture btnCreditoSel;
	private Texture btnJogar;
	private Texture btnJogarSel;
	private Texture btnSair;
	private Texture btnSairSel;
	private Texture CreditosPopUp;
	private int i = 0;
	
	private boolean isCreditosOpen = false;
	
	private Music trilha;
	
	Vector3 clickPos = new Vector3();
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		fundo = new Texture("TelaInicial/Capa.png");
		fundoMapa = new Texture("Mapa.png");
		//btnOpcoes = new Texture("iconOptions.png");
		
		capaInicio = new Texture("TelaInicial/Capa.png");
		btnCredito = new Texture("TelaInicial/Credito.png");
		btnCreditoSel = new Texture("TelaInicial/CreditoSel.png");
		btnJogar = new Texture("TelaInicial/Jogar.png");
		btnJogarSel = new Texture("TelaInicial/JogarSel.png");
		btnSair = new Texture("TelaInicial/Sair.png");
		btnSairSel = new Texture("TelaInicial/SairSel.png");
		
		CreditosPopUp = new Texture("CreditosPopUp.png");
		
		
		trilha = Gdx.audio.newMusic(Gdx.files.internal("Trilha.mp3"));
		
		trilha.setLooping(true);
		trilha.setVolume(1.0f);
		trilha.play();
		
		
		
		 
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched())
		{
			clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			if((Gdx.input.getX() >= 80 && Gdx.input.getX() <= 210) && (Gdx.input.getY() >= 470 && Gdx.input.getY() <= 520))
			{
				this.dispose();
				//gsm.set(new TelaJogoMapa(gsm));
				gsm.set(new TelaTutorial(gsm));
				//gsm.set(new TelaAgua(gsm));
				trilha.pause();
				dispose();
			}
			else if((Gdx.input.getX() >= 90 && Gdx.input.getX() <= 220) && (Gdx.input.getY() >= 620 && Gdx.input.getY() <= 670))
			{
				System.exit(0);
			}
			
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		/*clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		if((clickPos.x >= 800 && clickPos.x <= 980) && (clickPos.y >= 400 && clickPos.y <= 450))
		{
			sb.draw(fundoMapa, 0, 0, 1200, 700);
		}
		else
		{*/
			sb.draw(fundo, 0, 0, 1200, 700);
		//} 
		if((Gdx.input.getX() >= 80 && Gdx.input.getX() <= 210) && (Gdx.input.getY() >= 470 && Gdx.input.getY() <= 520))
			sb.draw(btnJogarSel,  80,  180, 130, 50);
		else
			sb.draw(btnJogar,  80,  180, 130, 50);
		
		if((Gdx.input.getX() >= 90 && Gdx.input.getX() <= 220) && (Gdx.input.getY() >= 540 && Gdx.input.getY() <= 590))
			sb.draw(btnCreditoSel,  90,  110, 130, 50);
		else
			sb.draw(btnCredito,  90,  110, 130, 50);
		if((Gdx.input.getX() >= 90 && Gdx.input.getX() <= 220) && (Gdx.input.getY() >= 620 && Gdx.input.getY() <= 670))
			sb.draw(btnSairSel,  90,  30, 130, 50);
		else
			sb.draw(btnSair,  90,  30, 130, 50);
		
		
		if((clickPos.x >= 90 && clickPos.x <= 220) && (clickPos.y >= 540 && clickPos.y <= 590))
		{
			sb.draw(CreditosPopUp, 300, 50, 700, 600);	
		}

		sb.end();
		
	}

	@Override
	public void dispose() {
		fundo.dispose();
		btnJogar.dispose();
		btnJogarSel.dispose();
		btnCredito.dispose();
		btnCreditoSel.dispose();
		btnSair.dispose();
		btnSairSel.dispose();
		
	}
	

}
