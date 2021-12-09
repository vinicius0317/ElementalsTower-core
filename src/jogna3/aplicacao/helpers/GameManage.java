package jogna3.aplicacao.helpers;

public class GameManage {
	
	public static int FPS = 60;

	private boolean isPaused;
	public static int playerGold;
	public static byte enemyNumber;
	public static byte enemyActualNumber;
	public static byte tritonNumber;
	public static byte tritonActualNumber;
	//public static byte waveNumber;
	
	
	public GameManage()
	{
		isPaused = true;
		enemyNumber = 3;
		enemyActualNumber = enemyNumber;
		//waveNumber = 1;
		playerGold = 0;
		createPositions();
	}
	
	void createPositions()
	{
		int x= 0;
		int y = 0;
		int n = 0;
		
	}
	
	// metodo que surge mais inimigos na tela
	public static void changeWaves()
	{
		enemyNumber+=2;
		if(enemyNumber>8)
			enemyNumber = 8;
		enemyActualNumber = enemyNumber;
	}
	
}
