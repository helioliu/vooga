package levelEditor_beta;

import javax.swing.JTable;



@SuppressWarnings("serial")
public class EditSpriteProperties extends JTable{
        
        
        
        public EditSpriteProperties(){
                super(setData(),setColumnNames());
                
        }
        private static Object[][] setData(){
                Object[][] data = {
                            {"X Velocity", "",},
                            {"Y Velocity", "",},
                            {"Sprite Group", "",}};
                return data;
        }
        private static String[] setColumnNames(){
                String[] columnNames = {"Sprite Properties", "Sprite Values",};
                return columnNames;
        }
        
        

}

