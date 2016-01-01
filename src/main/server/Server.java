package main.server;

import java.io.IOException;

import CentralizedDataCenter.CDC;
import Common.Constants;
import Common.Interfaces.ICentralizedDataCenter;
import Common.Interfaces.ITCPServer;
import Common.Interfaces.IUDPBroadcast;
import Net.TCP.Server.TCPServer;
import Net.UDP.Server.UDPBroadCast;

public class Server {
	
	private ITCPServer tcpServer;
	
	private ICentralizedDataCenter cdc;
	
	private IUDPBroadcast udpBroadcast;
	
	private String currentState = Constants.GAME_STATE_WAIT;
	
	public Server() throws IOException {
		// TODO Auto-generated constructor stub
		cdc = new CDC();
		tcpServer = new TCPServer(cdc);
		udpBroadcast = new UDPBroadCast(tcpServer, cdc);
		
		cdc.startGameLogicSchedule();
		tcpServer.startServer();
		udpBroadcast.startUDPBroadCast();
		
		while(!cdc.getGameState().equals(Constants.GAME_STATE_OVER)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!currentState.equals(cdc.getGameState())){
				tcpServer.BroadcastAllClient(Constants.GAME_STATE_INIT);
				currentState = Constants.GAME_STATE_INIT;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
