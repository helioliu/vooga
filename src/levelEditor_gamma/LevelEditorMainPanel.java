package levelEditor_gamma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class LevelEditorMainPanel extends JPanel {

	private LevelEditorView myGUI;
	private PlacementPanel placementPanel;
	private SelectionPanel selectionPanel;
	private TableFrame attributeTable;
	
	
	public LevelEditorMainPanel(LevelEditorView gui) {
		super(new BorderLayout());
		myGUI = gui;
		
		placementPanel = new PlacementPanel(this);
//		selectionPanel = new SelectionPanel(placementPanel, attributeList);
		selectionPanel = new SelectionPanel(placementPanel);
		attributeTable = new TableFrame();
		
		JScrollPane scrollablePlacementPanel = new JScrollPane(placementPanel);
		scrollablePlacementPanel.setPreferredSize(new Dimension(600, 800));
		this.add(scrollablePlacementPanel, BorderLayout.CENTER);

		selectionPanel.setPreferredSize(new Dimension(600, 400));
		this.add(selectionPanel, BorderLayout.EAST);

	}
}
