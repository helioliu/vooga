package levelEditor_gamma;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class PlacementPanel extends JLayeredPane {

	private LevelEditorMainPanel myLevelEditorMainPanel;

	public PlacementPanel(LevelEditorMainPanel levelEditorMainPanel) {
		super();
		this.myLevelEditorMainPanel = levelEditorMainPanel;

		this.addMouseMotionListener(new MouseMotionTracker());
		this.addMouseListener(new MouseClickListener());
	}
	
	public void loadBackgroundImage(){
		
	}
	
	private class MouseMotionTracker implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			int xCord = e.getX();
			int yCord = e.getY();
//			System.out.println("X: "+xCord + " Y: "+yCord);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			return;			
		}
		
	}
	
	@SuppressWarnings("unused")
	private class MouseClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			System.out.println("X: "+x + " Y: "+y);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			return;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			return;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			return;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			return;
		}
		
	}
	
	
}
