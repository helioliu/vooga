package levelEditor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;


public class Controller {

	public Controller() {
		
	}
	
	public void makeSample() throws IOException {

		Gson gson = new Gson();
		ArrayList<SpriteInfo> Levels = new ArrayList<SpriteInfo>();
		SpriteInfo info1=new SpriteInfo("Character","mario1.png",100,000,false, true);
		Levels.add(info1);
		SpriteInfo info2= new SpriteInfo("Bad_Guys","villain.png",200,100,true, true);
		Levels.add(info2);
		SpriteInfo info3= new SpriteInfo("Bad_Guys","villain.png",300,200,true, true);
		Levels.add(info3);
		String jsonString = gson.toJson(Levels);
		System.out.println("Generated json text: " + jsonString);
		// let's write our JSON string to a file
		FileWriter fileOut = new FileWriter("sampleLevel.json");
		BufferedWriter out = new BufferedWriter(fileOut);
		out.write(jsonString);
		out.close();
	}
}

