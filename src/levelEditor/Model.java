package levelEditor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Model {

	private String levelName;

	public Model() {
	}

	public void Export(GameFile gameFile, String name) throws IOException {
		levelName=name;
		
		Gson gson3 = new Gson();
		String jsonString3 = gson3.toJson(gameFile);
		FileWriter fs = new FileWriter(levelName);
		BufferedWriter o = new BufferedWriter(fs);
		o.write(jsonString3);
		o.close();

		ArrayList<String> levelnames; 
	
		Gson gson = new Gson();
		File f = new File("Levels.json");
		if(f.exists())  {

		Scanner Levelscanner;
		Levelscanner = new Scanner(new File("Levels.json"));
		String LevelFile = Levelscanner.useDelimiter("\\A").next();

		Type collectionType = new TypeToken<ArrayList<String>>() {
		}.getType();
		levelnames = gson.fromJson(LevelFile, collectionType);
		if (levelnames==null) {
			levelnames= new ArrayList<String>();
		}
		
		if (!levelnames.contains(levelName)) {
		levelnames.add(levelName);}
		} else {
			levelnames= new ArrayList<String>();
			levelnames.add(levelName);
			
		}
		System.out.println(levelnames);
		String jsonString = gson.toJson(levelnames);
		FileWriter fileOut = new FileWriter("Levels.json");
		BufferedWriter out = new BufferedWriter(fileOut);
		out.write(jsonString);
		out.close();
	}

	public void SetLevelName(String name) {
		levelName = name;
	}

}
