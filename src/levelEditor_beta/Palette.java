package levelEditor_beta;

import java.awt.*;
import javax.swing.*;

import org.w3c.dom.Element;

/**
 * A palette of sprite buttons. To be used with the DrawingBoard.
 */
@SuppressWarnings("serial")
public class Palette extends JPanel
{

    public static final Dimension DEFAULT_SIZE = new Dimension(220, 600);
   
    private Viewport pane;
   
    public Palette(Viewport pane)
    {
        super(new FlowLayout(FlowLayout.LEFT));
        this.pane = pane;
        this.setPreferredSize(DEFAULT_SIZE);
    }
   
    public void addButton(Element e)
    {
        SpriteButton newButton = new SpriteButton(pane, e);
        this.add(newButton);
        this.revalidate();
    }

}


