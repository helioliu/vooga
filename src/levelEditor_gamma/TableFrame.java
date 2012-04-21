package levelEditor_gamma;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TableFrame extends JPanel{
	
	SpritePropertiesTable table;
	
    public TableFrame(){
    		super();
//            this.setSize(50, 20);
//            this.setLocation(x, y);
    		table = new SpritePropertiesTable();
            JScrollPane scroll = new JScrollPane(new SpritePropertiesTable());
            this.add(scroll);
    }
    
    @Override
    public void setPreferredSize(Dimension d){
    	table.setPreferredSize(d);
    }
}
