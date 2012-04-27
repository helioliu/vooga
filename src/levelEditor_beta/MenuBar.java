package levelEditor_beta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The menu bar for the level editor.
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{

    private SwingGUI owner;
   
    private JFileChooser fileChooser;

    public MenuBar(SwingGUI owner)
    {
        super();
       
        this.owner = owner;

        /*
         * Set up the File menu.
         */
        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        this.add(filemenu);

        JMenuItem newmenuitem = new JMenuItem("New");
        newmenuitem.setMnemonic(KeyEvent.VK_N);
        newmenuitem.addActionListener(new NewAction());
        filemenu.add(newmenuitem);

        JMenuItem loadmenuitem = new JMenuItem("Load");
        loadmenuitem.setMnemonic(KeyEvent.VK_L);
        loadmenuitem.addActionListener(new LoadAction());
        filemenu.add(loadmenuitem);

        JMenuItem savemenuitem = new JMenuItem("Save");
        savemenuitem.setMnemonic(KeyEvent.VK_S);
        savemenuitem.addActionListener(new SaveAction());
        filemenu.add(savemenuitem);

        JMenuItem exitmenuitem = new JMenuItem("Exit");
        exitmenuitem.setMnemonic(KeyEvent.VK_X);
        exitmenuitem.addActionListener(new ExitAction());
        filemenu.add(exitmenuitem);
    }

    private class NewAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private class LoadAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fileChooser = new JFileChooser();
            int val = fileChooser.showOpenDialog(MenuBar.this);
            if(val == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                owner.loadFile(file);
            }
        }
    }

    private class SaveAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fileChooser = new JFileChooser();
            int val = fileChooser.showSaveDialog(MenuBar.this);
            if(val == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                owner.saveFile(file);
            }
        }
    }

    private class ExitAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}


