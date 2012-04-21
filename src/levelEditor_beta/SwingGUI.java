package levelEditor_beta;

import java.awt.*;
import java.io.File;
import javax.swing.*;


/**
 * The main controller.
 */
@SuppressWarnings("serial")
public class SwingGUI extends JFrame
{

    private DrawingBoard board;

    private MenuBar menubar;

    public SwingGUI()
    {
        /*
         * Initialize the window.
         */
        setTitle("Level Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Set up the menubar. The menubar goes here and not with in the
         * DrawingBoard because JMenuBar is designed to go with JFrame.
         */
        menubar = new MenuBar(this);
        this.setJMenuBar(menubar);

        /*
         * Set up the drawing board, which is the main area of action.
         */
        board = new DrawingBoard(this);
        add(board, BorderLayout.CENTER);
    }

    public void loadFile(File f)
    {
        /*
         * For the purposes of testing, ignore the file that was given, and
         * just use the sample file so that I don't have to load it every damn
         * time.
         */
        board.loadFile(null);
    }
    
    public void saveFile(File f)
    {
        board.saveFile(f);
    }

}

