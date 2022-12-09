import java.util.Scanner;

public class testTreeObject {

	public static void main(String[] args) {
		
		final int DISPLAY_LIST = 1;
		final int SORT_LIST = 2;
		final int SEARCH_NAME = 3;
		final int EXIT = 0;
				
		// Create treeObject instance to access methods
		treeObject testTree = new treeObject();
				
		// File to open
		//String treefile = "c:\\treeobj\\scores.txt";
		String treefile = "./src/scores.txt";
		
		Scanner input = new Scanner(System.in);
		int choice;
		treeNode search = new treeNode();
		try {
			if(testTree.fileInit(treefile))
				System.out.println(treefile + " was loaded successfully\n");
			
			do 
			{
				System.out.println("1 - Display list");
				System.out.println("2 - Sort list");
				System.out.println("3 - Search\n");
				System.out.println("Enter an option (or enter 0 to exit)");
				choice = input.nextInt();
				
				switch(choice)
				{
					case DISPLAY_LIST:
						if(!testTree.isTreeEmpty()) {
							testTree.displayPlayers();
						}
						else
							System.out.println("Unable to display list\n");
						break;
					case SORT_LIST:
						if(!testTree.isTreeEmpty()) {
							//testTree.sort_Ver1();
							testTree.sort();
	//						testTree.sortBubble();
						}
						else
							System.out.println("Unable to sort list\n");
						break;
					case SEARCH_NAME:
						System.out.println("Enter name:");
						search = testTree.treeSearch(input.next());
						if (search.getPlayer() != "")
						{
							System.out.println("Player Name\tGoals\tAssists\tPoints");
							System.out.println(search.toString());
						}
						else
							System.out.println("Not found\n");
						break;
					case EXIT:
						break;
					default:
						System.out.println("Invalid option\n");
				}
			}
			while(choice != EXIT);
			input.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
