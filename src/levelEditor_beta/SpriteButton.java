package levelEditor_beta;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import org.w3c.dom.Element;

/**
 * A button that holds a sprite.
 * @author Charlie Hatcher
 * @author Alex Lee
 */
@SuppressWarnings("serial")
public class SpriteButton extends JButton
{

    private ImageIcon myIcon;
    private Viewport myPane;
    @SuppressWarnings("unused")
        private String myImageLocation;
    private Element spriteProperties;

    public SpriteButton(Viewport pane, Element spritedata)
    {
        myPane = pane;
        spriteProperties = spritedata;
        String path = spriteProperties.getElementsByTagName("image").item(0).getTextContent();
        myIcon = new ImageIcon(path);
        this.setIcon(myIcon);
        this.setPreferredSize(new Dimension(100, 100));
        this.addActionListener(new ClickAction());
    }

    private class ClickAction implements ActionListener
    {
        
        /**
         * If the button is clicked, create the image represented by the button
         * and place it on the level image. The MouseMotionListener is used by the
         * level image to determine if the image has been selected and if it is being
         * dragged.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            DraggableImage s = new DraggableImage(myPane, spriteProperties, true);
            myPane.addMouseMotionListener(s);
            myPane.addImage(s);
        }
       

    }
    
  

}

