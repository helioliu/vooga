package levelBuilder;

import game.Platformer;
import game.PlatformGame;
import game.Platformer.CharacterProjectileCollision;
import game.Platformer.CharacterSpringCollision;
import game.Platformer.EnemyProjectileCollision;
import game.Platformer.SpritePlatformCollision;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import sprites.*;
import sprites.Character;

import levelEditor.GameFile;
import levelEditor.SpriteInfo;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LevelBuilder {
	public Platformer myGame;
	public Background background;


	public LevelBuilder(Platformer p) {
		myGame=p;
	}
	
	public PlayField createLevel(String jsonString) {
		PlayField field= new PlayField();
		GameFile myGameInfo;
		Gson gson = new Gson();
		Scanner scanner;
		try {
			scanner = new Scanner(new File(jsonString);

			String wholeFile = scanner.useDelimiter("\\A").next();
			Type collectionType = new TypeToken<GameFile>() {
			}.getType();
			myGameInfo = gson.fromJson(wholeFile, collectionType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(myGameInfo.toString());


		File backgroundPathFile= null; 
		BufferedImage myBackground=null;		
				try {
		backgroundPathFile= new File(myGameInfo.getBackground());

			myBackground = ImageIO.read(backgroundPathFile);
		} catch (IOException e1) {
			System.out.print("no background");
		}
		background = new ImageBackground(myBackground);
		field = new PlayField(background);
		// create groups
		CHARACTER = field.addGroup(new SpriteGroup("Character"));
		PROJECTILE = field.addGroup(new SpriteGroup("Projectile"));
		POWER_UP = field.addGroup(new SpriteGroup("Power_Up"));
		PLATFORM = field.addGroup(new SpriteGroup("Platform"));
		SPAWNPOINT = field.addGroup(new SpriteGroup("SPAWNPOINT"));
		COINS = field.addGroup(new SpriteGroup("COINS"));
		BAD_GUYS = field.addGroup(new SpriteGroup("BAD_GUYS"));
		SPRINGS = field.addGroup(new SpriteGroup("SPRINGS"));


		ArrayList<LESprite> SpriteList= new ArrayList<LESprite>();
		SpriteList.add(new Bad_Guys());
		SpriteList.add(new Character());
		SpriteList.add(new Platform());
		
		for (int i=0;i<SpriteList.size();i++) {
			if(SpriteList.get(i).isInstanceOf()) {
				Sprite= SpriteList.get(i).parse(o, myGame)
			}
		}
		
		for (int i = 0; i < myGameInfo.getList().size(); i++) {
			SpriteInfo info = myGameInfo.getList().get(i);
			String className = info.getClassName();
			System.out.println(className);
		    Sprite s;
			try {
				s = (Sprite) Class.forName(className).newInstance();
				File ImageFile= new File();

				BufferedImage myImage = ImageIO.read(ImageFile);
				s.setImage(myImage);
			PLATFORM.add(s);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	 

		

		// set up collision groups
		field.addCollisionGroup(CHARACTER, PROJECTILE,
				new CharacterProjectileCollision());
		field.addCollisionGroup(PROJECTILE, BAD_GUYS,
				new EnemyProjectileCollision());
		field.addCollisionGroup(CHARACTER, PLATFORM,
				new SpritePlatformCollision());
		field.addCollisionGroup(CHARACTER, SPRINGS,
				new CharacterSpringCollision());
		// playfield.addCollisionGroup(CHARACTER, BAD_GUYS,
		// new CharacterEnemyCollision());
		// playfield.addCollisionGroup(CHARACTER, COINS, new CoinCollision());
		// playfield.addCollisionGroup(CHARACTER, POWER_UP,
		// new PowerUpCollision());
		// playfield.addCollisionGroup(CHARACTER, PLATFORM,
		// new PlatformCollision());
		// playfield.addCollisionGroup(CHARACTER, EXIT, new ExitCollision());
		// playfield.addCollisionGroup(BAD_GUYS, PLATFORM,
		// new PlatformCollision());

		return field;
		
	}
}
