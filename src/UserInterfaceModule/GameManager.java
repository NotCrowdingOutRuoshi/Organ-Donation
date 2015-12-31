package UserInterfaceModule;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JPanel;


import CentralizedDataCenter.Entities.Player;
import Common.Constants;
import DynamicObjectModule.DynamicObjectModule;
import Net.TCP.Client.TCPClient;


public class GameManager {
	
	private String status;
	private Director director;
	private JPanel statusPanel;
	private Player player;
	private DynamicObjectModule dom;
	private TCPClient tcp;
	private GameScene gs = null;
	private WaitScene ws = null;
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
		tcp = new TCPClient();
    	dom = new DynamicObjectModule();
	}
    
//    public void setDom(DynamicObjectModule dom){
//    	this.dom = dom;
//    }
    
    public void setDirector(Director director){
    	this.director = director;
    }
	
	public void setGameStatus(String status){
		this.status = status;
		switch(this.status){
		case Constants.GAME_STATE_MENU:
			MenuScene ms = new MenuScene();
			statusPanel = ms;
			break;
		case Constants.GAME_STATE_WAIT:
			
//			try {
//				tcp.connectServer(InetAddress.getByName("140.115.53.45"));
//			} catch (UnknownHostException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			player = new Player();
			ws = new WaitScene(dom);
			Thread w = new Thread(ws);
	        w.start();
			statusPanel = ws;
			break;
		case Constants.GAME_STATE_INIT:

			try {
				gs = new GameScene(dom,player);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statusPanel = gs;
			break;
		case Constants.GAME_STATE_START:
			gs.gameStart();
			break;
		case Constants.GAME_STATE_OVER:
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
	
	public void setCountdown(int i){
		gs.setCountDown(i);
	}
	
	
	public void sendtoTcp(String s){
		System.out.println(s);
		try {
			tcp.inputMoves(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
