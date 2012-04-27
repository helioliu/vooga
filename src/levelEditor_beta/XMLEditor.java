package levelEditor_beta;

import javax.swing.JTree;

import org.w3c.dom.Element;

/**
 * A simple XML editor.
 */
@SuppressWarnings("serial")
public class XMLEditor extends JTree
{

    @SuppressWarnings("unused")
        private Element properties;

    public XMLEditor(Element e)
    {
        this.properties = e;
    }

}

