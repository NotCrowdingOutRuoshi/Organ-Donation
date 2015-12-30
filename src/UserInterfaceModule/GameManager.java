package UserInterfaceModule;

import javax.swing.JPanel;

import CentralizedDataCenter.Entities.Player;
import DynamicObjectModule.DynamicObjectModule;
import Net.TCP.Client.TCPCM;

public class GameManager {

	private String status;
	private Director director;
	private JPanel statusPanel;
	private Player player;
	private DynamicObjectModule dom;
	private TCPCM tcp;
	
	/**
     * 使用靜態變數記錄Singleton, 並建立實例
     */
    private static GameManager singleton;

  
    /**
     * 讓外部只能透過這個method取得Singleton實例
     */
    public static GameManager getInstance() {
    	if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }
	
    private GameManager(){
		//tcp = new TCP();
	}
    
    public void setDom(DynamicObjectModule dom){
    	this.dom = dom;
    }
    
    public void setDirector(Director director){
    	this.director = director;
    }
	
	public void setGameStatus(String status){
		this.status = status;
		switch(this.status){
		case "menu":
			MenuScene ms = new MenuScene();
			statusPanel = ms;
			break;
		case "wait":
			player = new Player();
			WaitScene ws = new WaitScene(dom);
			statusPanel = ws;
			break;
		case "game":
			GameScene gs = new GameScene(dom,player);
			//KeyAdapterDemo k = new KeyAdapterDemo(gs);
			statusPanel = gs;
			break;
		case "gameover":
			GameOverScene gos = new GameOverScene();
			statusPanel = gos;
			break;
		
		}
		
		this.director.setpanel(statusPanel);
		statusPanel.requestFocusInWindow();
	}
	
	public void setClientId(int clientid){
		player.setID(clientid);
	}
	
	public void setCountdown(){
		
	}
	
	public void addPlayer(int clientNumber){
		dom.addVirtualCharacter(clientNumber);
	}
	
	public void sendtoTcp(String s){
		System.out.println(s);
		// Temporary comment out
		//tcp.send();
	}
	
	
}
