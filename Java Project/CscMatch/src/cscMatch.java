import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;


/**
 * @author Austin Chopra
 * Date: Oct 23, 2019
 * Course and Section Number:  CSC205AB, 12189
 * Program Name:  main.java
 * Program Description:
 *
 */

@SuppressWarnings("unused")
public class cscMatch {
	
//	public static membersList<member> users;
//	
//	public cscMatch() {
//		users = new membersList<member>();
//	}
//	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	file MatchDB = new file();
	MatchDB.filePath = Paths.get("CSCMatchD.csc");
	
//	try {
//		file.load(MatchDB.filePath);
////		System.out.print(file.load(MatchDB.filePath));
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.print(users);
	
//		System.out.println(file)
		//  Balance tester = new Balance();

		// file.save(filePath);
//	
		try{
			file.save(MatchDB.filePath);
		}
		catch(IOException e) {
		
		}
	}
}
