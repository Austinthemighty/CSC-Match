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
	
	
	
	
	public static Members MembersList = new Members();
	
	public static void main(String[] args) throws IOException {
		
		
//		Member temp = new Member();
//		temp.name = "austin";
//		MembersList.addMember(temp);
		
		
		file MatchDB = new file();
		MatchDB.filePath = Paths.get("CSCMatchDB.csc");
		file SaveDB = new file();
		SaveDB.filePath = Paths.get("CSCMatchDB_save.csc");
		
		file.load(MatchDB.filePath);
		
		file.save(SaveDB.filePath);
//		file.addUser();
//		try{
//			file.load(MatchDB.filePath);
//			
//		}
//		catch(IOException e) {
//			System.out.println(e);
//		}
		
		
//		System.out.println(MembersList.size());
//		System.out.println(MembersList.toString());
	}
}
