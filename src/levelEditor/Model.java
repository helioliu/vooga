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
		levelName = "default";
	}

	public void Export(GameFile gameFile) throws IOException {
		Gson gson3 = new Gson();
		String jsonString3 = gson3.toJson(gameFile);
		FileWriter fs = new FileWriter(levelName + ".json");
		BufferedWriter o = new BufferedWriter(fs);
		o.write(jsonString3);
		o.close();

		Gson gson = new Gson();
		Scanner Levelscanner;
		Levelscanner = new Scanner(new File("Levels.json"));
		String LevelFile = Levelscanner.useDelimiter("\\A").next();

		Type collectionType = new TypeToken<ArrayList<String>>() {
		}.getType();
		ArrayList<String> levelnames = gson.fromJson(LevelFile, collectionType);

		if (levelnames==null) {
			levelnames= new ArrayList<String>();
		}		
		levelnames.add(levelName + ".json");
		String jsonString = gson.toJson(levelnames);
		// let's write our JSON string to a file
		FileWriter fileOut = new FileWriter("Levels.json");
		BufferedWriter out = new BufferedWriter(fileOut);
		out.write(jsonString);
		out.close();
	}

	public void SetLevelName(String name) {
		levelName = name;
	}

}
