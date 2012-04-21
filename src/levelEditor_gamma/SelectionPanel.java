package levelEditor_gamma;

import java.awt.BorderLayout;

import java.util.List;

import javax.swing.*;

@SuppressWarnings("serial")
public class SelectionPanel extends JPanel{

	private TableFrame table;
	private ButtonPanel buttons;
	
	public SelectionPanel(PlacementPanel placementPanel) {		
		super(new BorderLayout());
		
		table = new TableFrame();
		this.add(table, BorderLayout.NORTH);
		
		buttons = new ButtonPanel(this);
		this.add(buttons, BorderLayout.CENTER);
		
	}

}
