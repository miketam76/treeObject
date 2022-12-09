public class treeNode {

	private String playerName;
	private int goals;
	private int assists;
	private int points;
		
	// Default Constructor
	public treeNode() { this("",0,0,0); }
	
	// Overload constructor using all arguments listed variable listing
	public treeNode(String player, int goals, int assists, int points)
	{
		this.playerName = player;
		this.goals = goals;
		this.assists = assists;
		this.points = points;
	}

	public String getPlayer() { return playerName; } 
	public void setPlayer(String name) { this.playerName = name; }
	
	public int getGoals() { return goals; }
	public void setGoals(int g) { this.goals = g; }
	
	public int getAssists() { return assists; }
	public void setAssists(int a) { this.assists = a; }
	
	public int getPoints() { return points; }
	public void setPoints(int p) { this.points = p; }

	public String toString ()
	{
		// This fixes issues with tab spacing for any player name greater than 7 characters
		if (this.playerName.length() > 7)
			return (this.playerName + "\t" + this.goals + "\t"  + this.assists + "\t" + this.points + "\n");
		else
			return (this.playerName + "\t\t" + this.goals + "\t" + this.assists + "\t" + this.points + "\n");
	}
}
