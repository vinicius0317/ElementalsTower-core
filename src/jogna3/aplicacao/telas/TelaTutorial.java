package jogna3.aplicacao.telas;

import java.util.Iterator;

import javax.net.ssl.SSLEngineResult.Status;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import jogna3.aplicacao.helpers.GameInfos;
import jogna3.aplicacao.helpers.GameManage;
import jogna3.aplicacao.hud.HudInfos;
import jogna3.aplicacao.hud.LimitesCaminho;
import jogna3.aplicacao.images.ImagesEnemys;
import jogna3.aplicacao.sprites.Gnomo;
import jogna3.aplicacao.sprites.Triton;
import jogna3.aplicacao.sprites.TritonController;
import jogna3.aplicacao.sprites.TritonTracker;


public class TelaTutorial extends State {

	private Texture fundoTela;
	private HudInfos hud;
	public Label goldPlayerText;
	private Texture placaVenda;
	private Texture circuloInvoc;
	private Texture cancelado;
	private Texture tritonImgDano;
	private Texture tritonImg;
	private Texture gnomoImgDano;
	private Texture gnomoImg;
	private Texture fadaImgDano;
	private Texture fadaImg;
	private Texture salamandraImg;
	private Texture salamandraImgDano;	
	
	
	private Texture[] Lasers;
	private Texture[] atkFogo;
	private Texture[] atkGelo;
	private Texture[] conjFogo;
	private Texture[] conjGelo;
	
	private String elementoAtual = "Terra";

	private TritonTracker tritonTracker; 
	
	private boolean txtRegraOpened = false;
	
	
	private Music trilha;
	
	private boolean duranteWave = false;
	private boolean placa1 = true;
	private boolean placa2 = true;
	private boolean placa3 = true;
	private boolean placa4 = true;
	private boolean inicioWave = false;
	private boolean isPaused = true;
	private int goldActual = 500;
	private int lifeActual = 20;
	private int waveActual = 1;
	private int torreWidth = 420;
	private int torreHeight = 420;
	private int elementoTorreSelecionado = 1;
	
	private int[] elementosTorreSelecionados;
	
	private int indexMata = 0; 
	
	// Elementos torres selecionados 1 = Terra, 2 = Ar, 3 = Fogo, 4 = Gelo;
	
	//private TritonController triton;
	
	
	private int enemyLife = 12;
	private int enemyActualLife = enemyLife;
	
	public Triton tritonn;
	
	GameManage gm;
	
	private Triton triton[];
	private Triton tritons[];
	private Gnomo gnomo;
	
	
	
	Vector3 clickPos = new Vector3();
	
	int placaParaTirar = 1;
	
	private Array<Rectangle> tritonArray;
	private Array<Rectangle> gnomoArray;
	private Array<Rectangle> fadaArray;
	private Array<Rectangle> salamandraArray;
	
	private Array<Integer> tritonLife;
	private int monstroAtacar;
	
	private long instanteUltimaTriton;
	
	private Texture[] tritonImgs;
	
	private Texture[] torres1Imgs;
	private Texture[] torres2Imgs;
	private Texture[] torres3Imgs;
	private Texture[] torres4Imgs;
	
	private Texture[] torres1ImgsAtk;
	private Texture[] torres2ImgsAtk;
	private Texture[] torres3ImgsAtk;
	private Texture[] torres4ImgsAtk;
	
	private Texture[] placasTutorial;
	
	private int[] lvlTorre;
	private boolean temTorre1;
	private boolean temTorre2;
	private boolean temTorre3;
	private boolean temTorre4;
	
	private int estadoTorre1 = 1;
	private int estadoTorre2 = 1;
	private int estadoTorre3 = 1;
	private int estadoTorre4 = 1;
	
	private boolean AtacarInimigo = false;
	
	private Array<Rectangle> ataqueInimigo;
	private long instanteUltimoAtaque = 0;
	private long startTime;
	private long elapsedTime;
	
	private int indexCurva = 2;
	private int indexInfosTutorial = 0;
	
	private int contAtaques = 0;
	
	BitmapFont fontLife;
	BitmapFont fontGold;
	BitmapFont fontWave;
	
	public TelaTutorial(GameStateManager gsm) {
		super(gsm);
		fundoTela = new Texture("FaseTutorial.png");
		placaVenda = new Texture("Placa.png");
		circuloInvoc = new Texture("CirculoInvocacao.png");
		cancelado = new Texture("Cancelado.png");
		tritonImg = new Texture("Inimigos/Tritao/Poses/Parado/INVERTIDO/T1.png");
		tritonImgDano = new Texture("Inimigos/Tritao/Poses/Dano/INVERTIDO/TD4.png");
		gnomoImg = new Texture("Inimigos/Gnomo/POSES/Parado/INVERTIDO/GP1.png");
		gnomoImgDano = new Texture("Inimigos/Gnomo/POSES/Dano/INVERTADO/G5.png");
		fadaImg = new Texture("Inimigos/Fada/Poses/Voando_Parado/INVERTIDO/F1.png");
		fadaImgDano = new Texture("Inimigos/Fada/Poses/Dano/INVERTIDO/FD4.png");
		salamandraImg = new Texture("Inimigos/Salamandra/Poses/Parado/INVERTIDO/S1.png");
		salamandraImgDano = new Texture("Inimigos/Salamandra/Poses/Dano/INVERTIDO/SA9.png");
		
		
		elementosTorreSelecionados = new int[4];
		for(int i=0; i<4; i++)
		{
			elementosTorreSelecionados[i] = i+1;
		}
		
		
		torres1Imgs = new Texture[2];
		torres2Imgs = new Texture[2];
		torres3Imgs = new Texture[2];
		torres4Imgs = new Texture[2];
		
		torres1ImgsAtk = new Texture[2];
		torres2ImgsAtk = new Texture[2];
		torres3ImgsAtk = new Texture[2];
		torres4ImgsAtk = new Texture[2];
		
		Lasers = new Texture[4];
		
		for(int i=0; i<4; i++)
		{
			Lasers[i] = new Texture("Lasers/Laser0" + (i +1) + ".png");
		}
		
		
		for(int i = 0; i < 2; i++)
		{
			torres1Imgs[i] = new Texture("Torres/TorreTerra0" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres2Imgs[i] = new Texture("Torres/TorreVento0" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres3Imgs[i] = new Texture("Torres/TorreFogo0" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres4Imgs[i] = new Texture("Torres/TorreGelo0" + (i +1) + ".png");
		}
		
		
		for(int i = 0; i < 2; i++)
		{
			torres1ImgsAtk[i] = new Texture("Torres/TorreTerra020" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres2ImgsAtk[i] = new Texture("Torres/TorreVento020" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres3ImgsAtk[i] = new Texture("Torres/TorreFogo020" + (i +1) + ".png");
		}
		for(int i = 0; i < 2; i++)
		{
			torres4ImgsAtk[i] = new Texture("Torres/TorreGelo020" + (i +1) + ".png");
		}
		
		
		lvlTorre = new int[4];
		
		for(int i = 0; i < 4; i++)
		{
			lvlTorre[i] = 1;
		}
		
		
		placasTutorial = new Texture[16];
		for(int i = 0; i < 16; i++)
		{
			placasTutorial[i] = new Texture("InfosTutorial/Infos" + (i +1) + ".png");
		}
		
		
		atkGelo = new Texture[3];
		for(int i = 0; i<3; i++) {
			atkGelo[i] = new Texture("Gelo/Nv1/Ataque/Geloarr" + (i+1) + ".png");
		}
		
		atkFogo = new Texture[6];
		for(int i = 0; i<6; i++) {
			atkFogo[i] = new Texture("Fogo/Nv1/Ataque/arnv" + (i+1) + ".png");
		}
		
		
		ImagesEnemys.loadEnemysImage();
		
		/*for(int i= 0; i< 7; i++)
		{
			tritonImgs[i] = new ImagesEnemys().tritonAndando[i];
			i++;
		}*/
		
		//this.gsm = gsm;
		
		triton = new Triton[gm.enemyNumber];
		
		if(placa1 && placa2 && placa3 && placa4) {
			placa1 = true;
		}
		
		waveActual = 1;
		
		if(waveActual == 1)
		{
			/*triton = new Triton(1100, 500);
			triton = new Triton(1100, 600);*/
		}
		
		gnomo = new Gnomo(1100, 530);
		
		hud = new HudInfos(gsm);
		hud.createTexts();
		
		fontLife = new BitmapFont(Gdx.files.internal("fonts/squealerVida.fnt"));
		fontGold = new BitmapFont(Gdx.files.internal("fonts/squealerGold.fnt"));
		fontWave = new BitmapFont(Gdx.files.internal("fonts/squealerWaves.fnt"));
		
		//font = (Gdx.files.internal("fonts/palamecia tiling.ttf"));
		 
		
		tritonArray = new Array<Rectangle>();
		gnomoArray = new Array<Rectangle>();
		fadaArray = new Array<Rectangle>();
		salamandraArray = new Array<Rectangle>();
		tritonArray = new Array<Rectangle>();
		
		temTorre1 = false;
		temTorre2 = false;
		temTorre3 = false;
		temTorre4 = false;
		//gerarTritons();
		
		startTime = System.currentTimeMillis();
		instanteUltimoAtaque = TimeUtils.nanoTime();
		
		trilha = Gdx.audio.newMusic(Gdx.files.internal("TrilhaTutorial.mp3"));
		
		trilha.setLooping(true);
		trilha.setVolume(1.0f);
		trilha.play();
		
	}

	
	private void gerarTritons(){
		Rectangle TritonsRectangle = new Rectangle();
		
		TritonsRectangle.x = MathUtils.random(1220, 1420);
		TritonsRectangle.y = MathUtils.random(470, 520);
		TritonsRectangle.width = 100;
		TritonsRectangle.height = 80;
		tritonArray.add(TritonsRectangle);
		//instanteUltimaTriton = TimeUtils.nanoTime();
	}

	private void gerarGnomo(){
		Rectangle gnomoRectangle = new Rectangle();
		
		gnomoRectangle.x = MathUtils.random(1220, 1420);
		gnomoRectangle.y = MathUtils.random(470, 520);
		gnomoRectangle.width = 60;
		gnomoRectangle.height = 100;
		gnomoArray.add(gnomoRectangle);
		//instanteUltimaTriton = TimeUtils.nanoTime();
	}
	
	private void gerarFada(){
		Rectangle fadaRectangle = new Rectangle();
		
		fadaRectangle.x = MathUtils.random(1220, 1420);
		fadaRectangle.y = MathUtils.random(470, 540);
		fadaRectangle.width = 60;
		fadaRectangle.height = 80;
		fadaArray.add(fadaRectangle);
		//instanteUltimaTriton = TimeUtils.nanoTime();
	}
	
	private void gerarSalamandra(){
		Rectangle salamandraRectangle = new Rectangle();
		
		salamandraRectangle.x = MathUtils.random(1220, 1420);
		salamandraRectangle.y = MathUtils.random(470, 520);
		salamandraRectangle.width = 120;
		salamandraRectangle.height = 70;
		salamandraArray.add(salamandraRectangle);
		//instanteUltimaTriton = TimeUtils.nanoTime();
	}
	
	
	@Override
	protected void handleInput() {
		if(Gdx.input.justTouched())
		{
			clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			/*else if((clickPos.x >= 360 && clickPos.x <= 460) && (clickPos.y >= 100 && clickPos.y <= 200))
			{
				setPlaca2(false);
			}*/
			if((clickPos.x >= 100 && clickPos.x <= 280) && (clickPos.y >= 300 && clickPos.y <= 500))
			{
				if(goldActual >= 400 && !temTorre1 && (lvlTorre[0] != 2)) {
					if(placa1)
						setPlaca1(false);
					else
						setPlaca1(true);
					goldActual -= 400;
				}
				else if(temTorre1 && (lvlTorre[0] == 2))
				{
					if(placa1)
						setPlaca1(false);
					else
						setPlaca1(true);
					lvlTorre[0] = 1;
					temTorre1 = false;
					goldActual += 400;
				}
				if((clickPos.x >= 170 && clickPos.x <= 220) && (clickPos.y >= 300 && clickPos.y <= 350) && (!temTorre1) && (!placa1))
				{
					elementosTorreSelecionados[0] = 1;
					temTorre1 = true;
				}
				else if((clickPos.x >= 230 && clickPos.x <= 280) && (clickPos.y >= 360 && clickPos.y <= 410) && (!temTorre1) && (!placa1))
				{
					elementosTorreSelecionados[0] = 2;
					temTorre1 = true;
				}
				else if((clickPos.x >= 170 && clickPos.x <= 220) && (clickPos.y >= 420 && clickPos.y <= 470) && (!temTorre1) && (!placa1))
				{
					elementosTorreSelecionados[0] = 3;
					temTorre1 = true;
				}
				else if((clickPos.x >= 110 && clickPos.x <= 160) && (clickPos.y >= 360 && clickPos.y <= 410) && (!temTorre1) && (!placa1))
				{
					elementosTorreSelecionados[0] = 4;
					temTorre1 = true;
				}
				else if((temTorre1) && (!placa1))
				{
					//lvlTorre[0] = 2;
					goldActual += 400;
					temTorre1 = false;
					placa1 = true;
				}
				else {
					
				}
			}
			if((clickPos.x >= 320 && clickPos.x <= 500) && (clickPos.y >= 30 && clickPos.y <= 220))
			{
				if(goldActual >= 400 && !temTorre2 && (lvlTorre[1] != 2)) {
					if(placa2)
						setPlaca2(false);
					else
						setPlaca2(true);
					goldActual -= 400;
				}
				else if(temTorre2 && (lvlTorre[1] == 2))
				{
					if(placa2)
						setPlaca2(false);
					else
						setPlaca2(true);
					lvlTorre[1] = 1;
					temTorre2 = false;
					goldActual += 400;
				}
				if((clickPos.x >= 390 && clickPos.x <= 440) && (clickPos.y >= 30 && clickPos.y <= 80) && (!temTorre2) && (!placa2))
				{
					elementosTorreSelecionados[1] = 1;
					temTorre2 = true;
				}
				else if((clickPos.x >= 450 && clickPos.x <= 500) && (clickPos.y >= 90 && clickPos.y <= 140) && (!temTorre2) && (!placa2))
				{
					elementosTorreSelecionados[1] = 2;
					temTorre2 = true;
				}
				else if((clickPos.x >= 390 && clickPos.x <= 440) && (clickPos.y >= 160 && clickPos.y <= 210) && (!temTorre2) && (!placa2))
				{
					elementosTorreSelecionados[1] = 3;
					temTorre2 = true;
				}
				else if((clickPos.x >= 330 && clickPos.x <= 380) && (clickPos.y >= 90 && clickPos.y <= 140) && (!temTorre2) && (!placa2))
				{
					elementosTorreSelecionados[1] = 4;
					temTorre2 = true;
				}
				else if((temTorre2) && (!placa2))
				{
					//lvlTorre[1] = 2;
					goldActual += 400;
					temTorre2 = false;
					placa2 = true;
				}
				else {
					
				}
				
			}
			
			else if((clickPos.x >= 520 && clickPos.x <= 700) && (clickPos.y >= 250 && clickPos.y <= 450))
			{
				if(goldActual >= 400 && !temTorre3 && (lvlTorre[2] != 2)) {
					if(placa3)
						setPlaca3(false);
					else
						setPlaca3(true);
					goldActual -= 400;
				}
				else if(temTorre3 && (lvlTorre[2] == 2))
				{
					if(placa3)
						setPlaca3(false);
					else
						setPlaca3(true);
					lvlTorre[2] = 1;
					temTorre3 = false;
					goldActual += 400;
				}
				if((clickPos.x >= 590 && clickPos.x <= 640) && (clickPos.y >= 270 && clickPos.y <= 320) && (!temTorre3) && (!placa3))
				{
					elementosTorreSelecionados[2] = 1;
					temTorre3 = true;
				}
				else if((clickPos.x >= 650 && clickPos.x <= 700) && (clickPos.y >= 330 && clickPos.y <= 380) && (!temTorre3) && (!placa3))
				{
					elementosTorreSelecionados[2] = 2;
					temTorre3 = true;
				}
				else if((clickPos.x >= 590 && clickPos.x <= 640) && (clickPos.y >= 390 && clickPos.y <= 450) && (!temTorre3) && (!placa3))
				{
					elementosTorreSelecionados[2] = 3;
					temTorre3 = true;
				}
				else if((clickPos.x >= 520 && clickPos.x <= 580) && (clickPos.y >= 330 && clickPos.y <= 380) && (!temTorre3) && (!placa3))
				{
					elementosTorreSelecionados[2] = 4;
					temTorre3 = true;
				}
				else if((temTorre3) && (!placa3))
				{
					//lvlTorre[2] = 2;
					temTorre3 = false;
					placa3 = true;
					goldActual += 400;
				}
				else {
					
				}
			}
			else if((clickPos.x >= 1000 && clickPos.x <= 1180) && (clickPos.y >= 230 && clickPos.y <= 420))
			{
				if(goldActual >= 400 && !temTorre4 && (lvlTorre[3] != 2)) {
					if(placa4)
						setPlaca4(false);
					else
						setPlaca4(true);
					goldActual -= 400;
				}
				else if(temTorre4 && (lvlTorre[3] == 2))
				{
					if(placa4)
						setPlaca4(false);
					else
						setPlaca4(true);
					lvlTorre[3] = 1;
					temTorre4 = false;
					goldActual += 400;
				}
				if((clickPos.x >= 1070 && clickPos.x <= 1120) && (clickPos.y >= 230 && clickPos.y <= 280) && (!temTorre4) && (!placa4))
				{
					elementosTorreSelecionados[3] = 1;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1130 && clickPos.x <= 1180) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre4) && (!placa4))
				{
					elementosTorreSelecionados[3] = 2;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1070 && clickPos.x <= 1120) && (clickPos.y >= 360 && clickPos.y <= 410) && (!temTorre4) && (!placa4))
				{
					elementosTorreSelecionados[3] = 3;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1000 && clickPos.x <= 1050) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre4) && (!placa4))
				{
					elementosTorreSelecionados[3] = 4;
					temTorre4 = true;
				}
				else if((temTorre4) && (!placa4))
				{
					//lvlTorre[3] = 2;
					temTorre4 = false;
					placa4 = true;
					goldActual += 400;
				}
				else {
					
				}
			}
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		/*for(int i = 0; i < 2; i++)
		{
			triton[i].update(dt);
		}*/
		/*if(LimitesCaminho.collide(tritonTeste))
		//tritonTeste*/
		//gnomo.update(dt);
	}	


	@Override
	public void render(SpriteBatch sb) {
		
				

		sb.begin();
		sb.draw(fundoTela, 0, 0, 1200, 700);
		
		if(inicioWave) {
			System.out.println("Entrou if");
			/*for(int i = 0; i < 3; i++) {
				if(waveActual == 1) {
					gerarTritons();
				}
				else if(waveActual == 2)
				{
					//System.out.println("Gerou Gnomo");
					gerarGnomo();
				}
				else if(waveActual == 3)
				{
					gerarFada();
					//System.out.println("GerouFada");
				}
				else if(waveActual == 4)
				{
					gerarSalamandra();
				}
			}*/
			for(int i = 0; i < 3; i++) {
				if(waveActual == 1) {
					gerarTritons();
				}
				else if(waveActual == 3)
				{
					gerarFada();
					//System.out.println("GerouFada");
				}
				else if(waveActual == 4)
				{
					gerarSalamandra();
				}
			}
			for(int i = 0; i <2; i++)
			{
				
				if(waveActual == 2)
				{
					//System.out.println("Gerou Gnomo");
					gerarGnomo();
				}
			}
			inicioWave = false;
			duranteWave = true;
		}
		

		
		if(placa1)
		{
			sb.draw(placaVenda, 140, 300, 100, 100);
		}
		else {
			//sb.draw(placaVenda, 170, 350, 50, 50);
			if(temTorre1)
			{
				if(estadoTorre1 == 1)
				{
					if(elementosTorreSelecionados[0] == 1)
						sb.draw(torres1Imgs[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 2)
						sb.draw(torres2Imgs[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 3)
						sb.draw(torres3Imgs[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 4)
						sb.draw(torres4Imgs[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
				}
				else {
					if(elementosTorreSelecionados[0] == 1)
						sb.draw(torres1ImgsAtk[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 2)
						sb.draw(torres2ImgsAtk[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 3)
						sb.draw(torres3ImgsAtk[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[0] == 4)
						sb.draw(torres4ImgsAtk[(lvlTorre[0] - 1)], -20, 240, torreWidth, torreHeight);
				}
			}
			else
				sb.draw(circuloInvoc, 100, 220, 180, 180);
			
		}
		
		if(placa2)
		{
			sb.draw(placaVenda, 360, 570, 100, 100);
		}
		else{
			if(temTorre2)
			{
				if(estadoTorre2 == 1)
				{
					if(elementosTorreSelecionados[1] == 1)
						sb.draw(torres1Imgs[(lvlTorre[1] - 1)], 250, 500, 320, 320);
					else if(elementosTorreSelecionados[1] == 2)
						sb.draw(torres2Imgs[(lvlTorre[1] - 1)], 250, 520, 320, 320);
					else if(elementosTorreSelecionados[1] == 3)
						sb.draw(torres3Imgs[(lvlTorre[1] - 1)], 250, 500, 320, 320);
					else if(elementosTorreSelecionados[1] == 4)
						sb.draw(torres4Imgs[(lvlTorre[1] - 1)], 250, 480, 320, 320);
				}
				else
				{
					if(elementosTorreSelecionados[1] == 1)
						sb.draw(torres1ImgsAtk[(lvlTorre[1] - 1)], 250, 500, 320, 320);
					else if(elementosTorreSelecionados[1] == 2)
						sb.draw(torres2ImgsAtk[(lvlTorre[1] - 1)], 250, 520, 320, 320);
					else if(elementosTorreSelecionados[1] == 3)
						sb.draw(torres3ImgsAtk[(lvlTorre[1] - 1)], 250, 500, 320, 320);
					else if(elementosTorreSelecionados[1] == 4)
						sb.draw(torres4ImgsAtk[(lvlTorre[1] - 1)], 250, 480, 320, 320);
				}
			}
			else
				sb.draw(circuloInvoc, 320, 490, 180, 180);
		}
		
		
		
		for(Iterator<Rectangle> iter = tritonArray.iterator(); iter.hasNext();) 
		{
			Rectangle triton = iter.next();
			
			if(triton.x >= 120 && triton.x < 130)
			{
				triton.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(triton.y >= 130 && triton.y <= 180)
			{
				triton.x -= 50 * Gdx.graphics.getDeltaTime();
				triton.y -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(triton.x <= 490 && triton.x >= 310)
			{
				if(triton.x >= 340 && triton.x <= 345)
				{
					triton.y -= 50 * Gdx.graphics.getDeltaTime();
				}
				else {
					triton.x -= 50 * Gdx.graphics.getDeltaTime();
					triton.y -= 50 * Gdx.graphics.getDeltaTime();
				}
					
			}
			else {
				triton.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			
			if(triton.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 2;
					inicioWave = true;
					System.out.println("entrou Triton");
				}
			}
			if(temTorre1 && triton.x <= 200 && (elementosTorreSelecionados[0] == 2)
					|| (temTorre1 && (temTorre2 || temTorre3 || temTorre4) && (triton.x <= 200)))
			{		
				estadoTorre2 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 2;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre2 && (triton.x <= 412 && triton.x >= 412) && (elementosTorreSelecionados[1] == 2)
					|| (temTorre2 && (temTorre3 || temTorre4) && (triton.x <= 412 && triton.x >= 410)) )
			{
				System.out.println("EntrouAqui2");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 2;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre3 && (triton.x <= 553 && triton.x >= 551) && (elementosTorreSelecionados[2] == 2)
					|| (temTorre3 && (temTorre4) && (triton.x <= 551 && triton.x >= 551)) )
			{
				System.out.println("Entrou No terceiro IF");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 2;
					inicioWave = true;
				}
				iter.remove();
			}
			/*if(!iter.hasNext())
			{
				inicioWave = true;
				waveActual = 2;
			}*/
		}
		
		
		for(Iterator<Rectangle> iter = gnomoArray.iterator(); iter.hasNext();) 
		{
			Rectangle gnomo = iter.next();
			
			/*indexCurva++; 
			System.out.println(indexCurva);*/
			
			if(gnomo.x >= 120 && gnomo.x < 130)
			{
				gnomo.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(gnomo.y >= 130 && gnomo.y <= 180)
			{
				gnomo.x -= 50 * Gdx.graphics.getDeltaTime();
				gnomo.y -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(gnomo.x <= 510 && gnomo.x >= 310)
			{
				if(gnomo.x >= 340 && gnomo.x <= 345)
				{
					gnomo.y -= 50 * Gdx.graphics.getDeltaTime();
				}
				else {
					gnomo.x -= 50 * Gdx.graphics.getDeltaTime();
					gnomo.y -= 50 * Gdx.graphics.getDeltaTime();
				}
			}
			else {
				gnomo.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			
			if(gnomo.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 3;
					inicioWave = true;
					System.out.println("entrou Gnomo");
				}
			}
			if(temTorre1 && gnomo.x <= 200 && (elementosTorreSelecionados[0] == 3)
				|| (temTorre1 && (temTorre2 || temTorre3 || temTorre4) && (gnomo.x <= 200)))
			{
				//System.out.println("EntrouAqui");
				estadoTorre3 = 1;
				goldActual += 50;
				if(!iter.hasNext())
				{
					waveActual = 3;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre2 && (gnomo.x <= 412 && gnomo.x >= 410) && (elementosTorreSelecionados[1] == 3)
					|| (temTorre2 && (temTorre3 || temTorre4) && (gnomo.x <= 412 && gnomo.x >= 410)))
					//||  )
			{
				System.out.println("EntrouAqui2");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 3;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre3 && (gnomo.x <= 553 && gnomo.x >= 551) && (elementosTorreSelecionados[2] == 3)
					|| (temTorre3 && (temTorre4) && (gnomo.x <= 551 && gnomo.x >= 551)) )
			{
				System.out.println("Entrou No terceiro IF");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 3;
					inicioWave = true;
				}
				iter.remove();
			}
		}
		
		for(Iterator<Rectangle> iter = fadaArray.iterator(); iter.hasNext();) 
		{
			Rectangle fada = iter.next();
			
			
			if(fada.x >= 120 && fada.x < 130)
			{
				fada.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(fada.y >= 130 && fada.y <= 180)
			{
				fada.x -= 50 * Gdx.graphics.getDeltaTime();
				fada.y -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(fada.x <= 490  && fada.x >= 310)
			{
				if(fada.x >= 340 && fada.x <= 345)
				{
					fada.y -= 50 * Gdx.graphics.getDeltaTime();
				}
				else {
					fada.x -= 50 * Gdx.graphics.getDeltaTime();
					fada.y -= 50 * Gdx.graphics.getDeltaTime();
				}
			}
			else {
				fada.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			
			if(fada.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 4;
					inicioWave = true;
					System.out.println("entrou Fada");
				}
			}
			if((temTorre1 && fada.x <= 200 && (elementosTorreSelecionados[0] == 1))
					|| (temTorre1 && (temTorre2 || temTorre3 || temTorre4) && fada.x <= 200))
			{
				System.out.println("EntrouAqui");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 4;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre2 && (fada.x <= 412 && fada.x >= 410) && (elementosTorreSelecionados[1] == 1)
					|| (temTorre2 && (temTorre3 || temTorre4) && (fada.x <= 412 && fada.x >= 410)) )
			{
				System.out.println("EntrouAqui2");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 4;
					inicioWave = true;
				}
				iter.remove();
			}
			if(temTorre3 && (fada.x <= 553 && fada.x >= 551) && (elementosTorreSelecionados[2] == 1)
					|| (temTorre3 && (temTorre4) && (fada.x <= 551 && fada.x >= 551)) )
			{
				System.out.println("Entrou No terceiro IF");
				estadoTorre1 = 1;
				goldActual += 100;
				if(!iter.hasNext())
				{
					waveActual = 4;
					inicioWave = true;
				}
				iter.remove();
			}
		}
		
		
		for(Iterator<Rectangle> iter = salamandraArray.iterator(); iter.hasNext();) 
		{
			Rectangle salamandra = iter.next();
			
			if(salamandra.x >= 120 && salamandra.x < 130)
			{
				salamandra.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(salamandra.y >= 130 && salamandra.y <= 180)
			{
				salamandra.x -= 50 * Gdx.graphics.getDeltaTime();
				salamandra.y -= 50 * Gdx.graphics.getDeltaTime();
			}
			else if(salamandra.x <= 410 && salamandra.x >= 310)
			{
				if(salamandra.x >= 320 && salamandra.x <= 325)
				{
					salamandra.y -= 50 * Gdx.graphics.getDeltaTime();
				}
				else {
					salamandra.x -= 50 * Gdx.graphics.getDeltaTime();
					salamandra.y -= 50 * Gdx.graphics.getDeltaTime();
				}
			}
			else {
				salamandra.x -= 50 * Gdx.graphics.getDeltaTime();
			}
			
			if(salamandra.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 5;
					inicioWave = true;
					System.out.println("entrou Salamandra");
					this.dispose();
					trilha.pause();
					gsm.set(new TelaJogoMapa(gsm));
					dispose();
				}
			}
			if((temTorre1 && salamandra.x <= 200 && (elementosTorreSelecionados[0] == 4))
					|| (temTorre1 && (temTorre2 || temTorre3 || temTorre4) && salamandra.x <= 200))
			{
				System.out.println("EntrouAqui");
				estadoTorre1 = 1;
				goldActual += 100;
				waveActual = 5;
				inicioWave = true;
				iter.remove();
				if(!iter.hasNext())
				{
					this.dispose();
					gsm.set(new TelaJogoMapa(gsm));
					dispose();
				}
			}
			if(temTorre2 && (salamandra.x <= 412 && salamandra.x >= 410) && (elementosTorreSelecionados[1] == 4)
					|| (temTorre2 && (temTorre3 || temTorre4) && (salamandra.x <= 412 && salamandra.x >= 410)) )
			{
				System.out.println("EntrouAqui2");
				estadoTorre1 = 1;
				goldActual += 100;
				waveActual = 5;
				inicioWave = true;
				iter.remove();
				if(!iter.hasNext())
				{
					this.dispose();
					gsm.set(new TelaJogoMapa(gsm));
					dispose();
				}
			}
			if(temTorre3 && (salamandra.x <= 553 && salamandra.x >= 551) && (elementosTorreSelecionados[2] == 4)
					|| (temTorre3 && (temTorre4) && (salamandra.x <= 551 && salamandra.x >= 551)) )
			{
				System.out.println("Entrou No terceiro IF");
				estadoTorre1 = 1;
				goldActual += 100;
				waveActual = 5;
				inicioWave = true;
				iter.remove();
				if(!iter.hasNext())
				{
					this.dispose();
					gsm.set(new TelaJogoMapa(gsm));
					dispose();
				}
			}
		}
		
		
		for(Rectangle tritao: tritonArray)
		{
			//sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
			if(temTorre1 || temTorre2 || temTorre3 || temTorre4) 
			{
				if(temTorre1)
				{
					if((tritao.x >= 50 && tritao.y <= 270)) 
					{
						//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
						
							if(((((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0 )) {
								sb.draw(tritonImgDano, tritao.x, tritao.y, tritao.width, tritao.height);
								sb.draw(Lasers[elementosTorreSelecionados[0] - 1], tritao.x - 20, tritao.y + 20, 20, 20);
								//System.out.println("Contador Ataques: " + (contAtaques++));
								//System.out.println("Tempo: " + (((System.currentTimeMillis() - startTime) / 1000) % 2));
								
								estadoTorre1 = 2;
							}
							else {
								sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
								estadoTorre1 = 1;
							}
					}
					else {
						sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
						estadoTorre1 = 1;
					}
				}
				if(temTorre2)
				{
					if((tritao.x >= 411 && tritao.x <= 610) && (tritao.y >= 460 && tritao.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(tritonImgDano, tritao.x, tritao.y, tritao.width, tritao.height);
							sb.draw(Lasers[elementosTorreSelecionados[1] - 1], tritao.x - 20, tritao.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre2 = 2;
						}
						else {
							sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
							estadoTorre2 = 1;
						}
					}
					else {
						sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
						estadoTorre2 = 1;
					}
				}
				if(temTorre3)
				{
					if((tritao.x >= 550 && tritao.x <= 700) && (tritao.y >= 460 && tritao.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(tritonImgDano, tritao.x, tritao.y, tritao.width, tritao.height);
							sb.draw(Lasers[elementosTorreSelecionados[2] - 1], tritao.x - 20, tritao.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre3 = 2;
						}
						else {
							sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
							estadoTorre3 = 1;
						}
					}
					else {
						sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
						estadoTorre3 = 1;
					}
				}
				if(temTorre4)
				{
					if((tritao.x >= 900 && tritao.x <= 1100) && (tritao.y >= 460 && tritao.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(tritonImgDano, tritao.x, tritao.y, tritao.width, tritao.height);
							sb.draw(Lasers[elementosTorreSelecionados[3] - 1], tritao.x - 20, tritao.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre4 = 2;
						}
						else {
							sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
							estadoTorre4 = 1;
						}
					}
					else {
						sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
						estadoTorre4 = 1;
					}
				}
			}
			else {
				sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
			}
		}
		for(Rectangle gnomo: gnomoArray)
		{
			if(temTorre1 || temTorre2 || temTorre3 || temTorre4) 
			{
				if(temTorre1)
				{
					if((gnomo.x >= 50 && gnomo.y <= 270)) {
						//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(gnomoImgDano, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							sb.draw(Lasers[elementosTorreSelecionados[0] - 1], gnomo.x - 20, gnomo.y + 20, 20, 20);
							estadoTorre1 = 2;
						}
						else {
							sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							estadoTorre1 = 1;
						}
					}
					else {
						sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
						estadoTorre1 = 1;
					}
				}
				if(temTorre2)
				{
					System.out.println("Entrou Gnomo0");
					if((gnomo.x >= 411 && gnomo.x <= 610) && (gnomo.y >= 460 && gnomo.y <= 520))
					{
						System.out.println("Entrou Gnomo1");
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(gnomoImgDano, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							System.out.println("Entrou Gnomo2");
							sb.draw(Lasers[elementosTorreSelecionados[1] - 1], gnomo.x - 20, gnomo.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre2 = 2;
						}
						else {
							sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							estadoTorre2 = 1;
						}
					}
					else {
						sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
						estadoTorre2 = 1;
					}
				}
				if(temTorre3)
				{
					if((gnomo.x >= 550 && gnomo.x <= 700) && (gnomo.y >= 460 && gnomo.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(gnomoImgDano, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							sb.draw(Lasers[elementosTorreSelecionados[2] - 1], gnomo.x - 20, gnomo.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre3 = 2;
						}
						else {
							sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							estadoTorre3 = 1;
						}
					}
					else {
						sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
						estadoTorre3 = 1;
					}
				}
				if(temTorre4)
				{
					if((gnomo.x >= 900 && gnomo.x <= 1100) && (gnomo.y >= 460 && gnomo.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(gnomoImgDano, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							sb.draw(Lasers[elementosTorreSelecionados[3] - 1], gnomo.x - 20, gnomo.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre4 = 2;
						}
						else {
							sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
							estadoTorre4 = 1;
						}
					}
					else {
						sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
						estadoTorre4 = 1;
					}
				}
			}
			else {
				sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
			}
		}
		for(Rectangle fada: fadaArray)
		{
			if(temTorre1 || temTorre2 || temTorre3 || temTorre4) 
			{
				if(temTorre1)
				{
					if((fada.x >= 50 && fada.y <= 270)) 
					{
						//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(fadaImgDano, fada.x, fada.y, fada.width, fada.height);
							sb.draw(Lasers[elementosTorreSelecionados[0] - 1], fada.x - 20, fada.y + 20, 20, 20);
							estadoTorre1 = 2;
						}
						else {
							sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
							estadoTorre1 = 1;
						}
					}
					else {
							sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
							estadoTorre1 = 1;
					}		
				}
				if(temTorre2) 
				{
					if((fada.x >= 411 && fada.x <= 610) && (fada.y >= 460 && fada.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(fadaImgDano, fada.x, fada.y, fada.width, fada.height);
							sb.draw(Lasers[elementosTorreSelecionados[1] - 1], fada.x - 20, fada.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre2 = 2;
						}
						else {
							sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
							estadoTorre2 = 1;
						}
					}
					else {
						sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
						estadoTorre2 = 1;
					}
				}
				if(temTorre3) 
				{
					if((fada.x >= 550 && fada.x <= 700) && (fada.y >= 460 && fada.y <= 540))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(fadaImgDano, fada.x, fada.y, fada.width, fada.height);
							sb.draw(Lasers[elementosTorreSelecionados[2] - 1], fada.x - 20, fada.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre3 = 2;
						}
						else {
							sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
							estadoTorre3 = 1;
						}
					}
					else {
						sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
						estadoTorre3 = 1;
					}
				}
				if(temTorre4) {
					if((fada.x >= 900 && fada.x <= 1100) && (fada.y >= 460 && fada.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(fadaImgDano, fada.x, fada.y, fada.width, fada.height);
							sb.draw(Lasers[elementosTorreSelecionados[3] - 1], fada.x - 20, fada.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre4 = 2;
						}
						else {
							sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
							estadoTorre4 = 1;
						}
					}
					else {
						sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
						estadoTorre4 = 1;
					}
				}
			}
			else {
				sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
			}
		}
		
		for(Rectangle salamandra: salamandraArray)
		{
			if(temTorre1 || temTorre2 || temTorre3 || temTorre4) 
			{
			if(temTorre1)
			{
				if((salamandra.x >= 50 && salamandra.y <= 270)) 
				{
					//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
					if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
						sb.draw(salamandraImgDano, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						sb.draw(Lasers[elementosTorreSelecionados[0] - 1], salamandra.x - 20, salamandra.y + 20, 20, 20);
						estadoTorre1 = 2;
					}
					else {
						sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						estadoTorre1 = 1;
					}
				}
				else {
					sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
					estadoTorre1 = 1;
				}
			}
			if(temTorre2) 
			{
					if((salamandra.x >= 411 && salamandra.x <= 610) && (salamandra.y >= 460 && salamandra.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(salamandraImgDano, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							sb.draw(Lasers[elementosTorreSelecionados[1] - 1], salamandra.x - 20, salamandra.y + 20, 20, 20);
							//System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre2 = 2;
						}
						else {
							sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							estadoTorre2 = 1;
						}
					}
					else {
						sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						estadoTorre2 = 1;
					}
				}
			if(temTorre3) 
			{
					if((salamandra.x >= 550 && salamandra.x <= 700) && (salamandra.y >= 460 && salamandra.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(salamandraImgDano, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							sb.draw(Lasers[elementosTorreSelecionados[2] - 1], salamandra.x - 20, salamandra.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre3 = 2;
						}
						else {
							sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							estadoTorre3 = 1;
						}
					}
					else {
						sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						estadoTorre3 = 1;
					}
				}
				if(temTorre4) {
					if((salamandra.x >= 900 && salamandra.x <= 1100) && (salamandra.y >= 460 && salamandra.y <= 520))
					{
						if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
							sb.draw(salamandraImgDano, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							sb.draw(Lasers[elementosTorreSelecionados[3] - 1], salamandra.x - 20, salamandra.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
							estadoTorre4 = 2;
						}
						else {
							sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
							estadoTorre4 = 1;
						}
					}
					else {
						sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						estadoTorre4 = 1;
					}
				}
			}
			else {
				sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
			}
			
		}
		
		
		//System.out.println("Tempo decorrido :" + instanteUltimoAtaque);
		//System.out.println("Tempo decorrido: " + ((System.currentTimeMillis() - startTime) / 1000));
		
		
		if(placa3)
		{
			sb.draw(placaVenda, 560, 330, 100, 100);
		}
		else {
			if(temTorre3)
			{
				if(estadoTorre3 == 1)
				{
					if(elementosTorreSelecionados[2] == 1)
						sb.draw(torres1Imgs[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 2)
						sb.draw(torres2Imgs[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 3)
						sb.draw(torres3Imgs[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 4)
						sb.draw(torres4Imgs[(lvlTorre[2] - 1)], 430, 270, 350, 350);
				}
				else {
					if(elementosTorreSelecionados[2] == 1)
						sb.draw(torres1ImgsAtk[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 2)
						sb.draw(torres2ImgsAtk[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 3)
						sb.draw(torres3ImgsAtk[(lvlTorre[2] - 1)], 400, 270, torreWidth, torreHeight);
					else if(elementosTorreSelecionados[2] == 4)
						sb.draw(torres4ImgsAtk[(lvlTorre[2] - 1)], 430, 270, 350, 350);
				}
			}
			else
				sb.draw(circuloInvoc, 520, 250, 180, 180);
		}
		if(placa4)
		{
			sb.draw(placaVenda, 1040, 370, 100, 100);
		}
		else {
			if(temTorre4)
			{
				if(estadoTorre4 == 1)
				{
					if(elementosTorreSelecionados[3] == 1)
						sb.draw(torres1Imgs[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 2)
						sb.draw(torres2Imgs[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 3)
						sb.draw(torres3Imgs[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 4)
						sb.draw(torres4Imgs[(lvlTorre[3] - 1)], 930, 310, 320, 320);
				}
				else {
					if(elementosTorreSelecionados[3] == 1)
						sb.draw(torres1ImgsAtk[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 2)
						sb.draw(torres2ImgsAtk[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 3)
						sb.draw(torres3ImgsAtk[(lvlTorre[3] - 1)], 930, 310, 320, 320);
					else if(elementosTorreSelecionados[3] == 4)
						sb.draw(torres4ImgsAtk[(lvlTorre[3] - 1)], 930, 310, 320, 320);
				}
			}
			else
			sb.draw(circuloInvoc, 1000, 290, 180, 180);
		}
		
		if(!duranteWave) {
			if(indexInfosTutorial == 0)
				indexInfosTutorial++;
			else if(Gdx.input.justTouched())
			{
				if(indexInfosTutorial == 16)
				{
					inicioWave = true;
				}
				else
					indexInfosTutorial++;
			}
			else
				//sb.draw(placasTutorial[indexInfosTutorial - 1], 350 , 100);
				sb.draw(placasTutorial[indexInfosTutorial - 1], 0 , 0);
		}
		
		if(lifeActual <= 0)
		{
			System.out.println("Entrou Life zerou");
			this.dispose();
			//gsm.set(new TelaJogoMapa(gsm));
			trilha.pause();
			gsm.set(new MenuState(gsm));
			dispose();
		}
		
		//sb.draw(gnomo.getGnomo(), gnomo.getPos().x, gnomo.getPos().y, gnomo.gnomoWidth, gnomo.gnomoHeight);
		
		GlyphLayout lifeLayout = new GlyphLayout(fontLife, "" + lifeActual);
		GlyphLayout goldLayout = new GlyphLayout(fontGold, "" + goldActual);
		GlyphLayout waveLayout = new GlyphLayout(fontWave, "" + waveActual + "/5");
		//font.draw(sb, goldLayout, 100, 100);
		fontLife.draw(sb, lifeLayout, 80, 687);
		fontGold.draw(sb, goldLayout, 190, 687);
		fontWave.draw(sb, waveLayout, 300, 687);
		
		
		//triton.drawTritonAnimation(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		fundoTela.dispose();
		placaVenda.dispose();
		circuloInvoc.dispose();
		cancelado.dispose();
		tritonImg.dispose();
		tritonImgDano.dispose();
		gnomoImg.dispose();
		ImagesEnemys.disposeImages();
		
	}


	public boolean isPlaca1() {
		return placa1;
	}


	public void setPlaca1(boolean placa1) {
		this.placa1 = placa1;
	}


	public boolean isPlaca2() {
		return placa2;
	}


	public void setPlaca2(boolean placa2) {
		this.placa2 = placa2;
	}


	public boolean isPlaca3() {
		return placa3;
	}


	public void setPlaca3(boolean placa3) {
		this.placa3 = placa3;
	}


	public boolean isPlaca4() {
		return placa4;
	}


	public void setPlaca4(boolean placa4) {
		this.placa4 = placa4;
	}

	
	
}



