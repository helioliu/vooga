package levelEditor_beta;

import javax.swing.JFrame;

/**
 * Used to invoke the level editor. Developers may invoke SwingGUI directly.
 */
public class Main
{

    public static void main(String[] args)
    {
        SwingGUI g = new SwingGUI();
        g.pack();
        g.setVisible(true);
        g.setExtendedState(g.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

}

