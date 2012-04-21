package levelEditor_beta;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

/**
 * The main rendering and drawing area of the editor. It is composed of the
 * palette, which is a panel of buttons with pictures of sprites on them, and
 * the viewport, which is where the level is actually rendered and where the
 * user can place new sprites.
 * @author Alex Lee
 * @author Charlie Hatcher
 */
@SuppressWarnings("serial")
public class DrawingBoard extends JPanel
{

    @SuppressWarnings("unused")
        private SwingGUI owner;

    private Palette palette;

    private Viewport viewport;

    private JLabel statusbar;

    public DrawingBoard(SwingGUI owner)
    {
        /*
         * User a BorderLayout so that we can easily fill all available space
         * with the viewport.
         */
        super(new BorderLayout());

        /*
         * Associate this with the controller.
         */
        this.owner = owner;

        /*
         * Load the background image so that we know how big the drawing board
         * should be.
         */
        @SuppressWarnings("unused")
                BufferedImage bgimage = null;
        try
        {
            bgimage = ImageIO.read(new File("src/levelEditor_beta/images/space.jpg"));
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }

        /*
         * Now we have enough information to create the JLayeredPane. It is
         * placed in the center so that it will take all available space.
         */
        
        viewport = new Viewport(this);
        JScrollPane layersHolder = new JScrollPane(viewport);
        this.add(layersHolder, BorderLayout.CENTER);
        

        /*
         * Create the palette on the left.
         */
        
        palette = new Palette(viewport);
        JScrollPane paletteHolder = new JScrollPane(palette,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paletteHolder.setPreferredSize(new Dimension(240, 600));
        add(paletteHolder, BorderLayout.WEST);
        

        /*
         * Create the statusbar.
         */
        this.statusbar = new StatusBar();
    }

    protected void setStatusBar(String message)
    {
        statusbar.setText(message);
    }

    public void loadFile(File f)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/levelEditor_beta/resource/samplelevel.xml");
            
            String bgpath = doc.getElementsByTagName("background").item(0).getTextContent();
            BufferedImage bgimage = ImageIO.read(new File(bgpath));
            viewport.load(bgimage);
            
            NodeList sprites = doc.getElementsByTagName("sprite");
            for(int i = 0; i < sprites.getLength(); i++)
            {
                Element sprite = (Element)sprites.item(i);
                DraggableImage newSprite = new DraggableImage(viewport, sprite, false);
                viewport.addImage(newSprite);
                viewport.addMouseMotionListener(newSprite);
                palette.addButton(sprite);
            }
        }
        catch(Exception e)
        {
            // TODO Add catch
            e.printStackTrace();
        }
    }

    public void saveFile(File f)
    {
        /*
         * TODO: This is where save would be implemented for the xml file
         */
    }

}

