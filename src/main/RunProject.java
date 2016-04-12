package main;

import java.io.IOException;

public class RunProject {
	
	public static void main(String[] args) throws IOException {
		ParsingConfig con = new ParsingConfig();
		NotParsedInfo info = new NotParsedInfo();
		
		con.createEverything();
		info.fillTable();
		
	}
	
}
