import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CSCMatch {

	private static MembersList members;
	private static Scanner scnr;
	private static boolean edited;
	private static boolean saved = true;

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {

		members = new MembersList();
		scnr = new Scanner(System.in);
		
		boolean exit = false;

		System.out.println("Welcome to CSC Match!");
		Thread.sleep(1500); //pause before entering menu

		while(!exit) {

			String response;

			//introduce user
			System.out.println("\nWhat would you like to do?");
			printInstructions();
			response = scnr.nextLine().toLowerCase();
			
			switch(response.charAt(0)) {
			case 'a':
				
				addMember();
				
				edited = true;
				saved = false;
				break;

			case 'b': //Add Interest to Member
				
				addInterestSelector();
				
				edited = true;
				saved = false;
				break;

			case 'c': //List a Member
				
				listMember();
				break;

			case 'd': //List All Members
				
				listAllMembers();
				break;
				
			case 'e': //Remove Member		
				
				removeMember();
				break;

			case 'f': //Load Members

				load();
				break;

			case 'g': //Save Members

				save();
				break;

			case 'h': //Quit
				
				exit = quit();
				break;

			default:
				System.out.println("\nPlease, make a selection");
				break;

			}

		}
		
		System.out.println("\nHave a great day!");

	}

	public static void addMember() { //guides user through steps to add a new member
		
		do {

			String name;
			int year = 0;

			System.out.println("\n-- Adding Member --\n");
			System.out.print("Please enter the new member's name: ");
			name = adjustInput(scnr.nextLine());
			name = checkName(name); //send name to method which ensures it is unique

			System.out.print("Thanks, what year is " + name + " in? (1-5): ");
			year = getInt(1, 5, "year");

			Member tempMember = new Member(name, year);
			members.add(tempMember);
			System.out.println("Member successfully created.");
			
			System.out.print("\nWould you like to add interests to " + name + "? Y/N: ");
			String response = scnr.nextLine().toLowerCase();
			
			if(response.charAt(0) == 'y')
				addInterest(tempMember);

		} while(inquireLoop("add another member"));
		
	}

	public static void addInterest(Member m) { //adds interest to member passed through
		
		String interestName = "";
		int level = 0;
		boolean again;
		
		do {

			listAllInterests(m);

			System.out.println("--\nAdding interest to " + m.getName());
			System.out.print("Enter the name of the interest you'd like to add" + (m.getNumInterests() != 0 ? " or modify" : "") + ": ");
			interestName = adjustInput(scnr.nextLine());

			System.out.print("Thanks, what is the interest level of " + interestName + " for " + m.getName() + "?: ");
			level = getInt(0, 10, "interest level");

			Interest i = new Interest(interestName); //create interest with name n
			members.interests.add(i);

			m.addInterest(i, level);
			System.out.println(interestName + " successfully added to " + m.getName() + "'s interests.");

			again = inquireLoop("add or modify another interest to " + m.getName());

		} while(again);
		
	}
	
	public static void addInterestSelector() { //used to ask user which member they'd like to add an interest to
		
		do {

			int memberIndex = selectMember("add an interest to", true);

			if(memberIndex == -1) //exit to menu if there are no members
				return;

			Member tempMember = members.get(memberIndex); //get member based on user's selection from member's list

			addInterest(tempMember);

		} while(inquireLoop("add or modify an interest of a different member"));
		
	}
	
	public static void listMember() {

		do {

			int memberIndex = selectMember("display", true);

			if(memberIndex == -1) //exit to menu if there are no members
				return;

			Member tempMember = members.get(memberIndex);

			if(edited)
				compare(members);

			System.out.println("\n-- Listing Member --");
			System.out.println(tempMember.toString());
			waitUserInput();

			edited = false;

		} while(inquireLoop("list another member"));
		
	}
	
	
	public static void listAllMembers() {
		
		System.out.println(members.toString());
		waitUserInput();
		
	}
	

	public static void removeMember() {

		do {

			boolean remove = false;
			String choice;

			int memberIndex = selectMember("remove", false);

			if(memberIndex == -1) //exit to menu if there are no members
				return;

			Member tempMember = members.get(memberIndex);
			String name = tempMember.getName();
			System.out.print("Are you sure you'd like to remove member: " + tempMember.getName() + "? Y/N: ");
			choice = scnr.nextLine().toLowerCase();
			remove = choice.startsWith("y"); 

			if(remove) {
				members.removeMember(tempMember);
				System.out.println(name + " removed successfully");
				edited = true;
				saved = false;
			}
			else
				System.out.println("Member not removed");

		} while(inquireLoop("remove another member"));
		
	}
	
	
	public static void load() throws ClassNotFoundException, IOException {
		
		boolean fileAvailable = false;
		String fileName;
		File membersFile;
		
		System.out.println("\n-- Loading Members --");
		
		do {
			
			System.out.print("\nWhat is the name of the file you would like to load in? ");
			fileName = scnr.nextLine();
			membersFile = new File(fileName + ".csc");


			if(membersFile.canRead())
				fileAvailable = true;
			else if(!membersFile.exists()) {
				System.out.println("File: " + fileName + ".csc does not exist! Please enter correct file.");
				fileAvailable = false;
			}
			else {
				System.out.println("File can't be read. Please enter correct filename."); 
				fileAvailable = false;
			}
		} while(!fileAvailable);

		members = MembersList.load(fileName + ".csc");
		System.out.println("File: " + fileName + ".csc loaded successfully.");
		saved = false;
		
	}
	
	public static void save() throws IOException {
		
		boolean canWrite;
		boolean loop = true;
		String fileName;
		String response;
		File membersFile;
		PrintWriter file;

		System.out.println("\n-- Saving Members --");

		do {

			boolean canCreate = true;
			file = null;

			System.out.print("\nWhat would you like to save this file as? ");
			fileName = scnr.nextLine();
			membersFile = new File(fileName + ".csc");

			if(membersFile.exists()) {
				System.out.print("File: " + fileName + " already exists, would you like to overwrite it? Y/N: ");
				response = scnr.nextLine().toLowerCase();
				if(response.charAt(0) == 'y')
					canCreate = true;
				else {
					canCreate = false;
					loop = true;
				}
			}else
				canCreate = true;
			
			if(canCreate)
				file = new PrintWriter(fileName + ".csc");
			
			file.close();

			canWrite = membersFile.canWrite(); 

			if(!canWrite && canCreate) { 
				System.out.println("\nCan't write to file. Please enter another filename.");
				loop = true;
			}else if(canWrite && canCreate)
				loop = false;

		} while(loop);
		
		compare(members);
		edited = false;
		members.save(fileName + ".csc");

		System.out.println("\nSaved file: " + fileName + ".csc successfully.");
		saved = true;
		waitUserInput();
		
	}
	
	
	public static boolean quit() {
		
		if(!saved) 
			System.out.print("\nThere are currently unsaved changes. Do you still want to exit CSC Match? Y/N: ");
		else
			System.out.print("\nDo you want to exit CSC Match? Y/N: ");
		
		String response = scnr.nextLine().toLowerCase();
		return response.charAt(0) == 'y';
		
	}
	
	
	public static void compare(MembersList m) { //runs through the minimum required loop of all members within the members list and ensures they are all compared with one another.
		int z = 0;
		for(int i = 0; i < m.size() - 1; i++) {
			z++;
			for(int j = z; j < m.size(); j++) {
				m.get(i).compareTo(m.get(j));
				m.get(j).compareTo(m.get(i));
			}

		}
	}
	
	/* Below - private helper methods */
	
	
	private static int selectMember(String action, boolean askAdd) { //returns user selection of member based on their index, -1 if there are no members
		
		int selection = 0;
		
		if(membersEmpty()) { //check if CSC Match has no members, if so return -1, else continue
			if(askAdd)
				System.out.println("\n-- No Members, please add some first --\n");
			else
				System.out.println("\n-- No Members --\n");
			waitUserInput();
			return -1;
		}
		
		listAllMembersSelection();
		System.out.print("Which member would you like to " + action + "? (1, 2, 3...): ");
		selection = getInt(1, members.size(), "selection") - 1; //get integer from scanner using method with exception handling and ensure user selects within the correct range
		
		return selection;
		
	}
	

	private static void listAllMembersSelection() { //Lists all members though with integers for user selection
		System.out.println(members.toStringSelection());
	}
	

	private static boolean membersEmpty() {
		return members.isEmpty();
	}
	
	
	private static void listAllInterests(Member m) {
		System.out.println(members.allInterests(m));
	}
	

	private static String checkName(String n) { //ensures name passed isn't in use by a member in members list

		boolean unique = true;

		do {
			
			unique = true;
			
			Iterator<Member> itr = members.iterator();
			while(itr.hasNext() && unique) { //iterate through whole list unless name is found to not be unique at which point continue
				unique = !itr.next().getName().equalsIgnoreCase(n);
			}
			
			if(!unique) {
				System.out.print("Sorry, that name is taken, please enter a different name: ");
				n = adjustInput(scnr.nextLine()); //gets rid of leading and trailing spaces while capitalizing only the first letter ensures consistent inputs
			}
			
		} while(!unique);
		
		return n; //will return original name if  unique, or a new name the user inputed which is unique
		
	}
	

	private static int getInt(int min, int max, String type) { //exception handling for getting integers from scanner including range checking
		
		int temp = 0;
		boolean loop = false;
		
		do {
			
			try { //ensure user enters an integer
				
				temp = scnr.nextInt();
				scnr.nextLine();
				
				if(temp < min || temp > max) { //ensure user entered an integer within a correct range
					System.out.print("Invalid " + type + ", please enter a valid " + type + " (" + ((min != max) ? + min + "-" + max : min) + "): ");
					loop = true;
				} else
					loop = false;
				
			} catch(InputMismatchException e) {
				System.out.print("Please enter an integer: ");
				scnr.nextLine();
				loop = true;
			}
			
		} while(loop);

		return temp;

	}
	
	
	private static void waitUserInput() { //allows user to remain at current state until they hit enter.
		System.out.print("Press enter to continue.");
		scnr.nextLine();
	}
	

	private static String adjustInput(String s) { //trim leading and trailing white spaces while capitalizing only first letter.
		s = s.toLowerCase();
		s = s.trim();
		return s.toUpperCase().charAt(0) + s.substring(1, s.length());
	}
	

	private static boolean inquireLoop(String action) {
		String input;
		System.out.print("\nWould you like to " + action + "? Y/N: ");
		input = scnr.nextLine().toLowerCase();
		return input.startsWith("y");
	}
	

	private static void printInstructions() { //selection list displayed to user
		System.out.println("\tA. Add a Member");
		System.out.println("\tB. Add an Interest to a Member");
		System.out.println("\tC. List Member");
		System.out.println("\tD. List All Members");
		System.out.println("\tE. Remove a Member");
		System.out.println("\tF. Load the Members");
		System.out.println("\tG. Save the Members");
		System.out.println("\tH. Quit");
		System.out.print("\t(Enter A, B, C...): ");
	}

}
