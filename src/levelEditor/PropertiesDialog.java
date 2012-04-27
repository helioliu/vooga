package levelEditor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class PropertiesDialog extends JDialog implements ActionListener{

	private JDialog propertiesDialog;
	private Tile propertyTile;
	private JSpinner tileNumber;
	private JTextField userText;
	private JTextField tileName;
	private JTextField tileType;
	private JLabel tileImg;
	private JButton applyBtn;
	private JButton cancelBtn;
	private JButton deleteBtn;
	private JTextField imageFile;
	
	private GraphicsBank myGraphicsBank;
	private JFrame myOwner;
	
	public PropertiesDialog(GraphicsBank gbank, JFrame owner){
		myGraphicsBank = gbank;
		myOwner = owner;
		createPropertiesDialog(myOwner);
	}
	
	public void createPropertiesDialog(JFrame dialogOwner) {

		/* Setup for the proeprties dialog */
		propertiesDialog = new JDialog(dialogOwner, "Tile Properties");
		propertiesDialog.setSize(300, 300);
		propertiesDialog.setLocationRelativeTo(null);
		tileName = new JTextField("", 20);
		tileType = new JTextField("", 20);
		imageFile = new JTextField("", 20);
		imageFile.setEditable(false);
		tileNumber = new JSpinner();
		userText = new JTextField("", 20);
		propertyTile = null;
		tileImg = new JLabel();
		tileImg.setHorizontalAlignment(SwingConstants.CENTER);
		tileImg.setBorder(new TitledBorder("Image"));
		JPanel cp = (JPanel) propertiesDialog.getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel p2 = new JPanel(new BorderLayout());
		cp.add(p2, BorderLayout.CENTER);
		cp.add(tileImg, BorderLayout.NORTH);
		JPanel btns1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3, 3, 3, 3);

		c.gridx = 0;
		c.gridy = 0;

		btns1.add(new JLabel("ID"), c);
		c.gridx = 1;
		c.ipadx = 30;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		btns1.add(tileNumber, c);
		c.ipadx = 0;

		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 1;
		btns1.add(new JLabel("Type"), c);
		c.gridx = 1;
		btns1.add(tileType, c);
		c.gridx = 0;
		c.gridy = 2;

		btns1.add(new JLabel("Name"), c);
		c.gridx = 1;
		btns1.add(tileName, c);

		c.gridx = 0;
		c.gridy = 3;
		btns1.add(new JLabel("User Text"), c);
		c.gridx = 1;
		btns1.add(userText, c);

		p2.add(btns1, BorderLayout.NORTH);

		/* The buttons */
		applyBtn = new JButton("Save");
		deleteBtn = new JButton("Delete Tile");
		cancelBtn = new JButton("Cancel");
		applyBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		JPanel btns2 = new JPanel(new GridLayout(1, 3));
		btns2.add(deleteBtn);
		btns2.add(applyBtn);
		btns2.add(cancelBtn);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;

		btns1.add(btns2, c);

		propertiesDialog.setSize(300, 500);
		propertiesDialog.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == applyBtn && propertyTile != null) {
			propertyTile.name = tileName.getText();
			propertyTile.type = tileType.getText();
			propertyTile.number = ((Integer) tileNumber.getValue()).intValue();
			propertyTile.info = userText.getText();
			propertiesDialog.dispose();
			propertyTile = null;
		} else if (e.getSource() == cancelBtn) {
			propertiesDialog.dispose();
			propertyTile = null;
		} else if (e.getSource() == deleteBtn) {
			if (propertyTile != null) {
				myGraphicsBank.remove(propertyTile);
				propertyTile = null;
			}
			propertiesDialog.dispose();
		} else {
			System.err.println("Unknown button fired action. " + e);
		}
	}
}
