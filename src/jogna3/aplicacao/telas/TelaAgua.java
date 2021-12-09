package jogna3.aplicacao.telas;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import jogna3.aplicacao.hud.HudInfos;

public class TelaAgua extends State{
	
	

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
	
	private String elementoAtual = "Terra";
	private boolean txtRegraOpened = false;
	
	private Music trilha;
	
	private boolean duranteWave = false;
	private boolean placa1 = true;
	private boolean placa2 = true;
	private boolean placa3 = true;
	private boolean placa4 = true;
	private boolean inicioWave = true;
	private boolean isPaused = true;
	private int goldActual = 500;
	private int lifeActual = 20;
	private int waveActual = 1;
	private int torreWidth = 420;
	private int torreHeight = 420;
	private int elementoTorreSelecionado = 1;
	

	private int enemyLife = 12;
	private int enemyActualLife = enemyLife;
	

	Vector3 clickPos = new Vector3();
	
	
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
	
	private Texture[] placasTelaAgua;
	
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
	
	
	
	public TelaAgua(GameStateManager gsm) {
		super(gsm);
		
		fundoTela = new Texture("FaseAgua.png");
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
		
		
		placasTelaAgua = new Texture[8];
		for(int i = 0; i < 8; i++)
		{
			placasTelaAgua[i] = new Texture("InfosTutorial/Infos" + (i +1) + ".png");
		}
		

		if(placa1 && placa2 && placa3 && placa4) {
			placa1 = true;
		}
		
		waveActual = 1;
		
		hud = new HudInfos(gsm);
		hud.createTexts();
		

		fontLife = new BitmapFont(Gdx.files.internal("fonts/squealerVida.fnt"));
		fontGold = new BitmapFont(Gdx.files.internal("fonts/squealerGold.fnt"));
		fontWave = new BitmapFont(Gdx.files.internal("fonts/squealerWaves.fnt"));
		
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
		
		trilha = Gdx.audio.newMusic(Gdx.files.internal("TrilhaFaseAgua.mp3"));
		
		trilha.setLooping(true);
		trilha.setVolume(1.0f);
		trilha.play();
		
	}
	
	
	private void gerarTritons(){
		Rectangle TritonsRectangle = new Rectangle();
		
		TritonsRectangle.x = MathUtils.random(1220, 1400);
		TritonsRectangle.y = MathUtils.random(100, 200);
		TritonsRectangle.width = 90;
		TritonsRectangle.height = 75;
		tritonArray.add(TritonsRectangle);
	}

	private void gerarGnomo(){
		Rectangle gnomoRectangle = new Rectangle();
		
		gnomoRectangle.x = MathUtils.random(1220, 1400);
		gnomoRectangle.y = MathUtils.random(70, 180);
		gnomoRectangle.width = 60;
		gnomoRectangle.height = 100;
		gnomoArray.add(gnomoRectangle);
	}
	
	private void gerarFada(){
		Rectangle fadaRectangle = new Rectangle();
		
		fadaRectangle.x = MathUtils.random(1220, 1400);
		fadaRectangle.y = MathUtils.random(100, 220);
		fadaRectangle.width = 60;
		fadaRectangle.height = 80;
		fadaArray.add(fadaRectangle);
	}
	
	private void gerarSalamandra(){
		Rectangle salamandraRectangle = new Rectangle();
		
		salamandraRectangle.x = MathUtils.random(1220, 1400);
		salamandraRectangle.y = MathUtils.random(100, 200);
		salamandraRectangle.width = 120;
		salamandraRectangle.height = 70;
		salamandraArray.add(salamandraRectangle);
	}
	
	
	@Override
	protected void handleInput() {
		
		
		if(Gdx.input.justTouched())
		{
			clickPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			
			
			/*sb.draw(placaVenda, 70, 360, 50, 50); //1
			sb.draw(placaVenda, 120, 310, 50, 50); //2
			sb.draw(placaVenda, 70, 250, 50, 50); //3
			sb.draw(placaVenda, 10, 310, 50, 50); //4*/
			if((clickPos.x >= 10 && clickPos.x <= 170) && (clickPos.y >= 280 && clickPos.y <= 440))
			{
				System.out.println("Entrou 1");
				if(goldActual >= 400 && !temTorre2 && (lvlTorre[0] != 2)) {
					if(placa1)
						setPlaca1(false);
					else
						setPlaca1(true);
					goldActual -= 400;
					System.out.println("Entrou 2");
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
				if((clickPos.x >= 70 && clickPos.x <= 120) && (clickPos.y >= 360 && clickPos.y <= 410) && (!temTorre1) && (!placa1))
				{
					elementoTorreSelecionado = 1;
					temTorre1 = true;
				}
				else if((clickPos.x >= 120 && clickPos.x <= 170) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre1) && (!placa1))
				{
					elementoTorreSelecionado = 2;
					temTorre1 = true;
				}
				else if((clickPos.x >= 70 && clickPos.x <= 120) && (clickPos.y >= 250 && clickPos.y <= 300) && (!temTorre1) && (!placa1))
				{
					elementoTorreSelecionado = 3;
					temTorre1 = true;
				}
				else if((clickPos.x >= 10 && clickPos.x <= 60) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre1) && (!placa1))
				{
					elementoTorreSelecionado = 4;
					temTorre1 = true;
				}
				else if((temTorre1) && (!placa1))
				{
					lvlTorre[0] = 2;
				}
				else {
					
				}
			}
			/*if((clickPos.x >= 320 && clickPos.x <= 500) && (clickPos.y >= 30 && clickPos.y <= 220))
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
					elementoTorreSelecionado = 1;
					temTorre2 = true;
				}
				else if((clickPos.x >= 450 && clickPos.x <= 500) && (clickPos.y >= 90 && clickPos.y <= 140) && (!temTorre2) && (!placa2))
				{
					elementoTorreSelecionado = 2;
					temTorre2 = true;
				}
				else if((clickPos.x >= 390 && clickPos.x <= 440) && (clickPos.y >= 160 && clickPos.y <= 210) && (!temTorre2) && (!placa2))
				{
					elementoTorreSelecionado = 3;
					temTorre2 = true;
				}
				else if((clickPos.x >= 330 && clickPos.x <= 380) && (clickPos.y >= 90 && clickPos.y <= 140) && (!temTorre2) && (!placa2))
				{
					elementoTorreSelecionado = 4;
					temTorre2 = true;
				}
				else if((temTorre2) && (!placa2))
				{
					lvlTorre[1] = 2;
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
					elementoTorreSelecionado = 1;
					temTorre3 = true;
				}
				else if((clickPos.x >= 650 && clickPos.x <= 700) && (clickPos.y >= 330 && clickPos.y <= 380) && (!temTorre3) && (!placa3))
				{
					elementoTorreSelecionado = 2;
					temTorre3 = true;
				}
				else if((clickPos.x >= 590 && clickPos.x <= 640) && (clickPos.y >= 390 && clickPos.y <= 450) && (!temTorre3) && (!placa3))
				{
					elementoTorreSelecionado = 3;
					temTorre3 = true;
				}
				else if((clickPos.x >= 520 && clickPos.x <= 580) && (clickPos.y >= 330 && clickPos.y <= 380) && (!temTorre3) && (!placa3))
				{
					elementoTorreSelecionado = 4;
					temTorre3 = true;
				}
				else if((temTorre3) && (!placa3))
				{
					lvlTorre[2] = 2;
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
					elementoTorreSelecionado = 1;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1130 && clickPos.x <= 1180) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre4) && (!placa4))
				{
					elementoTorreSelecionado = 2;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1070 && clickPos.x <= 1120) && (clickPos.y >= 360 && clickPos.y <= 410) && (!temTorre4) && (!placa4))
				{
					elementoTorreSelecionado = 3;
					temTorre4 = true;
				}
				else if((clickPos.x >= 1000 && clickPos.x <= 1050) && (clickPos.y >= 310 && clickPos.y <= 360) && (!temTorre4) && (!placa4))
				{
					elementoTorreSelecionado = 4;
					temTorre4 = true;
				}
				else if((temTorre4) && (!placa4))
				{
					lvlTorre[3] = 2;
				}
				else {
					
				}
			}*/
		}
		
	}
	
	
	@Override
	public void update(float dt)
	{
		handleInput();
	}
	
	
	
	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		sb.draw(fundoTela, 0, 0, 1200, 700);
		
		
		if(inicioWave) {
			for(int i = 0; i < 3; i++) {
				if(waveActual == 1) {
					gerarTritons();
				}
				else if(waveActual == 2)
				{
					gerarGnomo();
				}
				else if(waveActual == 3)
				{
					gerarFada();
				}
				else if(waveActual == 4)
				{
					gerarSalamandra();
				}
			}
			inicioWave = false;
			duranteWave = true;
		}
		
		
		for(Iterator<Rectangle> iter = tritonArray.iterator(); iter.hasNext();) 
		{
			Rectangle triton = iter.next();
			
			/*int i=1;
			i++;
			System.out.println(i);
			int text = tritonArray.size;*/
			
			
			if((triton.x >= 600 && triton.x <= 950) && (triton.y >= 100 && triton.y <= 490))
			{
				triton.x -= 40 * Gdx.graphics.getDeltaTime();
				triton.y += 40 * Gdx.graphics.getDeltaTime();
			}
			else if((triton.x > 120 && triton.x <= 410) && (triton.x >= 130 && triton.y <= 510))
			{
				triton.x -= 40 * Gdx.graphics.getDeltaTime();
				triton.y -= 40 * Gdx.graphics.getDeltaTime();
			}
			else
				triton.x -= 40 * Gdx.graphics.getDeltaTime();
			
			if(triton.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 2;
					inicioWave = true;
					//System.out.println("entrou Triton");
				}
			}
		}
		
		for(Iterator<Rectangle> iter = gnomoArray.iterator(); iter.hasNext();) 
		{
			Rectangle gnomo = iter.next();
			
			/*indexCurva++; 
			System.out.println(indexCurva);*/
			
			if((gnomo.x >= 600 && gnomo.x <= 950) && (gnomo.y >= 100 && gnomo.y <= 490))
			{
				gnomo.x -= 40 * Gdx.graphics.getDeltaTime();
				gnomo.y += 40 * Gdx.graphics.getDeltaTime();
			}
			else if((gnomo.x > 120 && gnomo.x <= 410) && (gnomo.x >= 130 && gnomo.y <= 510))
			{
				gnomo.x -= 40 * Gdx.graphics.getDeltaTime();
				gnomo.y -= 40 * Gdx.graphics.getDeltaTime();
			}
			else
				gnomo.x -= 40 * Gdx.graphics.getDeltaTime();
			
			if(gnomo.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 3;
					inicioWave = true;
					//System.out.println("entrou Gnomo");
				}
			}
		}
		
		for(Iterator<Rectangle> iter = fadaArray.iterator(); iter.hasNext();) 
		{
			Rectangle fada = iter.next();
			
			if((fada.x >= 600 && fada.x <= 950) && (fada.y >= 100 && fada.y <= 490))
			{
				fada.x -= 40 * Gdx.graphics.getDeltaTime();
				fada.y += 40 * Gdx.graphics.getDeltaTime();
			}
			else if((fada.x > 120 && fada.x <= 410) && (fada.x >= 130 && fada.y <= 510))
			{
				fada.x -= 40 * Gdx.graphics.getDeltaTime();
				fada.y -= 40 * Gdx.graphics.getDeltaTime();
			}
			else
				fada.x -= 40 * Gdx.graphics.getDeltaTime();
			
			
			if(fada.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 4;
					inicioWave = true;
					//System.out.println("entrou Fada");
				}
			}
		}
		
		
		for(Iterator<Rectangle> iter = salamandraArray.iterator(); iter.hasNext();) 
		{
			Rectangle salamandra = iter.next();
			
			if((salamandra.x >= 600 && salamandra.x <= 950) && (salamandra.y >= 100 && salamandra.y <= 490))
			{
				salamandra.x -= 40 * Gdx.graphics.getDeltaTime();
				salamandra.y += 40 * Gdx.graphics.getDeltaTime();
			}
			else if((salamandra.x > 120 && salamandra.x <= 410) && (salamandra.x >= 130 && salamandra.y <= 510))
			{
				salamandra.x -= 40 * Gdx.graphics.getDeltaTime();
				salamandra.y -= 40 * Gdx.graphics.getDeltaTime();
			}
			else
				salamandra.x -= 40 * Gdx.graphics.getDeltaTime();
			
			if(salamandra.x + 80 < 0) {
				iter.remove();
				lifeActual -= 1;
				if(!iter.hasNext() && inicioWave == false) {
					waveActual = 5;
					inicioWave = true;
					System.out.println("entrou Salamandra");
					this.dispose();
					gsm.set(new TelaJogoMapa(gsm));
					dispose();
				}
			}
		}
		
		
		
		
		for(Rectangle tritao: tritonArray)
		{
			//sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
			//contAtaques++;
			sb.draw(tritonImg, tritao.x, tritao.y, tritao.width, tritao.height);
			/*if(temTorre1)
			{
				if((tritao.x >= 0 && tritao.y <= 410)) 
				{
					//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
					
						if( ((((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0 && (contAtaques % 2 == 0))) {
							sb.draw(tritonImgDano, tritao.x, tritao.y, tritao.width, tritao.height);
							sb.draw(Lasers[elementoTorreSelecionado - 1], tritao.x - 20, tritao.y + 20, 20, 20);
							System.out.println("Contador Ataques: " + (contAtaques++));
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
			}*/
		}
		
		for(Rectangle gnomo: gnomoArray)
		{
			sb.draw(gnomoImg, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
			/*if(temTorre1)
			{
				if((gnomo.x >= 0 && gnomo.y <= 410)) {
					//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
					if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
						sb.draw(gnomoImgDano, gnomo.x, gnomo.y, gnomo.width, gnomo.height);
						sb.draw(Lasers[elementoTorreSelecionado - 1], gnomo.x - 20, gnomo.y + 20, 20, 20);
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
			}*/
		}
		
		for(Rectangle fada: fadaArray)
		{
			sb.draw(fadaImg, fada.x, fada.y, fada.width, fada.height);
			/*if(temTorre1)
			{
				if((fada.x >= 0 && fada.y <= 410)) 
				{
					//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
					if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
						sb.draw(fadaImgDano, fada.x, fada.y, fada.width, fada.height);
						sb.draw(Lasers[elementoTorreSelecionado - 1], fada.x - 20, fada.y + 20, 20, 20);
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
			}*/
		}
		
		for(Rectangle salamandra: salamandraArray)
		{
			sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
			/*if(temTorre1)
			{
				if((salamandra.x >= 0 && salamandra.y <= 410)) 
				{
					//if(TimeUtils.nanoTime() - instanteUltimoAtaque > 1000000000)
					if( (((System.currentTimeMillis() - startTime) / 1000) % 2)  == 0) {
						sb.draw(salamandraImgDano, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						sb.draw(Lasers[elementoTorreSelecionado - 1], salamandra.x - 20, salamandra.y + 20, 20, 20);
						estadoTorre1 = 2;
					}
					else {
						sb.draw(salamandraImg, salamandra.x, salamandra.y, salamandra.width, salamandra.height);
						estadoTorre1 = 1;
					}
				}
				else {
					
				}
			}*/
		}
		
		
		if(placa1)
		{
			sb.draw(placaVenda, 30, 320, 100, 100);
		}
		else {
			if(temTorre1)
			{
				if(estadoTorre1 == 1)
				{
					if(elementoTorreSelecionado == 1)
						sb.draw(torres1Imgs[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 2)
						sb.draw(torres2Imgs[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 3)
						sb.draw(torres3Imgs[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 4)
						sb.draw(torres4Imgs[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
				}
				else {
					if(elementoTorreSelecionado == 1)
						sb.draw(torres1ImgsAtk[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 2)
						sb.draw(torres2ImgsAtk[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 3)
						sb.draw(torres3ImgsAtk[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
					else if(elementoTorreSelecionado == 4)
						sb.draw(torres4ImgsAtk[(lvlTorre[0] - 1)], -120, 260, torreWidth, torreHeight);
				}
				
			}
			else
				sb.draw(circuloInvoc, 10, 250, 160, 160);
				/*sb.draw(placaVenda, 70, 360, 50, 50); //1
				sb.draw(placaVenda, 120, 310, 50, 50); //2
				sb.draw(placaVenda, 70, 250, 50, 50); //3
				sb.draw(placaVenda, 10, 310, 50, 50); //4*/
		}
		
		
		if(lifeActual <= 0)
		{
			this.dispose();
			gsm.set(new TelaJogoMapa(gsm));
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
