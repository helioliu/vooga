package levelEditor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LevelNameWindowMaker extends JPanel{
	
	JPanel myPanel;
	JTextField nameField;
	String name;
	

	public LevelNameWindowMaker(String levelName){
		this.name=levelName;
		myPanel = new JPanel();
        //Don't allow us to stretch vertically.
        setLayout(new FlowLayout());
      
        nameField= new JTextField(15);
        nameField.setText("defaultname");
        myPanel.add(nameField);
        JButton submitButton=new JButton("Submit Name Change");   
		submitButton.addActionListener(new SubmitAction());
        myPanel.add(submitButton);
        
        add(myPanel);

	}
	
	
	private class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
                changeName();
				myPanel.revalidate();
		}
	}
	
	public void changeName() {
		if (nameField.getText().isEmpty()) {
			name="default.json";
			return;
		}
		name= nameField.getText() + ".json";
		System.out.println(name);
	}
}
