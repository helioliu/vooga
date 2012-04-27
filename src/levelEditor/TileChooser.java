package levelEditor;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class TileChooser extends JPanel implements ActionListener,GraphicsBankChangeListener {
	// DropTarget dropTarget;
	private GridLayout layout;
	private GraphicsBank myGraphicsBank;
	private int tileWidth = 32;
	private Tile selectedTile;
	private ButtonGroup group;
	private JPanel tilePanel;
	private JPanel spacer;

	/* For tile properties dialog */
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

	private LevelEditorController myController;
	private FileDropHandler fileDrop;

	public TileChooser(GraphicsBank graphicsBank) {
		tilePanel = new JPanel();
		layout = new GridLayout(0, 5);
		tilePanel.setLayout(layout);

		this.setLayout(new BorderLayout());
		this.add(tilePanel, BorderLayout.NORTH);

		spacer = new JPanel();
		spacer.add(new JLabel("  Drop new tiles here"));
		spacer.setToolTipText("Drop image files here to create more tiles.");
		this.add(spacer, BorderLayout.CENTER);
		this.myGraphicsBank = graphicsBank;
		reset();

		fileDrop = new FileDropHandler(this);
		setTransferHandler(fileDrop);
		graphicsBank.addChangeListener(this);
		propertiesDialog = null;
	}

	public TileChooser(GraphicsBank gfx, LevelEditorController controller,JFrame dialogOwner) {
		this(gfx);
		myController = controller;
		createPropertiesDialog(dialogOwner);
	}

	public void createPropertiesDialog(JFrame dialogOwner) {

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


	public void reset() {
		int count = 0;
		tilePanel.removeAll();
		group = new ButtonGroup();

		TileButton b = new TileButton(null,myGraphicsBank, this);
		tilePanel.add(b);
		group.add(b);
		count++;

		Iterator i = myGraphicsBank.iterator();
		while (i.hasNext()) {
			b = new TileButton((Tile) i.next(), myGraphicsBank, this);
			tilePanel.add(b);
			group.add(b);
			count++;
		}

		if (count <= 0) {
			spacer.setPreferredSize(new Dimension(1, 100));
		} else {
			spacer.setPreferredSize(new Dimension(1, 30));
		}

		tilePanel.revalidate();
		repaint();
	}

	public void tilesetUpdated(GraphicsBank g) {
		if (g == myGraphicsBank) {
			reset();
		}
	}

	public void tileRemoved(GraphicsBank g, Tile t) {
		if (g == myGraphicsBank) {
			reset();
		}
	}

	public void tileAdded(GraphicsBank g, Tile t) {
		TileButton b = new TileButton(t, myGraphicsBank, this);
		tilePanel.add(b);
		group.add(b);
		spacer.setPreferredSize(new Dimension(1, 30));
		tilePanel.revalidate();
		repaint();
	}

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

	public void setWidth(int width) {
		if (width >= tileWidth + 8) {
			layout.setColumns(width / (tileWidth + 15));
			tilePanel.revalidate();
		}

	}

	public Tile getSelectedTile() {
		return selectedTile;
	}

	public void showProperties(Tile t) {
		propertyTile = t;

		if (t != null) {
			userText.setText(t.getInfo());
			tileNumber.setValue(new Integer(t.getNumber()));
			tileName.setText(t.getName());
			tileType.setText(t.getType());
			tileImg.setIcon(new ImageIcon(t.getImage()));

			applyBtn.setEnabled(true);
			deleteBtn.setEnabled(true);
			userText.setEditable(true);
			tileNumber.setEnabled(true);
			tileName.setEditable(true);
			tileType.setEditable(true);
		} else {
			userText.setText("");
			tileNumber.setValue(new Integer(0));
			tileName.setText("Null (Erases existing tiles)");
			tileType.setText("");
			tileImg.setIcon(null);

			userText.setEditable(false);
			tileNumber.setEnabled(false);
			tileName.setEditable(false);
			tileType.setEditable(false);
			applyBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}

		propertiesDialog.pack();
		propertiesDialog.setVisible(true);

	}

	public void importImageAsTile(File f) throws IOException {

		if (f.isDirectory()) {
			File[] contents = f.listFiles();

			for (int num = 0; num < contents.length; num++) {
				importImageAsTile(contents[num]);
			}
		}
		try {
			ImageIO.read(f);
		} catch (Exception e) {
			System.out.println("FAIL");
			return;
		}

		int n = myGraphicsBank.getUnusedNumber();
		Tile t = new Tile(n, f.getAbsolutePath(), "New Tile " + n, "No Type");
		myGraphicsBank.add(t);

		if (propertiesDialog != null) {
			showProperties(t);
		}
	}

	public void setSelectedTile(Tile t){
		selectedTile = t;
	}
}
