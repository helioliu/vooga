package levelEditor_gamma;

import java.awt.Dimension;


import javax.swing.JTable;

public class SpritePropertiesTable extends JTable {

	// private static List<String> attributes;
	//
	// public SpritePropertiesTable(List<String> attributeList){
	// super(setData(),setColumnNames());
	// this.setRowHeight(10);
	// this.setShowGrid(true);
	// this.setShowHorizontalLines(true);
	// this.setShowVerticalLines(true);
	// attributes = attributeList;
	// }
	//
	// private static Vector<String> setData(){
	// return (Vector<String>) attributes;
	// }
	// private static Vector<String> setColumnNames(){
	// List<String> col = new Vector<String>();
	// col.add("Sprite Properties");
	// col.add("Sprite Values");
	// return (Vector<String>) col;
	// }

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
