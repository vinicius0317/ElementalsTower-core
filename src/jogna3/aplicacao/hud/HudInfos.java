package jogna3.aplicacao.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


import jogna3.aplicacao.helpers.GameInfos;
import jogna3.aplicacao.helpers.GameManage;
import jogna3.aplicacao.telas.GameStateManager;



public class HudInfos {
	
	private GameStateManager game;
	
	public Label goldPlayerText;
	private Stage stage;
	private Viewport gameVision;
	BitmapFont font;

	public HudInfos(GameStateManager gsm)
	{
		this.game = gsm;
		gameVision = new FitViewport(GameInfos.WIDTH,GameInfos.HEIGHT,new OrthographicCamera());
        //stage = new Stage(gameVision,this.game.getBatch());
		createAndPosImages();
        createTexts();
		
	}
	
	public void createAndPosImages()
    {
		
		
    
    }
	
	public void createTexts()
    {

		//font = new BitmapFont(Gdx.files.internal("fonts/berlinsans.ttf"));
		font = new BitmapFont();
        goldPlayerText = new Label("R$ "+GameManage.playerGold,new Label.LabelStyle(font, Color.BLACK));
        goldPlayerText.setPosition(300, 300, Align.center);
        
		
    }

}