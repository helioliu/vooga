package levelEditor_beta;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

/**
 * A simple status bar. Used to display helpful messages to the user.
 */
@SuppressWarnings("serial")
public class StatusBar extends JLabel
{

    private static final String STATUS_BAR_MESSAGE = "Ready";

        public StatusBar()
    {
        super();
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setText(STATUS_BAR_MESSAGE);
    }

}
