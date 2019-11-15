import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CSCMatchCopy implements Serializable {

	private static MembersList members;
	private static Scanner scnr;

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {

		members = new MembersList();
		scnr = new Scanner(System.in);
		File membersFile;

		boolean exit = false;
		int memberIndex = 0;
		Member tempMember;
		boolean again;
		boolean edited = false;
		boolean loop = true;
		String fileName = "";

		System.out.println("Welcome to CSC Match!");
		Thread.sleep(1500);

		while(!exit) {

			String response;

			//introduce user
			System.out.println("\nWhat would you like to do?");
			printInstructions();

			response = scnr.nextLine().toLowerCase();
			switch(response.charAt(0)) {
			case 'a': //Add Member

				again = false;

				do {

					String name;
					int year = 0;

					System.out.println("\n-- Adding Member --\n");
					System.out.print("Please enter the new member's name: ");
					name = adjustInput(scnr.nextLine());

					System.out.print("Thanks, what year is " + name + " in? (1-5): ");
					year = getInt();

					addMember(name, year);
					System.out.println("Member successfully created.\n");

					again = inquireLoop("add another member");

				} while(again);

				edited = true;

				break;

			case 'b': //Add Interest to Member

				String interestName;
				int level;

				do {
					if(membersEmpty()) {
						System.out.println("\n-- No Members, please add some first --\n");
						waitUserInput();
						break;
					}
					listAllMembersSelection();
					System.out.print("Which member would you like to add an interest to? (1, 2, 3...): ");
					memberIndex = getInt() - 1;

					tempMember = members.get(memberIndex); //get member based on user's selection from member's list

					listAllInterests();

					System.out.println("\n--\nAdding interest to " + tempMember.getName());
					System.out.print("Enter the name of the interest you'd like to add: ");
					interestName = adjustInput(scnr.nextLine());

					System.out.print("Thanks, what is the interest level of " + interestName + " for " + tempMember.getName() + "?: ");
					level = getInt();

					addInterest(tempMember, interestName, level); //push information to addInterest method

					again = inquireLoop("add another interest"); //run method which asks the user if they'd like to run this again

				} while(again);

				edited = true;

				break;

			case 'c':

				do {
					if(membersEmpty()) {
						System.out.println("\n-- No Members, please add some first --\n");
						waitUserInput();
						break;
					}
					listAllMembersSelection();
					System.out.println("\n-- Listing a Member --\n");
					System.out.print("Which member would you like to display? (1, 2, 3...): ");
					memberIndex = getInt() - 1;
					tempMember = members.get(memberIndex);
					if(edited)
						compare(members);
					listMember(tempMember);
					waitUserInput();

					again = inquireLoop("list another member");

					edited = false;

				} while(again);

				break;

			case 'd':
				listAllMembers();
				waitUserInput();
				break;

			case 'e':


				do {
					if(membersEmpty()) {
						System.out.println("\n-- No Members --\n");
						waitUserInput();
						break;
					}
					listAllMembersSelection();
					System.out.println("\n-- Removing Member --\n");
					System.out.print("Which member would you like to remove? (1, 2, 3...): ");
					memberIndex = getInt() - 1;
					tempMember = members.get(memberIndex);
					String name = tempMember.getName();
					boolean removed = removeMember(tempMember);
					if(!removed)
						System.out.println("Member not removed");
					else {
						System.out.println(name + " removed successfully");
						edited = true;
					}

					again = inquireLoop("remove another member");

				} while(again);

				break;

			case 'f':

				boolean fileAvailable = false;
				
				System.out.println("\n-- Loading Members --");

				do {
					System.out.print("\nWhat is the name of the file you would like to load in? ");
					fileName = scnr.nextLine();
					membersFile = new File(fileName + ".csc");


					if(membersFile.canRead())
						fileAvailable = true;
					else if(!membersFile.exists()) {
						System.out.println("File: " + fileName + " does not exist! Please enter correct file.");
						fileAvailable = false;
					}
					else {
						System.out.println("File can't be read. Please enter correct filename."); 
						fileAvailable = false;
					}
				} while(!fileAvailable);

				members = MembersList.load(fileName + ".csc");

				break;

			case 'g':

				boolean canWrite;

				System.out.println("\n-- Saving Members --");

				do {

					boolean canCreate = true;
					PrintWriter file;

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
				waitUserInput();

				break;

			case 'h':
				if(edited) 
					System.out.print("\nThere are currently unsaved changes. Do you still want to exit CSC Match? Y/N: ");
				else
					System.out.print("\nDo you want to exit CSC Match? Y/N: ");
				response = scnr.nextLine().toLowerCase();
				exit = response.charAt(0) == 'y';
				break;

			default:
				System.out.println("\nPlease, make a selection");
				break;

			}

		}

	}

	public static void addMember(String n, int y) {
		n = checkName(n);
		y = checkInteger(y, 1, 5, "year");
		Member tempMember = new Member(n, y);
		members.add(tempMember);
	}

	private static int checkInteger(int y, int min, int max, String type) {

		while(y < min || y > max) {
			System.out.print("Invalid " + type + ", please enter your " + type + " (" + min + "-" + max + "): ");
			y = getInt();
		}

		return y;
	}

	private static String checkName(String n) {

		boolean unique = true;

		do {
			n = adjustInput(n);
			unique = true;
			Iterator<Member> itr = members.iterator();
			while(itr.hasNext() && unique) {
				unique = !itr.next().getName().equalsIgnoreCase(n);
			}
			if(!unique) {
				System.out.print("Sorry, that name is taken, please enter a different name: ");
				n = adjustInput(scnr.nextLine());
			}
		} while(!unique);
		return n;
	}

	public static void addInterest(Member m, String n, int l) {
		Interest i = new Interest(n);
		l = checkInteger(l, 0, 10, "interest level");
		members.interests.add(i);
		m.addInterest(i, l);
	}

	public static boolean removeMember(Member m) {
		boolean remove = false;
		String choice;
		System.out.print("Are you sure you'd like to remove member: " + m.getName() + "? Y/N: ");
		choice = scnr.nextLine().toLowerCase();
		remove = choice.startsWith("y");
		if(remove)
			members.remove(m);
		return remove;
	}

	public static void listMember(Member m) {
		System.out.println(m.toString());
	}

	public static void listAllMembers() {
		System.out.println(members.toString());
	}

	public static void listAllMembersSelection() {
		System.out.println(members.toStringSelection());
	}

	public static boolean membersEmpty() {
		return members.isEmpty();
	}

	public static void listAllInterests() {
		System.out.println(members.allInterests());
	}

	private static void compare(MembersList m) {
		int z = 0;
		for(int i = 0; i < m.size() - 1; i++) {
			z++;
			for(int j = z; j < m.size(); j++) {
				m.get(i).compareTo(m.get(j));
				m.get(j).compareTo(m.get(i));
			}

		}
	}

	private static int getInt() { //exception handling for getting integers from scanner.
		int temp = 0;
		boolean loop = false;
		do {
			try {
				temp = scnr.nextInt();
				scnr.nextLine();
				loop = false;
			} catch(InputMismatchException e) {
				System.out.print("Please enter an integer: ");
				scnr.nextLine();
				loop = true;
			}
		} while(loop);

		return temp;

	}
	
	private static void waitUserInput() {
		System.out.print("Press enter to continue.");
		scnr.nextLine();
	}

	private static String adjustInput(String s) {
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

	private static void printInstructions() {
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
