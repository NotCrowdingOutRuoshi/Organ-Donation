package Common.Interfaces;

public interface ICentralizedDataCenter {

	public void addPlayer(int id);
	public void updateDir(int id ,int dir);
	public void setState(int id,String state);
	public void setGameState(String gameState);
	public String getGameState();
	public String getUpdateInfo();
}
