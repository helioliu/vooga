package levelEditor;

import java.awt.Dimension;


import javax.swing.JTable;

@SuppressWarnings("serial")
public class SpritePropertiesTable extends JTable {

	public SpritePropertiesTable() {
		super(setData(), setColumnNames());
		this.setPreferredSize(new Dimension(50,50));
	}

	private static Object[][] setData() {
		Object[][] data = { { "X Velocity", "", }, { "Y Velocity", "", },
				{ "Sprite Group", "", } };
		return data;
	}

	private static String[] setColumnNames() {
		String[] columnNames = { "Sprite Properties", "Sprite Values", };
		return columnNames;
	}

}
