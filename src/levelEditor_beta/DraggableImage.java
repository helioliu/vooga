package levelEditor_beta;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


import org.w3c.dom.Element;
/**
 * Creates a image icon that can be dragged along the jlayeredpane
 * 
 * @author Charlie Hatcher
 *
 */
@SuppressWarnings("serial")
public class DraggableImage extends JLabel implements MouseMotionListener{
        private static final int Y_CORNER_OF_LEVEL = 100;
        private static final int X_CORNER_OF_LEVEL = 100;
        private int x,y;
        private ImageIcon myIcon;
        private Viewport myParent;
        private Element myProperties;
        @SuppressWarnings("unused")
        private String myName;
        private String myXCoordinate;
        private String myYCoordinate;
        private String myImagePath;
        
        private boolean myFlag = false;
        
        
        public DraggableImage(Viewport parent, Element data, boolean b) {
                super();
                this.myProperties=data;
                gatherData(b);
        this.myIcon = new ImageIcon(myImagePath);
        this.myParent = parent;
        this.setIcon(myIcon);
                setJLabelValuesForDraggableImage(myIcon, parent, x, y);
        }

        

        protected void setSpriteProperties(int x,int y){
                if(checkIfSelected(x, y)){
                        PopupFrame frame = new PopupFrame(x,y);
                        myParent.add(frame);
                }
        }
        
        /**
         * Moves the DraggableImage if the image is selected, if the DraggableImage is
         * currently selected, and the user clicks, the image will drop. If the user clicks
         * on the screen and the mouse's position is not in the image, the image does not
         * move.
         * @param x
         * @param y
         * @return
         */
        protected boolean moveIfSelected(int x, int y){
                if(myFlag){
                        setFlagOnClick(x, y, false);
                }
                else if(!myFlag){
                        setFlagOnClick(x,y, true);
                        
                        
                }
                return myFlag;
        }

        /**
         * If the DraggableImage is selected, set the DraggableImage's x and y coordinates
         * to the x and y value of the mouse.
         */
        @Override
        public void mouseMoved(MouseEvent arg0) {
                if (myFlag){
                        x = arg0.getX();
                        y = arg0.getY();
                        setJLabelValuesForDraggableImage(myIcon, myParent, x, y);
                }
        }
        
        /**
         * 
         * Sets all the necessary parameters to the DraggableImage in order to see it on
         * the viewport. Moves the new Image to the front of layeredpane.
         * @param icon
         * @param parent
         */
        private void setJLabelValuesForDraggableImage(ImageIcon icon,
                        Viewport parent, int x, int y) {
        parent.add(this, 1);
                this.setVisible(true);
                this.setBounds(x, y, icon.getIconWidth(), icon.getIconWidth());
                parent.moveToFront(this);
        }
        
        /**
         * Used to read the properties from the sprite based upon the xml file.
         * If the image is created by a button press, it plases the image in the corner
         * of the level image so that the image does not overlap with its root image
         * 
         * @param isAddedFromButton
         */
        private void gatherData(boolean isAddedFromButton) {
                myName = myProperties.getElementsByTagName("name").item(0).getTextContent();
        myXCoordinate = myProperties.getElementsByTagName("x").item(0).getTextContent();
        myYCoordinate = myProperties.getElementsByTagName("y").item(0).getTextContent();
        myImagePath = myProperties.getElementsByTagName("image").item(0).getTextContent();
        if(!isAddedFromButton){
                 x = new Integer(myXCoordinate);
             y = new Integer(myYCoordinate);
        }
        else{
                 x = X_CORNER_OF_LEVEL;
             y = Y_CORNER_OF_LEVEL;
        }
        }
        
        /**
         * Checks to see if the DraggableImage is selected, sets the flag on the click.
         * @param x
         * @param y
         * @param boolean resulting_flag
         */
        private void setFlagOnClick(int x, int y, boolean resulting_flag) {
                if(checkIfSelected(x, y)){
                        myFlag = resulting_flag;
                }
        }
        
        /**
         * Checks to see if the mouse's position is inside the DraggableImage.
         * @param x
         * @param y
         * @return
         */
        private boolean checkIfSelected(int x, int y) {
                return ((this.getX()-this.getWidth())<x && (this.getX()+this.getWidth())>x)
                                && ((this.getY()-this.getHeight())<y) &&(this.getY()+this.getHeight())>y;
        }
        
        @Override
        public void mouseDragged(MouseEvent arg0) {             
                
        }
}

