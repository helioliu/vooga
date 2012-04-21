package levelEditor_beta;


import javax.swing.JToolBar;
import javax.swing.JButton;

/**
 * The toolbar has useful buttons.
 */
@SuppressWarnings("serial")
public class ToolBar extends JToolBar
{

    public ToolBar()
    {
        super();
        
        this.add(new JButton("Arrow"));
        this.add(new JButton("Grab Scroll"));
    }

}

