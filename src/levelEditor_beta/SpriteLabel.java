package levelEditor_beta;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import org.w3c.dom.*;

/**
 * Represents a sprite drawn on the screen. Stores its properties in XML.
 */
@SuppressWarnings("serial")
public class SpriteLabel extends JLabel
{

    @SuppressWarnings("unused")
        private Viewport owner;
    private ImageIcon icon;
    private Element properties;
    
    public SpriteLabel(Viewport owner, Element data)
    {
        super();
        this.properties = data;
        String name = properties.getElementsByTagName("name").item(0).getTextContent();
        String xs = properties.getElementsByTagName("x").item(0).getTextContent();
        String ys = properties.getElementsByTagName("y").item(0).getTextContent();
        String imgs = properties.getElementsByTagName("image").item(0).getTextContent();
        System.out.format("%s is at (%s, %s) with an image of %s\n", name, xs, ys, imgs);
        
        int x = new Integer(xs);
        int y = new Integer(ys);
        icon = new ImageIcon(imgs);
        owner.add(this, owner.LAYER_SPRITES);
        this.setVisible(true);
        this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.setIcon(icon);
        owner.moveToFront(this);
    }

}

