package levelEditor_gamma;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class DraggableObject extends JComponent {

	private boolean draggable = true;
	protected Point anchorPoint;
	protected Cursor draggingCursor = Cursor
			.getPredefinedCursor(Cursor.HAND_CURSOR);
	protected boolean overbearing = false;
	final DraggableObject handle = this;

	public DraggableObject() {
		addMouseMotionListener();
		setOpaque(true);
		setBackground(new Color(240, 240, 240));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}


	private void removeMouseMotionListener() {
		for (MouseMotionListener listener : this.getMouseMotionListeners()) {
			removeMouseMotionListener(listener);
		}
		setCursor(Cursor.getDefaultCursor());
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
		if (draggable) {
			addMouseMotionListener();
		} else {
			removeMouseMotionListener();
		}

	}

	public Cursor getDraggingCursor() {
		return draggingCursor;
	}

	public void setDraggingCursor(Cursor draggingCursor) {
		this.draggingCursor = draggingCursor;
	}

	public boolean isOverbearing() {
		return overbearing;
	}

	public void setOverbearing(boolean overbearing) {
		this.overbearing = overbearing;
	}

    private void addMouseMotionListener() {
        /** This handle is a reference to THIS beacause in next Mouse Adapter "this" is not allowed */
        final DraggableObject handle = this;
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x - anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(position);

                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }
            }
        });
    }

}
