package levelEditor_beta;


import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
/**
 * Creates a internal frame that is used as a pop up menu for when the user right clicks
 * on an image file. The JScrollPane is used to add the EditSpriteProperties table into
 * the Internal Frame
 * @author Charlie Hatcher
 *
 */
@SuppressWarnings("serial")
public class PopupFrame extends JInternalFrame{
        public PopupFrame(int x, int y){
                super("Example", true, true, true, true);
                this.setSize(200, 200);
                this.setLocation(x, y);
                JScrollPane scroll = new JScrollPane(new EditSpriteProperties());
                this.add(scroll);
                this.show();
        }
}

