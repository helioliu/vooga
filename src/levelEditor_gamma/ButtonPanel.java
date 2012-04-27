package levelEditor_gamma;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{

	private SelectionPanel owner;
	
	public ButtonPanel(SelectionPanel sp){
		super(new FlowLayout());
		owner = sp;
		
		setSize(sp.getWidth()-100, 200);
	    setBackground(Color.WHITE);
		
		JButton button = new JButton();
		button.setSize(30, 30);
		add(button);
	    
		setVisible(true);
	}
}
