package interactiveSprites.interactiveExample;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;


public class RPGDialog {
	
	GameFont font;
	BufferedImage box;
	int y=0;
	int frame;
	String myText;
	String[] NPCDialog = {"HI BIG CAT! IT'S SO GREAT TO SEE YOU!", "RAWWWRR! YOU LOOK LIKE YOU'VE BEEN WORKING OUT!", "BIG CAT, I'VE MISSED YOU!", "I'M STILL UPSET AT YOU!",
							"YOU'RE SO POWERFUL AND AUTHORITATIVE!"};
	
	
	public RPGDialog(GameFont font, BufferedImage box) {
		this.font = font;
		this.box = box;
		Random generator = new Random();
		//myText = NPCDialog[generator.nextInt(NPCDialog.length)];
	}
	
	public void setDialog() {
		Random generator = new Random();
		myText = NPCDialog[generator.nextInt(NPCDialog.length)];
	}
	
	public void render(Graphics2D g){
		g.drawImage(box, 50, 50, null);
	//	font.drawString(g, dialog, 20, 20);
	}

}
