
/**
 * @author Austin Chopra
 * Date: Oct 23, 2019
 * Course and Section Number:  CSC205AB, 12189
 * Program Name:  file.java
 * Program Description: how save and open work:
 *        save:
 *         save file in the xml format, test file can be found in the root of the cscMatch java folder, take linked list, parse the data and append it to the file
 *
 *        open:
 *								opens the file and scrubs the file and saves it to a a linked list.
 */


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

@SuppressWarnings("unused")
public class file {
	//	public static membersList<member> users;

	public Path filePath;

	/**
	 * @return 
	 * @throws IOException 
	 *
	 */
	public static Object load(Path fileName) throws IOException {
		int UUID = 0;
		String name= null;
		int year = 0;
		String interestName;
		int interestRating;
		boolean inInterestsList = false;
		boolean inUsersList = false;

		//		

		Object[] codes = Files.readAllLines(fileName, StandardCharsets.UTF_8).toArray();
//		System.out.println(codes[3]);

		for(int line = 0; line<codes.length; line++) {
			String code = codes[line].toString();
			//			System.out.println(line);
			//			System.out.println(codes[line]);

			while(!inUsersList && !inInterestsList) {
				if(code.contains("<usersList>")) {
					inUsersList = true;
					
				}
				if(code.contains("<interestsList>")) {
					inInterestsList = true;
				}
			}

			while(inUsersList) {
				if(code.contains("</userList>")) {
					inUsersList = false;
				}
				else if(code.contains("<UUID id=")) {																		//Find new user
					//create a new user

					UUID = Integer.parseInt(code.substring(code.indexOf("id=")+4, code.indexOf(">")-1));				//grab the users uuid
					System.out.println("UUID: " +UUID);

				}
				else if(code.contains("</UUID>")) {																		//close the new user
					//save all to the new user
					System.out.println("end users");
					//				newMember(UUID, name, year);
					//				Object temp = new Object();
					//				temp.UUID = UUID;
					//				temp.name = name;
					//				temp.year = year;

					//				System.out.println(temp);
					//				users.addMember(temp);
					//				System.out.println(users);

				}
				else if(code.contains("<user>") && code.contains("</user>")) {											//get the users name
					name = code.substring((code.indexOf("<user>")+6), code.indexOf("</user>"));
					System.out.println("Name: "+ name);
				}

				else if(code.contains("<year>") && code.contains("</year>")) {											//get the year of the user
					year = Integer.parseInt(code.substring((code.indexOf("<year>")+6), code.indexOf("</year>")));
					System.out.println("Year: "+ year);
				}

				else if(code.contains("<interests")) {																	//get the users interest
					//create new interest list for the member
					System.out.println("new interests list");
				}
				else if(code.contains("</interests>")) {
					//append the interest to the interest list
				}
				else if(code.contains("<interest>")) {
					//create new interest node
				}
				else if(code.contains("</interest>")) {
					//add interest node to interest list
				}
				else if(code.contains("<name>") && code.contains("</name>")) {
					//get name and add it to the interest node
					interestName = code.substring((code.indexOf("<name>")+6), code.indexOf("</name>"));
					System.out.println("Interest name: "+ interestName);

				}
				else if(code.contains("<rating>")&& code.contains("</rating>")) {
					//get the users rating for that interest and add it to the interest node
					interestRating = Integer.parseInt(code.substring((code.indexOf("<rating>")+8), code.indexOf("</rating>")));
					System.out.println("Interest rating: "+ interestRating);
				}



			}
			while(inInterestsList) {
				
			}
		}

		return true;
	}

	public static Object save(Path path) throws IOException {
		if(Files.exists(path)) {


			try {
				backupDB(path);
			}
			catch (IOException e){
				System.out.print(e);
			}
			//			String INTRO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!-- Valid DB file -->\n";
			//			Files.createFile(path);
			//			Files.write(path, INTRO.getBytes(StandardCharsets.ISO_8859_1), StandardOpenOption.APPEND);
		}
		else {
			newFile(path);
		}

		return true;

	}
	private static void backupDB(Path source) throws IOException {

		String path = source.toString();
		String textFileName = path.split(".csc")[0] + "_OLD.csc";

		Path destination = Paths.get(textFileName);
		Files.deleteIfExists(destination);
		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		Files.delete(source);
	}

	private static void newFile(Path path) throws IOException{
		//TODO test to make sure that after the "." there file extension is a ".csc" file else make it a csc file or throw error
		System.out.println("Createing New file");
		String INTRO = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?> <!-- Valid DB file -->\\n";
		Files.createFile(path);
		Files.write(path, INTRO.getBytes(StandardCharsets.ISO_8859_1), StandardOpenOption.APPEND);
	}

}
